package CS418.javasql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Javasqlpractice {
    
	public static void main(String[] args) throws SQLException {
		
		String DB_URL = "jdbc:mysql://localhost/University";
		String USER = "";
		String PASS = "";
		Connection conn= create_connecteion(DB_URL,USER,PASS);
		Statement Stmt = conn.createStatement();
		Create_table(Stmt);
		Insert_Instructor(conn);
		select_allinstructor(Stmt);
		Stmt.close();
	}
		
        
			
	public static Connection create_connecteion(String DB_URL,String USER, String PASS) throws SQLException{Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);return conn;
	}
	public static void Create_table(Statement stmt) throws SQLException{
        String Instructor = "CREATE TABLE Instructor" +
               "(Email VARCHAR(100), " +
               " Name VARCHAR(50), " + 
			   "Salary int, "+
			    "Dname VARCHAR(30),"+
				"Id int,"+
               " PRIMARY KEY (Id))"; 

        
            stmt.executeUpdate(Instructor);
      

	}
	public static void Insert_Instructor(Connection conn) throws SQLException{
		String query="INSERT INTO Instructor(Email,Name,Salary,Dname,Id)VALUES(?,?,?,?,?)";
		PreparedStatement stmta = conn.prepareStatement(query);
		stmta.setString(1,"call@gsdm.edu");
        stmta.setString(2,"Cad");
        stmta.setInt(3,20000);
		stmta.setString(4,"Computer Science");
		stmta.setInt(5, 1);
		stmta.executeUpdate();


		stmta.setString(1,"D@e.edu");
        stmta.setString(2,"Dif");
        stmta.setInt(3,20000000);
		stmta.setString(4,"Science");
		stmta.setInt(5, 2);
		stmta.executeUpdate();
		stmta.close();
	
	}
	public static void select_allinstructor(Statement stmt) throws SQLException{
		//String Query="INSERT INTO Instructor(Email,Name,Salary,Dname,Id)VALUES(?,?,?,?,?)";
		String Query = "SELECT * FROM Instructor";
		ResultSet rs = stmt.executeQuery(Query);
		while (rs.next()) {
			System.out.print("Email: " + rs.getString("Email"));
			System.out.print(", Name: " + rs.getString("Name"));
			System.out.print(", Salary: " + rs.getInt("Salary"));
			System.out.println(", Department: " + rs.getString("Dname"));
			System.out.print(", Id: " + rs.getInt("Id"));
			}
		
	}
}	