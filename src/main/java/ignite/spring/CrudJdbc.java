package ignite.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ignite.IgniteException;

public class CrudJdbc {
	public static void main(String[] args) throws IgniteException {
		
		try {
			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
			Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");
			try (Statement stmt = conn.createStatement()) {
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Oras (" + " id LONG PRIMARY KEY, name VARCHAR) "
						+ " WITH \"template=replicated\"");
				stmt.executeUpdate("CREATE INDEX IF NOT EXISTS idx_city_name ON Oras (name)");
				try (PreparedStatement prStmt = conn.prepareStatement("INSERT INTO Oras (id, name) VALUES (?, ?)")) {

					prStmt.setLong(1, 1L);
					prStmt.setString(2, "Brasov");
					prStmt.executeUpdate();

					prStmt.setLong(1, 2L);
					prStmt.setString(2, "Denver");
					prStmt.executeUpdate();

					prStmt.setLong(1, 3L);
					prStmt.setString(2, "Bucuresti");
					prStmt.executeUpdate();
					
					prStmt.setLong(1, 4L);
					prStmt.setString(2, "Buzau");
					prStmt.executeUpdate();
				}catch (SQLException e) {
					System.out.println("Nu s-a facut insert-ul");
				}
				try (ResultSet rs = stmt.executeQuery("SELECT c.id, c.name FROM Oras c")) {

					while (rs.next())
						System.out.println(rs.getString(1) + ", " + rs.getString(2));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("final");
	}
}
