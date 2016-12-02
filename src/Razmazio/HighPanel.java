package Razmazio;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.*;
import java.io.*;

public class HighPanel extends JPanel
{	
	protected Image bkg = new ImageIcon("backgrounds\\startmenubkg.png").getImage();
	
	JButton jb = new JButton("World Rankings");
	RamzaioDB rd = new RamzaioDB();
	
	String[] names = new String[10];
	int scores[] = new int[10];
	String fields[] = {"Name","Points" };
	String values[][] = new String[500][2];
	JTable jt = new JTable(values, fields);
	JScrollPane jsp = new JScrollPane(jt);
	File files[];
	int rows = 0;
	
	HighPanel()
	{
		setLayout(new BorderLayout());
		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.LIGHT_GRAY);
		jt.setSize(1300, 700);
		add(jsp, "Center");
		add(jb, "South");
			readHighScore();
		jb.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				readHighScore();
				repaint();
				RzFrame.cl.show(RzFrame.cards,"RamzaioDB");
			}
		});
	}//end constructor
	
	 void SaveHighScore(String name, int score){
			File ff =new File("score\\",name);
			
		try{
			RandomAccessFile raf = new RandomAccessFile(ff, "rw");
			raf.writeChars(name);
			raf.writeInt(score);  // writing points collected
			raf.close();
		}
		 catch (FileNotFoundException e) {} catch (IOException e) {e.printStackTrace();}
		
	}
	
	void readHighScore(){
		File ff = new File("score\\");
		try{
			files = ff.listFiles();
			for(int i=0;i<ff.list().length;i++){
			RandomAccessFile raf = new RandomAccessFile(files[i], "r");
			if(ff.list()!=null){
				{
					values[rows][0] +=Character.toString(raf.readChar());
					values[rows][1] =Integer.toString(raf.readInt());
					rows++;
				}
			}
		raf.close();
		repaint();
		}
		}
		 catch (FileNotFoundException e) {} catch (IOException e) {}
	}
}
