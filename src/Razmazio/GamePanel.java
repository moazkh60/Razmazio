/*
Certificate: We (Muhammad Moaz Khan and Raza Yunus) do verify that the submitted code is our own effort
and that we have not copied it from any peer or any internet source that has not been acknowledged.
We also understand that if our submission fails the similarity detection, we would be awarded zero marks
not only for this submission but the whole evaluation component. 
Programmed by:
1.	Muhammad Moaz Khan 		Reg no: 5621
2.	Raza Yunus			Reg no: 5810
*/

package Razmazio;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

class GamePanel extends JPanel implements ActionListener, Serializable
{
	// Creating array image for multiple objects
	protected transient Image mysterybox[]  = new Image[15];
	protected transient Image goomba[]	  	= new Image[10];
	protected transient Image points[][] 	= new Image[20][5];
	protected transient Image cashys[]	  	= new Image[10];
	
	// Creating images for single objects
	protected transient Image rz_background   = new ImageIcon("backgrounds\\bkg5"+ ".png").getImage();		// Background Image
	protected transient Image rz_still_right  = new ImageIcon("razmazio\\rz_still_right.png").getImage();	// Standing still
	protected transient Image rz_still_left   = new ImageIcon("razmazio\\rz_still_left.png").getImage();	// Walking left
	protected transient Image rz_walk_left2   = new ImageIcon("razmazio\\rz_walk_left2.png").getImage();	// 
	protected transient Image rz_walk_right2  = new ImageIcon("razmazio\\rz_walk_right2.png").getImage();	// Walking right
	protected transient Image rz_jump_right   = new ImageIcon("razmazio\\rz_right_jump.png").getImage();	// Jumping
	protected transient Image rz_jump_left    = new ImageIcon("razmazio\\rz_left_jump.png").getImage();		//
	protected transient Image pipe 		  	  = new ImageIcon("razmazio\\pipe1.png").getImage();			// pipe
	protected transient Image wall 	 	  	  = new ImageIcon("razmazio\\Wall.png").getImage();				// wall image
	protected transient Image mushroom2 	  = new ImageIcon("razmazio\\mushroom2.png").getImage();		// mushroom as mysteryItem
	protected transient Image point 		  = new ImageIcon("razmazio\\point.png").getImage();			// points
	protected transient Image empty 		  = new ImageIcon("razmazio\\empty.png").getImage();			// empty box
	protected transient Image cashy 		  = new ImageIcon("razmazio\\cash.png").getImage();				// cash
	
	transient Image obj = rz_still_right; // Temporary Image reference
	
	final private transient int BKMIN_X = 1000, BKMAX_X = 10000; // Min and Max of background
	public int bk_x = 8000; 			   // background x and y coordinates
	public int bk_y = 535;
	public int rz_x = 600; 		 	   // character x and y coordinates
	public int rz_y = 615;
	private int goo_x[] = new int[10];				   // enemy x coordinates
	private int goo_y[] = new int [10];				   // enemy y coordinate
	private int wall_x[] = new int[15];				   // to keep track of x axis
	private int wall_y[] = new int[15]; 			   // random y axis of wall
	private int run = 1; 							   // variable for changing images
	int point_x[] = new int[20], point_y[] = new int[20]; // Point coordinates
	int cash_x[] = new int[10], cash_y[] = new int[10]; // Cash coordinates
	
	int mus = 0; // For music
	int tempPoin, tempLives, tempCash; // For changing the mentioned
	int count = 0 ; // to fall down the mushroom after specific length
	
	int mystery_x, mystery_y; 		//mystery box x and y coord
	static int direction = 0; 		// 0=still 1=up , 2=right , 3=left , 4=down
	private int goombalives[] = new int[10];					   // For enemy lives
	public int rzlives = 5; // Character lives
	
	static boolean moveableRight = true; // variable for collision detection
	static boolean moveableLeft  = true;
	static boolean moveableDown  = false;
	
	private boolean mysteryItemVisible = false;		   // Visibility of item
	private boolean pointTaken[][] 	   = new boolean[20][5]; // For removing points
	private boolean cashTaken [] 	   = new boolean[10];	// // For removing cash
	private boolean goombadie [] 	   = new boolean[10];     // Visibility of enemy
	private boolean goombaright[] 	   = new boolean[10];   // For movement of enemy
	private boolean goombadown [] 	   = new boolean[10]; // For goomba dying animation
	private boolean jumpright 	= true;				   // For direction of jump
	private boolean mysteryItemtaken = false;
	
	static boolean jump = false;					   // For jump
	
	public String poin = "0", lives = "5", cash = "0"; 		// to draw cash and lives
	
	private transient Random rand = new Random(); //for randomising the components
	private transient Timer time;
	
	int x = 0, z = 1000; // For character animation when colliding with goomba
	static int notify = 0; // For ending the game
	
	transient AudioStream rza; // For sounds
	
	GamePanel()
	{
	   
		time = new Timer(25,this); // starting a timer and passing the actionlistener for the running animation
		time.start(); //starting 
		
		setPos_and_Intialize(); //setting position of wall and points and initializing their arrays
		
		addKeyListener(new KeyAdapter() // Movement of razmazio
		{	
			public void keyPressed(KeyEvent kp)
			{
				
				if (kp.getKeyCode() == KeyEvent.VK_RIGHT & moveableRight == true)
				{
					direction = 2; //right
				}
				if ((kp.getKeyCode() == KeyEvent.VK_LEFT) & moveableLeft == true)
				{ 
					direction = 3; //left
				}
				if (kp.getKeyCode() == KeyEvent.VK_SPACE)
				{
					if(jump == false & rz_y == 615) //if character standing of platform
					{
							jump = true;
							moveableDown =true;
							if(direction == 2)
								jumpright = true;
							if(direction == 3)
								jumpright = false;
					}	
				}
			} // end keyPressed

			public void keyReleased(KeyEvent kr) 
			{
				if(direction == 2)
					obj = rz_still_right;   //if direction is right
				if(direction == 3)
					obj = rz_still_left; 	// if direction is left
		
				direction = 0; 				// set still image
			}
		});// end anonymous class and KeyListener
	}//end constructor
	
	///////////////////////////////		TIMED ACTION LISTENER 		\\\\\\\\\\\\\\\\\\\\\\\
	public void actionPerformed(ActionEvent e)
	{	
		if(direction == 2)
			right();
		if(direction == 3)
			left();	
		
	//	repaint(); //repaint after 30ms
	}
	
	////////////////////////////////////	PAINT FUNCTION		\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public void paintComponent(Graphics g)
	{
		mus++;
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		requestFocus(); //get focus after changing card
		setFocusable(true);
		
		// For starting music
		if(mus == 1)
			music();
		if(RzFrame.sound.getState() == false && mus == 1)
			mus = 0;
 		
		// setting background points and cash in the game
		setBackground(g2d);
		setText(g2d);
		setPipes(g2d);
		setWall(g2d);
		setEnemy(g2d);
		setPoints(g2d);
		setCash(g2d);
		
		//checking jump collision and enemy death
		Jump();
		detectCollision();
		goombaDie(g2d);
		
		//testingCode(g2d); // just to visualize the exact positions of objects
			
		if(mysteryItemVisible == true)
			openMysteryBox(g2d); //open mysterybox animation if razmazio intersects

		if(rz_y == 615 & direction!=3 & direction != 2 ) // to turn razmazio in normal still state after jump
		{
			if(obj == rz_jump_left)
				obj = rz_still_left;
			if(obj == rz_jump_right)
				obj = rz_still_right;
		}
		
		if(rzlives == 0) // When character dies
		{
			notify = 3; // Third condition in overpanel
			AudioPlayer.player.stop(rza);
			rzdie(); // Dying sound
		
			RzFrame.cl.show(RzFrame.cards, "OverPanel"); // Changing panel
			//rdb.writeRecord(ProfilePanel.name, Integer.parseInt(poin));
		}
		else
		{
			if(z <= 200 && z >= 0 ) // For animation when character collides with goomba
			{
				if(x == 1) // From left
				{
					bk_x -= 8;
					g2d.drawImage(obj, rz_x - 8, rz_y, this);
					z -= 8;
				}
				if(x == 2) // From right
				{
					bk_x += 8;
					g2d.drawImage(obj, rz_x + 8, rz_y, this);
					z += 8;
				}
			}
			if(!(z <= 200 && z >= 0)) // For normal movement
			{
				x = 0;
				g2d.drawImage(obj, rz_x, rz_y, this); //Drawing the character image
			}
		}
		repaint();
	} // end paintComponent
	
	void testingCode(Graphics2D g2d)
	{	
		/*******************Testing code ******************************/
		{
		Rectangle r_pipe1  = new Rectangle(1692 - bk_x, 585, 130, 300); // pipe1
		Rectangle r_pipe2  = new Rectangle(7150 - bk_x, 585, 130, 300); // pipe1
		Rectangle rz 	   = new Rectangle(rz_x, rz_y, 40, 60); //character
		Rectangle rz_goo   = new Rectangle(rz_x + 5, rz_y + 60, 25, 3); // for character enemy collision from above
		Rectangle goo[]	   = new Rectangle[10]; // enemy
		Rectangle goo_rz[] = new Rectangle[10]; // for character enemy collision from above
		Rectangle p[] 	   = new Rectangle[10]; // points
		Rectangle mush 	   = new Rectangle(mystery_x, mystery_y, 80, 70);
		Rectangle end = new Rectangle(9800 - bk_x, 0, 3, 768);
		Rectangle gooleft[] = new Rectangle[10];
		Rectangle gooright[] = new Rectangle[10];
		
		
		
		for(int i = 0; i < 10; i++)
		{
			p[i]	= new Rectangle(point_x[i]-bk_x,point_y[i], 50, 50); 
			goo[i] = new Rectangle(goo_x[i] - bk_x, goo_y[i], 59, 73); // enemy
			goo_rz[i] = new Rectangle(goo_x[i] - bk_x, goo_y[i] - 3, 59, 3); // for character enemy collision from above
			gooleft[i] = new Rectangle((goo_x[i] - 2) - bk_x, goo_y[i], 2, 73);
			gooright[i] = new Rectangle((goo_x[i] + 61) - bk_x, goo_y[i], 2, 73);
			
			g2d.draw(p[i]);
		}
		
		g2d.draw(end);
		g2d.setColor(Color.RED);
		g2d.draw(r_pipe1);
		g2d.draw(r_pipe2);
		g2d.draw(rz);
		g2d.draw(rz_goo);
		g2d.draw(mush);
		
		for(int i = 0; i < 10; i++)
		{
			g2d.draw(goo[i]);
			g2d.draw(goo_rz[i]);
			g2d.draw(gooleft[i]);
			g2d.draw(gooright[i]);
		}
		}
		/*********************************************/	
	}
	
	/////////////////////////////////	DIRECTION CONDITIONS 	\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	void Jump() // Jump mechanism
	{
		
		if(moveableDown == true)
		{
			if(jump == true & rz_y>=450) // For upward motion during jump
			{
				if(jumpright == true)
					obj = rz_jump_right;
				else
					obj = rz_jump_left;
				
				rz_y-=4;
				if(rz_y <= 450)
					jump = false;	
			}
			if(jump == false & rz_y<615) // For downward motion during jump
			{
				if(jumpright == true)
					obj = rz_jump_right;
				else
					obj = rz_jump_left;
				rz_y+=4;
			}		
		}
	
	}//end up

	void left()
	{
		if(moveableLeft == true & bk_x > BKMIN_X)
		{
			bk_x -=8; // decrease xcoord while moving left
			
			if(mysteryItemVisible == true)
				mystery_x +=8; //moving mystery item
				
			if (run % 3 == 0 | run % 5 == 0)
				obj = rz_still_left; //set image
			else
				obj = rz_walk_left2;
			run++;
		}//end if
	}//end left

	void right()
	{
		if(moveableRight == true & bk_x < BKMAX_X-800)
			{
				bk_x +=8; //increasing xcoord while moving right
			
				if(mysteryItemVisible == true)
					mystery_x -=8; // moving mystery item
				
				if (run % 3 == 0 | run % 5 == 0)
					obj = rz_still_right;
				else
					obj = rz_walk_right2;
				run++;
			}//end if
	}//end right
	
	////////////////////////////////////////	SETTER FUNCTIONS	\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	void setBackground(Graphics g2d)
	{
		g2d.drawImage(rz_background, 700 - bk_x, 0, null); // Drawing background relative to character
	}//end setBackground
	
	static void setRazmazioFont(Graphics g2d)
	{
		Font rzFont = null;
		File rzfontfile = new File("fonts\\SuperMario256.ttf");
		
		try
		{
			rzFont = Font.createFont(Font.TRUETYPE_FONT, rzfontfile).deriveFont(Font.PLAIN, 40f);
		}
		catch (FontFormatException e) {} catch (IOException e) {}
			
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(rzFont);
		g2d.setFont(rzFont);
	
	}//end setFont
	
	void setText(Graphics g2d) //Arranging points and lives labels at proper positions on the top
	{
		setRazmazioFont(g2d);
		g2d.setColor(Color.RED); 
		g2d.drawString("POINTS: ",getX(),50);
		g2d.drawString(poin ,180,50);
		
		g2d.setColor(new Color(0, 104, 55));
		g2d.drawString("CASH: ", 300, 50);
		g2d.drawString(cash, 480, 50);
		
		g2d.setColor(Color.YELLOW);
		g2d.drawString("LIVES: ", 600, 50);
		g2d.drawString(lives, 780, 50);
		
		g2d.setColor(Color.BLUE);
	}
	
	void setPipes(Graphics g2d)
	{
		g2d.drawImage(pipe, 1692 - bk_x, bk_y + 45, null); // first pipe
		g2d.drawImage(pipe, 7150 - bk_x, bk_y + 45, null); // second pipe
	}//end setPipes
	
	void setWall(Graphics g2d)
	{
		for(int i = 0;i<16000;i+=100) // setting wall at random ycoord but fixed x coord 2000 pixels apart
		{
			if(i%2000 == 0)
			{
				wall_x[i/2000] = i-bk_x; //saved for collision detection
				
				g2d.drawImage(wall,wall_x[i/2000],wall_y[i/2000],null); // dividing by 2000 to get the index
				g2d.drawImage(mysterybox[i/2000],wall_x[i/2000]+120,wall_y[i/2000],null);
				
				//Rectangle myr = new Rectangle(wall_x[i/2000]+120,wall_y[i/2000],80,80);
				g2d.setColor(Color.RED);
				 g2d.drawRect(wall_x[i/2000]+120,wall_y[i/2000],80,80);
				
			}//end if
		}//end for
	}//end setWall
	
	void setEnemy(Graphics g2d)
	{
		for(int i = 0; i < 10; i++)
		{
			if(goombadie[i] == false) // Checking for death condition
			{
				if(goombaright[i] == true)
					g2d.drawImage(goomba[i], (goo_x[i]++) - bk_x, goo_y[i], null); // Move right
				else
					g2d.drawImage(goomba[i], (goo_x[i]--) - bk_x, goo_y[i], null); //Move left
			}
			if(goombadie[i] == true)
			{
				g2d.drawImage(goomba[i], goo_x[i] - bk_x, goo_y[i], null); // Still while dying
			}
		}
	} // end setEnemy
	
	void setPos_and_Intialize()
	{
		for(int i = 0;i<8;i++)
			mysterybox[i]= new ImageIcon("razmazio\\mysterybox.png").getImage(); //intializing mysterybox array
		
		for(int i = 0; i < wall_y.length ;i++)
			wall_y[i] = rand.nextInt(250)+275; //randomizing ycoord of wall from 275 to 520
		
		for(int i = 0; i < 10; i++)
		{
			goomba[i]= new ImageIcon("razmazio\\Goomba.png").getImage(); // Creating enemy
			goombadie[i] = false; // Enemy is initialized as living
			goombadown[i] = false;
			goombalives[i] = 2;
			if(i%2 == 0) // Half enemies move left and half move right
				goombaright[i] = true;
			else
				goombaright[i] = false;
			goo_y[i] = 605;
			cashys[i] = cashy;
			cash_x[i] = rand.nextInt(9000) + 1000; // cash randomization
			cash_y[i] = rand.nextInt(120) + 480;
		}
		
		for(int i = 0; i < 5; i++)
		{
			goo_x[i] = rand.nextInt(8000) + 1000; // randomizing x coordinate of enemy
		}
		
		for(int i = 5; i < 10; i++)
		{
			goo_x[i] = rand.nextInt(10000) + 8000; // randomizing x coordinate of enemy
		}
		
		for(int i = 0; i < 20; i++)
			for(int j = 0; j<5;j++)
				points[i][j] = point;// Initializing points
		
		for(int i = 0; i < 20; i++)
		{
			point_x[i] = rand.nextInt(i+1)+(i+1)*1000; //randomizing xcoord of points
			point_y[i] = rand.nextInt(120)+480; // randomizing ycoord
		}
	}// end setPositions
	
	void setPoints(Graphics g2d)
	{	
		int next = 0; // for next point in series
		for(int i = 0; i < 20; i++)
		{
			next = 0; //resetting next for next set of points
			for(int j = 0; j < 5; j++)
			{
				g2d.drawImage(points[i][j], (point_x[i]+next)-bk_x, point_y[i], null);
				next += 50;//one point width is 40px
			}
		}
	}//end setPoints
	
	void setCash(Graphics2D g2d)
	{
		for(int i = 0; i < 10; i++)
			g2d.drawImage(cashys[i], cash_x[i] - bk_x, cash_y[i], null);
	}
	
    //////////////////////////////////	   SOUND FUNCTIONS		\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	void music() // game music
	{
		if(RzFrame.sound.getState() == true)
		{
			AudioData lm;
			ContinuousAudioDataStream loop = null;
			try
			{
				InputStream test = new FileInputStream("sounds\\mario.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
				lm = rza.getData();
				loop = new ContinuousAudioDataStream(lm);
				AudioPlayer.player.start(loop);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void coins()
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_coin.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void goomba() // striking goomba from above
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_stomp.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void rzdie() // Character dies
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_mariodie.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void blockBreak()
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_breakblock.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void gameover() // Player loses
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_gameover.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void lifeUp()
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_powerup.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void hit() // Character hits enemy from side
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_bump.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	void clear() // player wins
	{
		if(RzFrame.sound.getState() == true)
		{
			try
			{
				InputStream test = new FileInputStream("sounds\\smb_stage_clear.wav");
				rza = new AudioStream(test);
				AudioPlayer.player.start(rza);
			}
			catch (Exception e) {}
		}
	}//end music
	
	/////////////////////////////////////	   COLLISION DETECTION		\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	void detectCollision()
	{
		Rectangle r_pipe1 = new Rectangle(1692 - bk_x, 585, 130, 300); // pipe1
		Rectangle r_pipe2 = new Rectangle(7150 - bk_x, 585, 130, 300); // pipe2
		Rectangle rz = new Rectangle(rz_x, rz_y, 40, 60); //character
		Rectangle rz_goo = new Rectangle(rz_x + 5, rz_y + 60, 25, 3); // for character enemy collision from above
		Rectangle goo[] = new Rectangle[10]; // For enemy
		Rectangle goo_rz[] = new Rectangle[10]; // For collision from above
		Rectangle gooleft[] = new Rectangle[10]; // For left collision with goomba
		Rectangle gooright[] = new Rectangle[10]; // For right collision with goomba
		Rectangle r_wall[] = new Rectangle[wall_x.length]; //wall
		Rectangle r_mbox[] = new Rectangle[mysterybox.length]; // mystery box
		Rectangle r_cash[] = new Rectangle[10]; // cash
		Rectangle mush = new Rectangle(mystery_x, mystery_y, 80, 80); // mushroom
		Rectangle end = new Rectangle(9800 - bk_x, 0, 3, 768); // game ending point
		
		detectPointsCollision(rz);
		detectGoombaCollision(rz, rz_goo, goo, goo_rz, r_pipe1, r_pipe2, gooleft, gooright);
		detectWallCollision(rz, r_wall, r_mbox);
		detectPipe_MushCollision(rz, rz_goo, r_pipe1, r_pipe2, mush);
		detectCashCollision(rz, r_cash);
		detectEnd(rz, end);
	}//end detectCollision
	
	void detectPointsCollision(Rectangle rz) throws ArrayIndexOutOfBoundsException
	{	
		Rectangle p[][] = new Rectangle[20][5];
		for(int i = 0, next; i < 20; i++)
		{
			next = 0; //resetting for next set of points
			for(int j = 0; j < 5; j++)
			{
				p[i][j]	= new Rectangle((point_x[i]+next)-bk_x, point_y[i], 50, 50); //drawing rectangles on points
				
				if(rz.intersects(p[i][j]) & pointTaken[i][j]==false)
				{
					coins();
					pointTaken[i][j] = true;
					points[i][j] = empty; // removing point
					tempPoin = Integer.parseInt(poin)+10; //converting cash to int and storing in temp
					poin = Integer.toString(tempPoin); //storing temp in cash and converting to string
				}
				next+=50; // for next point in series
			}//end columns for
		}//end rows for
	}//end detectPointsCollision
	
	void detectGoombaCollision(Rectangle rz, Rectangle rz_goo, Rectangle[] goo, Rectangle[] goo_rz, Rectangle r_pipe1, Rectangle r_pipe2, Rectangle[] gooleft, Rectangle[] gooright)
	{
		for(int i = 0; i < 10; i++)
		{
				goo[i] = new Rectangle(goo_x[i] - bk_x, goo_y[i], 59, 73); // enemy
				goo_rz[i] = new Rectangle(goo_x[i] - bk_x, goo_y[i] - 3, 59, 3); // for character enemy collision from above
		}
		
		for(int i = 0; i < 10; i++) // For collision from above
		{
			if(rz_goo.intersects(goo_rz[i]) & (obj == rz_jump_right | obj == rz_jump_left))
			{
				jump = true;
				Jump(); // Jumping when colliding
				goombalives[i]--; // Decreasing individual enemy life
				goomba(); // sound
				tempPoin = Integer.parseInt(poin) + 10; //converting points to int and storing in temp
				poin = Integer.toString(tempPoin); //storing temp in points and converting to string
				
			}
		}
		
		for(int i = 0; i < 10; i++)
			if(goombalives[i] == 0)
				goombadie[i] = true; // Enemy dies
		
		for(int i = 0; i < 10; i++)
			if(goo_x[i] < BKMIN_X) // Left boundary limit for goomba movement
				if(goombaright[i] == false)
					goombaright[i] = true;
		
		for(int i = 0; i < 10; i++)
			if(goo_x[i] > BKMAX_X) // Right boundary limit for goomba movement
				if(goombaright[i] == true)
					goombaright[i] = false;
		
		for(int i = 0, j = 0; i < 10; i++) // For collision of enemy with pipes
			if(goo[i].intersects(r_pipe1) | goo[i].intersects(r_pipe2))
			{
				if(goombaright[i] == false)
				{
					goombaright[i] = true;
					j = 1;
				}
				if(goombaright[i] == true && j != 1)
					goombaright[i] = false;
				j = 0;
			}
		
		for(int j = 0; j < 10; j++) //For goomba collisions between themselves 
			for(int k = j+1; k < 10; k++)
			{
				if(goo[j].intersects(goo[k]))
				{
					if(goombadie[j] == false || goombadie[k] == false)
					{
						if(j % 2 == 0)
						{
							goombaright[j] = false;
							goombaright[k] = true;
						}
						else
						{
							goombaright[k] = false;
							goombaright[j] = true;
						}
					}
				}
			}
		for(int i = 0; i < 10; i++)
		{
			gooleft[i] = new Rectangle((goo_x[i] - 2) - bk_x, goo_y[i], 2, 73);
			gooright[i] = new Rectangle((goo_x[i] + 61) - bk_x, goo_y[i], 2, 73);
			if(rz.intersects(gooleft[i])) // Character hitting enemy from left
			{
				x = 1; // for animation
				z = 100; // for animation
				hit(); // sound
				rzlives--; // decreasing character lives
				tempLives = Integer.parseInt(lives) - 1; //converting lives to int and storing in temp
				lives = Integer.toString(tempLives); //storing temp in lives and converting to string
			}
			if(rz.intersects(gooright[i])) // Character hitting enemy from right
			{
				x = 2; // for animation
				z = 100; // for animation
				hit(); // sound
				rzlives--; // decreasing character lives
				tempLives = Integer.parseInt(lives) - 1; //converting cash to int and storing in temp
				lives = Integer.toString(tempLives); //storing temp in cash and converting to string
			}
		}
	}
	
	void detectWallCollision(Rectangle rz, Rectangle[] r_wall, Rectangle[] r_mbox)
	{
		for(int i = 0 ;i<wall_x.length;i++) // for many walls
		{ 
			r_wall[i]  = new Rectangle(wall_x[i], wall_y[i],300,81);
			r_mbox[i]  = new Rectangle(wall_x[i]+120,wall_y[i],80,80);			
			
			 if (rz.intersects(r_mbox[i]))//when intersects with mystery box
			{
				blockBreak();
				mysteryItemtaken =false;
				
				jump = false;
				mysteryItemVisible = true; // a mushroom pops out of the box
				mystery_x = (int)r_mbox[i].getX(); //intersection with mysteryItem
				mystery_y = (int)r_mbox[i].getY()-80; 
				count = 0; //setting count to 0 for next box
			}
			
			else if(rz.intersects(r_wall[i])) //if intersects with wall
				jump = false;
			
			else
			{
			 moveableRight = true; moveableLeft= true; moveableDown = true;		
			} 			
		}//end for	
	}
	
	void detectPipe_MushCollision(Rectangle rz, Rectangle rz_goo, Rectangle r_pipe1, Rectangle r_pipe2, Rectangle mush)
	{
		if (( rz_goo.intersects(r_pipe1) | rz_goo.intersects(r_pipe2))
				& rz_y<=535) //checking intersection with wall and pipe when lower rectangle intersects
		{
		
			moveableRight = true;
			moveableLeft = true;
			moveableDown =false;
		}
	
		else if ((rz.intersects(r_pipe1) | rz.intersects(r_pipe2)) & 
				(obj == rz_still_right | obj == rz_jump_right) & rz_y>535) // when character is right
		{
			moveableRight = false;
			moveableLeft = true;
			moveableDown =true;
		}
		else if ((rz.intersects(r_pipe1) | rz.intersects(r_pipe2)) & 
				(obj == rz_still_left| obj == rz_jump_left) & rz_y>535)
		{
			moveableRight = true;
			moveableLeft = false;
			moveableDown =true;
		}
		else if(rz.intersects(mush)) // taking mushrooms
		{
			int temp;
			int mys = rand.nextInt(10); // mystery variable for random generation
			
			
			if(mys >=0 & mys<5) // this mushroom will give life
			{
				rzlives++;
				temp = Integer.parseInt(lives);
				temp++;
				lives = Integer.toString(temp);
				lifeUp();
			}
			if(mys>= 5 & mys<=10) // this mushroom will take life
			{
				rzlives--;
				temp = Integer.parseInt(lives);
				temp--;
				lives = Integer.toString(temp);
				rzdie();
			}
			mysteryItemtaken = true;
			mystery_x = -1000;
		}
	}
	
	void detectCashCollision(Rectangle rz, Rectangle[] r_cash)
	{
		for(int i = 0; i < 10; i++)
		{
			r_cash[i] = new Rectangle(cash_x[i] - bk_x, cash_y[i], 50, 50);
			if(rz.intersects(r_cash[i]) & cashTaken[i] == false) // taking cash
			{
				cashTaken[i] = true; // cash taken
				cashys[i] = empty; // removing point
				tempCash = Integer.parseInt(cash) + 100; //converting cash to int and storing in temp
				cash = Integer.toString(tempCash); //storing temp in cash and converting to string
			}
		}
	}
	
	void detectEnd(Rectangle rz, Rectangle end)
	{
		if(rz.intersects(end)) // character reaches end
		{
	
			AudioPlayer.player.stop(rza);
			if(Integer.parseInt(cash) >= 900) // if cash greater than 900, player wins
			{
				notify = 1; // first condition of overpanel
				clear();
			}
			if(Integer.parseInt(cash) < 900) // if cash greater than 900, player loses
			{
				notify = 2; // second condition of overpanel
				gameover();
			}
			FileOutputStream fos;
			try
			{
				fos = new FileOutputStream(ProfilePanel.ff);
				fos.write(tempPoin); // saving points
				fos.close();
				
				HighPanel hp = new HighPanel();
				hp.SaveHighScore(ProfilePanel.name, tempPoin);
				
				RamzaioDB.registerDriver(); //registering driver
				RamzaioDB.connectToDatabase(); //connecting to db
				RamzaioDB.createStatement(); //creating statement
				RamzaioDB.writeRecord(ProfilePanel.name, tempPoin); // passing the value to worldRecords
				RamzaioDB.closeAll();
			}
			catch (Exception e){}
			RzFrame.cl.show(RzFrame.cards, "OverPanel"); // Showing appropiate overpanel
		}
	}
	
	void openMysteryBox(Graphics g2d) // mystery box smashed
	{
		
		Image img;
		img = mushroom2;
		if(mysteryItemtaken == true)
		{
			img = empty;
		}
		else
		{
			// mushroom animation
			if(count <=175)
			{
				count++;
				g2d.drawImage(img,mystery_x++ ,mystery_y,null);
			}
			if(count >= 175)
				g2d.drawImage(img, mystery_x, mystery_y++, null);
			if(mystery_y >= 595)
			{
				count = 0;
				g2d.drawImage(img, mystery_x++, mystery_y, null);
			}
		}
	}
	
	////////////////////////////////////ANIMATIONS 	\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	void goombaDie(Graphics2D g2d) // Animation for a dying goomba
	{
		for(int i = 0; i < 10; i++)
		{
			if(goombadie[i] == true) // if dies
			{
				g2d.drawImage(goomba[i], goo_x[i], goo_y[i]--, null); // move up
				if(goo_y[i] == 580)
					goombadown[i] = true;
			}
			if(goombadown[i] == true)
				g2d.drawImage(goomba[i], goo_x[i], goo_y[i]++, null); // moving down
		}
		for(int i = 0; i < 10; i++)
			if(goombadown[i] == true)
				g2d.drawImage(goomba[i], goo_x[i], goo_y[i]++, null);
	}
}// end class