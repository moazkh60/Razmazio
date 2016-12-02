package Razmazio;

import java.awt.*;
import javax.swing.*;

public class OverPanel extends JPanel
{
	protected Image go1 = new ImageIcon("backgrounds\\gameover1.png").getImage();
	protected Image go2 = new ImageIcon("backgrounds\\gameover2.png").getImage();
	protected Image go3 = new ImageIcon("backgrounds\\gameover3.png").getImage();
	
	protected void paintComponent(Graphics g)
	{
		if(GamePanel.notify == 1)
			g.drawImage(go1, 0, 0, null); // player wins
		if(GamePanel.notify == 2)
			g.drawImage(go2, 0, 0, null); // player loses
		if(GamePanel.notify == 3)
			g.drawImage(go3, 0, 0, null); // character dies
	}
}
