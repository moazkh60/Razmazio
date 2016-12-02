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
import java.awt.event.*;

class StartPanel extends JPanel
{
	int y = 400, y1 = 650, timer = 0, a = 0; // y: y coordinate of mario image, y1: y coordinate of razmazio text
											// timer: For timing the animation, a: For slowing down the enter image
	// Creating images
	protected Image startbkg = new ImageIcon("backgrounds\\startmenubkg.png").getImage();
	protected Image mario = new ImageIcon("razmazio\\Mario.png").getImage();
	protected Image razmazio = new ImageIcon("razmazio\\Razmazio.png").getImage();
	protected Image enter = new ImageIcon("razmazio\\Enter.png").getImage();
	protected Image blank = new ImageIcon("razmazio\\Blank.png").getImage(); // Blank image for making the enter image blink
	
	StartPanel()
	{
		setFocusable(true); // Focusing the panel for event handling
		addKeyListener(new KeyAdapter() // key adapter for pressing enter
		{
			public void keyPressed(KeyEvent kt)
			{
				if(kt.getKeyCode() == KeyEvent.VK_ENTER)
				{
					RzFrame.cl.show(RzFrame.cards, "MenuPanel"); // Showing menu panel when enter is pressed
				}
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		a++; // Counter for blinking condition
		g2d.drawImage(startbkg, 0, 0, null); // Drawing background
		if(y == 50)
		{
			g2d.drawImage(mario, 500, y, null); // When y reaches 50, make it still
			timer = 1; // Increasing the timer for next animation
		}
		else
			g2d.drawImage(mario, 500, y -= 2, null); // Decreasing y coordinate of mario for animation

		if(timer == 1)
		{
			if(y1 == 450)
			{
				g2d.drawImage(razmazio, 470, y1, null); // When y1 reaches 450, make it still
				timer = 2;// Increasing the timer for next animation
			}
			else
				g2d.drawImage(razmazio, 470, y1 -= 2, null); // Decreasing y coordinate of razmazio for animation
		}

		if(timer == 2 && (a % 100 == 0 || a % 100 == 1 || a % 100 == 2 || a % 100 == 3 || a % 100 == 4 || a % 100 == 5 || a % 100 == 6 || a % 100 == 7 || a % 100 == 8 || a % 100 == 9 || a % 100 == 10 || a % 100 == 11 || a % 100 == 12 || a % 100 == 13 || a % 100 == 14 || a % 100 == 15 || a % 100 == 16 || a % 100 == 17 || a % 100 == 18 || a % 100 == 19 || a % 100 == 20 || a % 100 == 21 || a % 100 == 22 || a % 100 == 23 || a % 100 == 24 || a % 100 == 25 || a % 100 == 26 || a % 100 == 27 || a % 100 == 28 || a % 100 == 29 || a % 100 == 30|| a % 100 == 31|| a % 100 == 32|| a % 100 == 33|| a % 100 == 34|| a % 100 == 35|| a % 100 == 36|| a % 100 == 37|| a % 100 == 38|| a % 100 == 39|| a % 100 == 40|| a % 100 == 41|| a % 100 == 42|| a % 100 == 43|| a % 100 == 44|| a % 100 == 45|| a % 100 == 46|| a % 100 == 47|| a % 100 == 48|| a % 100 == 49))
			g2d.drawImage(enter, 475, 550, null); // For 50 iterations, display the enter image
		if(timer == 2 && (a % 100 == 50 || a % 100 == 51 || a % 100 == 52 || a % 100 == 53 || a % 100 == 54 || a % 100 == 55 || a % 100 == 56 || a % 100 == 57 || a % 100 == 58 || a % 100 == 59 || a % 100 == 60 || a % 100 == 61 || a % 100 == 62 || a % 100 == 63 || a % 100 == 64 || a % 100 == 65 || a % 100 == 66 || a % 100 == 67 || a % 100 == 68 || a % 100 == 69 || a % 100 == 70 || a % 100 == 71 || a % 100 == 72 || a % 100 == 73 || a % 100 == 74 || a % 100 == 75 || a % 100 == 76 || a % 100 == 77 || a % 100 == 78 || a % 100 == 79 || a % 100 == 80 || a % 100 == 81 || a % 100 == 82 || a % 100 == 83 || a % 100 == 84 || a % 100 == 85 || a % 100 == 86 || a % 100 == 87 || a % 100 == 88 || a % 100 == 89 || a % 100 == 90 || a % 100 == 91 || a % 100 == 92 || a % 100 == 93 || a % 100 == 94 || a % 100 == 95 || a % 100 == 96 || a % 100 == 97 || a % 100 == 98 || a % 100 == 99))
			g2d.drawImage(blank, 475, 550, null); // For the next 50 iterations, display the blank image. This makes a blinking effect.(Sleep wasn't working on these images

		repaint(1);
	}
}