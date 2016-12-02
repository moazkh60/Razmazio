package Razmazio;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class LoadPanel extends JPanel implements MouseListener
{
	File dir = new File("profiles");
	File ff;
	String[] profiles;
	String st;
	protected ImageIcon lbutton = new ImageIcon("buttons\\Load.jpg");
	protected ImageIcon dbutton = new ImageIcon("buttons\\Delete.jpg");
	
	JTextField jtfl = new JTextField();
	JTextField jtfd = new JTextField();
	JButton jbl = new JButton("Load");
	JButton jbd = new JButton("Delete");
	byte[] b = new byte[5000];
	protected Image bkg = new ImageIcon("backgrounds\\startmenubkg.png").getImage();
	
	LoadPanel()
	{
		setLayout(null);
		jbl.setIcon(lbutton);
		jbd.setIcon(dbutton);
		
		jtfl.setBounds(100, 650, 200, 30);
		add(jtfl);
		jbl.setBounds(310, 650, 90, 30);
		add(jbl);
		jbl.addMouseListener(this);
		jtfd.setBounds(950, 650, 200, 30);
		add(jtfd);
		jbd.setBounds(1160, 650, 90, 30);
		add(jbd);
		jbd.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bkg, 0, 0, null);
		GamePanel.setRazmazioFont(g2d);
		g2d.setColor(Color.ORANGE);
		g2d.drawString("Select a profile to load/delete", 280, 50);
		profiles = dir.list();
		for(int i = 0, y = 150; i < profiles.length; i++, y += 60) // displaying all the profiles saved
		{
			st = profiles[i];
			g2d.drawString(st, 580, y);
		}
	}

	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == jbl)
		{
			String prof = jtfl.getText();
			
			ff = new File(dir, prof + ".dat"); // making a new file with the name from text field
			try
			
			{
			
				RandomAccessFile raf = new RandomAccessFile(ff,"r");
				raf.seek(0);
				RzFrame.gp.poin  = Integer.toString(raf.readInt()); // points
				RzFrame.gp.bk_x = raf.readInt(); // reading background xcoord
				RzFrame.gp.rz_x = raf.readInt(); // character xcoord
				RzFrame.gp.bk_y = raf.readInt(); // reading background ycoord
				RzFrame.gp.rz_y = raf.readInt(); // character ycoord
				RzFrame.gp.rzlives = raf.readInt();
				RzFrame.gp.cash  = Integer.toString(raf.readInt()); //cash value
				RzFrame.gp.lives = Integer.toString(raf.readInt()); // remaining lives
							
				raf.close();
				RzFrame.cl.show(RzFrame.cards, "LoadingPanel");
			}
			catch(Exception e) {}
		}
		if (me.getSource() == jbd)
		{
			String prof = jtfd.getText();
			jtfd.setText("");
			
			ff = new File(dir, prof + ".dat"); // making a file by getting directory
			try
			{
				ff.delete(); // deleting
				repaint(); //repaint after delete
			}
			catch(Exception e) {}
		}
	}
	

	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {}
}
