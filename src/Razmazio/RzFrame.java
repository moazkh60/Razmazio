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

public class RzFrame extends JFrame
{
	static CardLayout cl = new CardLayout(); // For card layout
	// Creating panels (Static, so that the same ones can be called in other classes)
	static JPanel cards = new JPanel(); // Base panel for card layout
	static GamePanel gp = new GamePanel();
	StartPanel sp = new StartPanel();
	static MenuPanel mp = new MenuPanel();
	static LoadingPanel lp = new LoadingPanel();
	static PausePanel pp = new PausePanel();
	static ProfilePanel profP = new ProfilePanel();
	static LoadPanel loadp = new LoadPanel();
	static HighPanel hp = new HighPanel();
	static OverPanel op = new OverPanel();
	static RamzaioDB rdb = new RamzaioDB();
	static AboutPanel ap = new AboutPanel();
	
	// Creating menu items
	JMenuBar mb = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu options = new JMenu("Options");
	JMenu help = new JMenu("Help");
	JMenuItem pause = new JMenuItem("Pause");
	JMenuItem resume = new JMenuItem("Resume");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem back = new JMenuItem("Back");
	JMenuItem about = new JMenuItem("About");
	static JCheckBoxMenuItem sound = new JCheckBoxMenuItem("Sound" , true);
	
	RzFrame()
	{
		// Adding menu items
		file.add(pause);
		file.add(resume);
		file.add(save);
		file.addSeparator();
		file.add(back);
		
		pause.addActionListener(new MenuItemListener());
		resume.addActionListener(new MenuItemListener());
		save.addActionListener(new MenuItemListener());
		back.addActionListener(new MenuItemListener());
		about.addActionListener(new MenuItemListener());
		
		options.add(sound);
		help.add(about);
		mb.add(file);
		mb.add(options);
		mb.add(help);
		setJMenuBar(mb); // Setting the menu bar of frame
		cards.setLayout(cl); // Setting layout of base panel to card layout
		// Adding other cards to base card
		cards.add(sp, "StartPanel");	
		cards.add(mp, "MenuPanel");
		cards.add(lp, "LoadingPanel");
		cards.add(profP, "ProfilePanel");
		cards.add(gp, "GamePanel");
		cards.add(pp, "PausePanel");
		cards.add(loadp, "LoadPanel");
		cards.add(hp, "HighPanel");
		cards.add(op, "OverPanel");
		cards.add(rdb,"RamzaioDB");
		cards.add(ap,"AboutPanel");
		cl.show(cards, "StartPanel"); // Showing first card
		
		add(cards); // Adding base card to frame
	}
	
	void setFrame() // Setting the properties of frame
	{
		setResizable(false);
		setAlwaysOnTop(true);
		setSize(1366, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Razmazio");
		add(cards);
		setVisible(true);
	}

	public static void main (String args[]) throws Exception
	{
		RzFrame rz = new RzFrame();
		rz.setFrame(); // Settings
	}
	
	public class MenuItemListener implements ActionListener // Menubar buttons functionality
	{
		public void actionPerformed(ActionEvent ae)
		{
			if (ae.getSource() == pause)
				cl.show(cards, "PausePanel");
			if (ae.getSource() == resume)
				cl.show(cards, "GamePanel");
			if (ae.getSource() == save)
				ProfilePanel.saveGame();
			if (ae.getSource() == back)
				cl.show(cards,  "MenuPanel");
			if (ae.getSource() == about)
				cl.show(cards,  "AboutPanel");
		}
	}
}