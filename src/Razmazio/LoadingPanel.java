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
import java.awt.geom.*;
import javax.swing.*;

class LoadingPanel extends JPanel
{
	// Variables for loading animation
	int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g1 = 0, h = 0, i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0, q = 0, r = 0, s = 0, t = 0;
	
	// Creating background and loading image
	protected Image startbkg = new ImageIcon("backgrounds\\bkg.png").getImage();
	protected Image loading = new ImageIcon("razmazio\\Loading.png").getImage();
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Drawing images
		g2d.drawImage(startbkg, 0, 0, null);
		g2d.drawImage(loading, 530, 300, null);
		
		// Setting colours and drawing circles in front of other by increasing the next variable. The previous one remains there and next circles are drawn.
		// The last condition draws the first circle and so on.
		if(t == 1)
		{
			g2d.setColor(Color.BLUE);
			g2d.fill(new Ellipse2D.Double(1340, 400, 30, 30));
			RzFrame.cl.show(RzFrame.cards, "GamePanel"); // Showing game panel after the last circle is drawn.
		}
		if(s == 1)
		{
			g2d.setColor(Color.BLUE);
			g2d.fill(new Ellipse2D.Double(1270, 400, 30, 30));
			t = 1;
		}
		if(r == 1)
		{
			g2d.setColor(Color.BLUE);
			g2d.fill(new Ellipse2D.Double(1200, 400, 30, 30));
			s = 1;
		}
		if(q == 1)
		{
			g2d.setColor(Color.BLUE);
			g2d.fill(new Ellipse2D.Double(1130, 400, 30, 30));
			r = 1;
		}
		if(p == 1)
		{
			g2d.setColor(Color.BLUE);
			g2d.fill(new Ellipse2D.Double(1060, 400, 30, 30));
			q = 1;
		}
		if(o == 1)
		{
			g2d.setColor(Color.RED);
			g2d.fill(new Ellipse2D.Double(990, 400, 30, 30));
			p = 1;
		}
		if(n == 1)
		{
			g2d.setColor(Color.RED);
			g2d.fill(new Ellipse2D.Double(920, 400, 30, 30));
			o = 1;
		}
		if(m == 1)
		{
			g2d.setColor(Color.RED);
			g2d.fill(new Ellipse2D.Double(850, 400, 30, 30));
			n = 1;
		}
		if(l == 1)
		{
			g2d.setColor(Color.RED);
			g2d.fill(new Ellipse2D.Double(780, 400, 30, 30));
			m = 1;
		}
		if(k == 1)
		{
			g2d.setColor(Color.RED);
			g2d.fill(new Ellipse2D.Double(710, 400, 30, 30));
			l = 1;
		}
		if(j == 1)
		{
			g2d.setColor(new Color(0, 104, 55));
			g2d.fill(new Ellipse2D.Double(640, 400, 30, 30));
			k = 1;
		}
		if(i == 1)
		{
			g2d.setColor(new Color(0, 104, 55));
			g2d.fill(new Ellipse2D.Double(570, 400, 30, 30));
			j = 1;
		}
		if(h == 1)
		{
			g2d.setColor(new Color(0, 104, 55));
			g2d.fill(new Ellipse2D.Double(500, 400, 30, 30));
			i = 1;
		}
		if(g1 == 1)
		{
			g2d.setColor(new Color(0, 104, 55));
			g2d.fill(new Ellipse2D.Double(430, 400, 30, 30));
			h = 1;
		}
		if(f == 1)
		{
			g2d.setColor(new Color(0, 104, 55));
			g2d.fill(new Ellipse2D.Double(360, 400, 30, 30));
			g1 = 1;
		}
		if(e == 1)
		{
			g2d.setColor(Color.YELLOW);
			g2d.fill(new Ellipse2D.Double(290, 400, 30, 30));
			f = 1;
		}
		if(d == 1)
		{
			g2d.setColor(Color.YELLOW);
			g2d.fill(new Ellipse2D.Double(220, 400, 30, 30));
			e = 1;
		}
		if(c == 1)
		{
			g2d.setColor(Color.YELLOW);
			g2d.fill(new Ellipse2D.Double(150, 400, 30, 30));
			d = 1;
		}
		if(b == 1)
		{
			g2d.setColor(Color.YELLOW);
			g2d.fill(new Ellipse2D.Double(80, 400, 30, 30));
			c = 1;
		}
		if(a == 0)
		{
			g2d.setColor(Color.YELLOW);
			g2d.fill(new Ellipse2D.Double(10, 400, 30, 30));
			b = 1;
		}
		
		try
		{
			Thread.sleep(200); // Reducing the speed
		}
		catch (Exception e) {}
		repaint(10);
	}
}