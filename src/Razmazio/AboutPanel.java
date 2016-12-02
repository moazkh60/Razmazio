package Razmazio;

import java.awt.*;
import javax.swing.*;

public class AboutPanel extends JPanel
{
	protected Image about = new ImageIcon("backgrounds\\about.png").getImage();
	public void paintComponent(Graphics g)
	{
		g.drawImage(about, 0, 0, null); // Showing help
	}
}
