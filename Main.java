import java.sql.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("Product Inventory Database");
		System.out.println("--------------------------");

		String url = "jdbc:sqlite:inventory.db";
		
		try {
			
			// Connect to the database
			System.out.println("Connecting to the database...");
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection successful.\n");

			// Create table
			System.out.println("Creating Products table...");
			String createTableSql = """
				CREATE TABLE IF NOT EXISTS products (
					id INTEGER PRIMARY KEY,
					name TEXT NOT NULL,
					category TEXT NOT NULL,
					price REAL NOT NULL,
					quantity INTEGER NOT NULL )
				""";

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createTableSql);
			System.out.println("Table ready.\n");

			// Insert products
			System.out.println("Inserting sample products...");
			String insertProductsSql = """
				INSERT INTO products (name, category, price, quantity) VALUES
					('Laptop', 'Electronics', 899.99, 7),
					('Mouse', 'Electronics', 24.99, 35),
					('Keyboard', 'Electronics', 49.99, 12),
					('Desk Chair', 'Furniture', 149.99, 5),
					('Monitor', 'Electronics', 199.99, 8);
					""";
			stmt.executeUpdate(insertProductsSql);
			System.out.println("Sample products inserted.\n");
		
			// Display all products
			System.out.println("All Products: ");
			String displayProductsSql = "SELECT * FROM products";
			ResultSet allProductsResultSet = stmt.executeQuery(displayProductsSql);
			displayProductResults(allProductsResultSet);

			// Display low stock products
			System.out.println("Low stock products: ");
			String lowStockProductsSql = "SELECT * FROM products WHERE quantity < 10";
			ResultSet lowStockProductsResultSet = stmt.executeQuery(lowStockProductsSql);
			displayProductResults(lowStockProductsResultSet);

			// Update products
			System.out.println("Updating quantity for Laptop...");
			String updateQuantitySql = "UPDATE products SET quantity = 15 WHERE id = 1";
			stmt.executeUpdate(updateQuantitySql);
			System.out.println("Updated successfully.\n");

			// Display updated products list
			System.out.println("Updated Products List: ");
			ResultSet updatedProductsResultSet = stmt.executeQuery(displayProductsSql);
			displayProductResults(updatedProductsResultSet);


		}
		catch (SQLException e) {
			System.out.println("Database error:");
			e.printStackTrace();
		}
	}


	private static void displayProductResults(ResultSet rs) {
		try {
			// Read and print each product row
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String category = rs.getString("category");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");

				System.out.println(id + " | " + name + " | " + category + 
					" | $" + price + " | Quantity: " + quantity);
			}
			System.out.println();
		}
		catch (SQLException e) {
			System.out.println("Error reading database results:");
			e.printStackTrace();
		}
	}
}
