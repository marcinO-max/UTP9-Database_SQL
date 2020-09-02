package zad1;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Database extends JTable {

	String url;
	TravelData td;
	Connection connection = null;
	private Integer id = 1;
	
	public Database(String url, TravelData td) {
		this.url = url;
		this.td=td;
		
	}
	
	
	public void create() {

		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(url, "sa","");
			if(connection != null) {
				System.out.println();
				System.out.println("Connected to derby!");
			}
		} catch (SQLException e) {
			System.err.println("Connection with DB exe");
			System.exit(2);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver Class not found");
			e.printStackTrace();
		}

		try {
			connection.createStatement().execute("CREATE TABLE Oferta("
					+ "id int PRIMARY KEY, "
					+ "kraj varchar(40), "
					+ "data_wyjazdu Date, "
					+ "data_powrotu Date, "
					+ "miejsce varchar(20), "
					+ "cena varchar(20), "
					+ "symbol_waluty varchar(10))"
			);

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Oferta VALUES(?,?,?,?,?,?,?)");
			for (String line : td.list) {
				String[] oddziel = line.split("\\t");
				pstmt.setInt(1, id);
				id++;
				
				pstmt.setString(2, oddziel[0]);
				
				pstmt.setString(3, oddziel[1]);
				
				pstmt.setString(4, oddziel[2]);
				
				pstmt.setString(5, oddziel[3]);
				
				pstmt.setString(6, oddziel[4]);
				
				pstmt.setString(7, oddziel[5]);
				
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.err.println("DDL&DML exe");
			e.printStackTrace();
		}
	}



	
	


	public void showGui() {
		// TODO Auto-generated method stub
		JTable table = new JTable(new TableModel(td));

        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                JFrame f = new JFrame("GUItable");
                f.add( new JScrollPane(table) );
                f.pack();
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);
            }
        });
	}
	
	
	
	
}
