package Razmazio;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class RamzaioDB extends JPanel {
	
	String fields[] = { "Rank", "Name","Points" };
	String values[][] = new String[500][3];
	
	JTable jt = new JTable(values, fields);
	JButton jb = new JButton("Local Rankings");
	
	
	JScrollPane jsp = new JScrollPane(jt);

	static String db_url = "jdbc:mysql://localhost/";
	static String db_name = "razmaziodb";

	static final String USER = "root";
	static final String PASS = "";

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static String query; // for running the query

	int rank = 1; // to rank the player
	int rows = 0;
	int id = 0;
	
	RamzaioDB()
	{
		setLayout(new BorderLayout());
		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.LIGHT_GRAY);
		
		showAllrecords();
		jt.setSize(1300, 700);
		add(jsp, "Center");
		add(jb, "South");
		jb.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				RzFrame.cl.show(RzFrame.cards, "HighPanel");
			}
		});

	}// end constructor

	static void registerDriver() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	}

	static void connectToDatabase() throws SQLException { // establishing connection
		conn = DriverManager.getConnection(db_url + db_name, USER, PASS);
	}

	static void createStatement() throws SQLException { // creating statement
		stmt = conn.createStatement();
	}

	static void writeRecord(String name, int score) { // writing record
		query = "INSERT INTO worldranking (Name,Score) VALUES('"+name+"',"+ score +")";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {e.printStackTrace();}
		
	}

	void executeQuery(String query) throws SQLException { // executing the query
		rs = stmt.executeQuery(query);
		while (rs.next()) {

			int score = rs.getInt("Score");
			String name = rs.getString("Name");

			rows++;
					values[rows][0]= Integer.toString(rank);
					values[rows][1]= name;
					values[rows][2]= Integer.toString(score);
			
			rank++;
		}
		repaint();
		
	}
	static void closeAll() throws SQLException { // close connection statement and
											// resultset
		rs.close();
		stmt.close();
		conn.close();
	}
	
	void showAllrecords(){
		
		try {
			String sql;
			sql = "SELECT Name,Score FROM `worldranking` ORDER BY Score DESC";
			
			registerDriver();
			connectToDatabase();
			createStatement();
			executeQuery(sql);
			closeAll();
			
		} catch (SQLException se) {}
	}

}// end class

