package todoapp.repositories;

import config.Database;
import entities.BarangBangunan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryDbImpl implements todoapp.repositories.TodoListRepository {

    @Override
    public List<BarangBangunan> getAllBarang() {
        List<BarangBangunan> barangList = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM barang_bangunan")) {

            while (resultSet.next()) {
                BarangBangunan barang = new BarangBangunan(
                        resultSet.getString("id"),
                        resultSet.getString("nama"),
                        resultSet.getDouble("harga")
                );
                barangList.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barangList;
    }

    @Override
    public BarangBangunan getBarangById(String id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM barang_bangunan WHERE id = ?")) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new BarangBangunan(
                        resultSet.getString("id"),
                        resultSet.getString("nama"),
                        resultSet.getDouble("harga")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addBarang(BarangBangunan barang) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO barang_bangunan (id, nama, harga) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, barang.getId());
            preparedStatement.setString(2, barang.getNama());
            preparedStatement.setDouble(3, barang.getHarga());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBarang(BarangBangunan barang) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE barang_bangunan SET nama = ?, harga = ? WHERE id = ?")) {

            preparedStatement.setString(1, barang.getNama());
            preparedStatement.setDouble(2, barang.getHarga());
            preparedStatement.setString(3, barang.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBarang(String id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM barang_bangunan WHERE id = ?")) {

            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
