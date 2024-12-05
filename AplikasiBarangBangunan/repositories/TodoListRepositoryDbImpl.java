package todoapp.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import todoapp.entities.BarangBangunan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BarangBangunanRepositoryDbImpl implements BarangBangunanRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Override
    public void save(BarangBangunan barang) {
        String query = "INSERT INTO barang_bangunan (id, nama, harga) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, barang.getId());
            stmt.setString(2, barang.getNama());
            stmt.setDouble(3, barang.getHarga());
            stmt.executeUpdate();
            System.out.println("Barang berhasil disimpan: " + barang);
        } catch (SQLException e) {
            System.out.println("Error saving barang: " + e.getMessage());
        }
    }

    @Override
    public boolean update(String id, String namaBaru, Double hargaBaru) {
        String query = "UPDATE barang_bangunan SET nama = ?, harga = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, namaBaru);
            stmt.setDouble(2, hargaBaru);
            stmt.setString(3, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating barang: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM barang_bangunan WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting barang: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<BarangBangunan> findAll() {
        String query = "SELECT * FROM barang_bangunan";
        List<BarangBangunan> barangList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BarangBangunan barang = new BarangBangunan(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga")
                );
                barangList.add(barang);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all barang: " + e.getMessage());
        }
        return barangList;
    }

    @Override
    public BarangBangunan findById(String id) {
        String query = "SELECT * FROM barang_bangunan WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BarangBangunan(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding barang by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<BarangBangunan> findByName(String name) {
        String query = "SELECT * FROM barang_bangunan WHERE nama LIKE ?";
        List<BarangBangunan> barangList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BarangBangunan barang = new BarangBangunan(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga")
                );
                barangList.add(barang);
            }
        } catch (SQLException e) {
            System.out.println("Error finding barang by name: " + e.getMessage());
        }
        return barangList;
    }

    @Override
    public List<BarangBangunan> sort(String kriteria) {
        String query = "SELECT * FROM barang_bangunan ORDER BY " + kriteria;
        List<BarangBangunan> barangList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BarangBangunan barang = new BarangBangunan(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga")
                );
                barangList.add(barang);
            }
        } catch (SQLException e) {
            System.out.println("Error sorting barang: " + e.getMessage());
        }
        return barangList;
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM barang_bangunan";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting barang: " + e.getMessage());
        }
        return 0;
    }
}
