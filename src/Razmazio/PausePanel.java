package Razmazio;

import java.awt.*;
import java.awt.Image;
import javax.swing.*;

public class PausePanel extends JPanel
{	
	Image pause = new ImageIcon("razmazio\\pauseGame.png").getImage();
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(pause,0,0,null); //when game pauses
	}
}
