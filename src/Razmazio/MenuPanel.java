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
import java.awt.event.*;
import javax.swing.*;

class MenuPanel extends JPanel
{
	int ng_y = 770, lg_y = 770, hs_y = 770, ex_y = 770, timer = 0; // Y coordinates for 4 buttons. Timer for animation
	
	// Creating buttons
	JButton jb1 = new JButton("New Game");
	JButton jb2 = new JButton("Load Game");
	JButton jb3 = new JButton("Highscore");
	JButton jb4 = new JButton("Exit");
	// Creating animation images
	protected Image mario1  = new ImageIcon("backgrounds\\startmario1.png").getImage();
	protected Image mario2  = new ImageIcon("backgrounds\\startmario2.png").getImage();
	protected Image mario3  = new ImageIcon("backgrounds\\startmario3.png").getImage();
	protected Image mario4  = new ImageIcon("backgrounds\\startmario4.png").getImage();
	protected Image mario5  = new ImageIcon("backgrounds\\startmario5.png").getImage();
	protected Image mario6  = new ImageIcon("backgrounds\\startmario6.png").getImage();
	protected Image mario7  = new ImageIcon("backgrounds\\startmario7.png").getImage();
	protected Image mario8  = new ImageIcon("backgrounds\\startmario8.png").getImage();
	protected Image mario9  = new ImageIcon("backgrounds\\startmario9.png").getImage();
	protected Image mario10 = new ImageIcon("backgrounds\\startmario10.png").getImage();
	protected Image mario11 = new ImageIcon("backgrounds\\startmario11.png").getImage();
	protected Image mario12 = new ImageIcon("backgrounds\\startmario12.png").getImage();
	// Creating image icons for buttons
	protected ImageIcon ngbutton = new ImageIcon("buttons\\New Game.png");
	protected ImageIcon lgbutton = new ImageIcon("buttons\\Load Game.png");
	protected ImageIcon hsbutton = new ImageIcon("buttons\\Highscore.png");
	protected ImageIcon exbutton = new ImageIcon("buttons\\Exit.png");
	
	int a = 0;
	
	ProfilePanel pp = new ProfilePanel();
	
	MenuPanel()
	{
		setLayout(null); // Removing layout for button animation
		// Setting images on buttons
		jb1.setIcon(ngbutton);
		jb2.setIcon(lgbutton);
		jb3.setIcon(hsbutton);
		jb4.setIcon(exbutton);
		
		// Adding mouse listener to buttons
		jb1.addMouseListener(new mouseClick());
		jb2.addMouseListener(new mouseClick());
		jb3.addMouseListener(new mouseClick());
		jb4.addMouseListener(new mouseClick());
		
		add(jb1);
		add(jb2);
		add(jb3);
		add(jb4);
	}
	
	public class mouseClick extends MouseAdapter{ // Class for event handling
		public void mouseClicked(MouseEvent mc){
			if(mc.getSource()  == jb1)
				RzFrame.cl.show(RzFrame.cards, "ProfilePanel");
			if(mc.getSource()  == jb2)
				RzFrame.cl.show(RzFrame.cards, "LoadPanel");
			if(mc.getSource()  == jb3)
				RzFrame.cl.show(RzFrame.cards, "HighPanel");
			if(mc.getSource() == jb4)
				System.exit(0); // Exit game if exit button is clicked
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		a++;
		// Drawing images to make the animation
		if(a == 1 || a == 2 || a == 3 || a == 4) 
			g2d.drawImage(mario12, 0, 0, null);
		if(a == 5 || a == 6 || a == 7 || a == 8) 
			g2d.drawImage(mario11, 0, 0, null);
		if(a == 9 || a == 10 || a == 11 || a == 12) 
			g2d.drawImage(mario10, 0, 0, null);
		if(a == 13 || a == 14 || a == 15 || a == 16) 
			g2d.drawImage(mario9, 0, 0, null);
		if(a == 17 || a == 18 || a == 19 || a == 20) 
			g2d.drawImage(mario8, 0, 0, null);
		if(a == 21 || a == 22 || a == 23 || a == 24) 
			g2d.drawImage(mario7, 0, 0, null);
		if(a == 25 || a == 26 || a == 27 || a == 28) 
			g2d.drawImage(mario6, 0, 0, null);
		if(a == 29 || a == 30 || a == 31 || a == 32) 
			g2d.drawImage(mario5, 0, 0, null);
		if(a == 33 || a == 34 || a == 35 || a == 36) 
			g2d.drawImage(mario4, 0, 0, null);
		if(a == 37 || a == 38 || a == 39 || a == 40)
			g2d.drawImage(mario3, 0, 0, null);
		if(a == 41 || a == 42 || a == 43 || a == 44) 
			g2d.drawImage(mario2, 0, 0, null);
		if(a > 45)
		{
			g2d.drawImage(mario1, 0, 0, null);
			timer = 1;
		}
		
		// Decreasing the y coordinates of buttons for animation, then stopping them and increasing the timer for next animation
		if(ng_y == 564)
		{
			jb1.setBounds(100, ng_y, 240, 82);
			timer = 2;
		}
		else
			jb1.setBounds(100, ng_y -= 2, 240, 82);
		if(timer == 2)
		{
			if(lg_y == 564)
			{
				jb2.setBounds(400, lg_y, 240, 82);
				timer = 3;
			}
			else
				jb2.setBounds(400, lg_y -= 2, 240, 82);
		}
		if(timer == 3)
		{
			if(hs_y == 564)
			{
				jb3.setBounds(700, hs_y, 240, 82);
				timer = 4;
			}
			else
				jb3.setBounds(700, hs_y -= 2, 240, 82);
		}
		if(timer == 4)
		{
			if(ex_y == 564)
				jb4.setBounds(1000, ex_y, 240, 82);
			else
				jb4.setBounds(1000, ex_y -= 2, 240, 82);
		}

		repaint();
	}
}