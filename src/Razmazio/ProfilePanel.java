package Razmazio;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ProfilePanel extends JPanel
{
	protected Image startbkg = new ImageIcon("backgrounds\\startmenubkg.png").getImage();
	protected ImageIcon pbutton = new ImageIcon("buttons\\Play.jpg");
	
	static File ff;

	JTextField nameField = new JTextField();
	JButton namejb = new JButton("PLAY");
	static String name;
	
	ProfilePanel()
	{
		setLayout(null);
		namejb.setIcon(pbutton);

		nameField.setBounds(470, 330, 300, 50);
		add(nameField);
		namejb.setBounds(780, 330, 130, 50);
		add(namejb);
		
		namejb.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if (me.getSource() == namejb)
				{
					name = nameField.getText(); // getting name
					if (name != "")
					{
						nameField.setText("");
						newProfile(name); // creating file
					}
				}
			} // end mouseClicked
		});// end anonymous class
	}// end constructor

	public void newProfile(String name)
	{
		ff = new File("profiles\\", name + ".dat");
		try
		{
			ff.createNewFile(); // creating profile for the name
			RzFrame.cl.show(RzFrame.cards, "LoadingPanel");
		}
		catch (IOException e) {}
	}
	
	public static void saveGame()
	{
		try
		{
			
			RandomAccessFile raf = new RandomAccessFile(ff, "rw");
			raf.writeInt(RzFrame.gp.tempPoin);  // writing points collected
			raf.writeInt(RzFrame.gp.bk_x); 		//writing the background xcoord to file
			raf.writeInt(RzFrame.gp.rz_x); 		// writing the characters xcoord to file
			raf.writeInt(RzFrame.gp.bk_y); 		//writing the background ycoord to file
			raf.writeInt(RzFrame.gp.rz_y); 		// writing the characters ycoord to file
			raf.writeInt(RzFrame.gp.rzlives);
			raf.writeInt(RzFrame.gp.tempCash);  // writing cash value
			raf.writeInt(RzFrame.gp.tempLives); // writing remaining lives
			
			raf.close(); 	
		}
		catch (IOException e) {e.printStackTrace();}
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(startbkg, 0, 0, null);
		GamePanel.setRazmazioFont(g2d);
		g2d.setColor(Color.ORANGE);
		g2d.drawString("Enter Name:", 470, 300);
		repaint();
	}// end paintComp

}
