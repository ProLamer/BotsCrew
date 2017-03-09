package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
	private static Connection instance;

	public static synchronized Connection getInstance() {
		if (instance == null) {
			try {
				instance = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/library?createDatabaseIfNotExist=true", "root", "root");
			} catch (SQLException e) {
				System.out.println("������� ���������� �� ���� �����.");
				e.printStackTrace();
			}
		}
		return instance;
	}
}
