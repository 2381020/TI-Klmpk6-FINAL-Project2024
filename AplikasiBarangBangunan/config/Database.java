package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // URL, username, dan password database
    private static final String URL = "jdbc:mysql://localhost:3306/todoapp"; // Ganti dengan URL database Anda
    private static final String USERNAME = "root"; // Ganti dengan username database Anda
    private static final String PASSWORD = ""; // Ganti dengan password database Anda

    // Metode untuk mendapatkan koneksi ke database
    public static Connection getConnection() throws SQLException {
        try {
            // Memastikan driver JDBC tersedia
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC tidak ditemukan.", e);
        }

        // Mengembalikan koneksi ke database
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Metode untuk menutup koneksi (opsional, untuk memastikan koneksi tertutup)
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi: " + e.getMessage());
            }
        }
    }
}
