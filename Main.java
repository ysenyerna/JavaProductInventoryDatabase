import java.sql.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("Product Inventory Database");
		System.out.println("--------------------------");

		String url = "jdbc:sqlite:inventory.db";
		
		try {
			
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection successful.");

		}
		catch (SQLException e) {
			System.out.println("Database error:");
			e.printStackTrace();
		}
	}
}
