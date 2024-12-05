package todoapp.repositories;

import todoapp.entities.BarangBangunan;

import java.util.List;

public interface BarangBangunanRepository {

    // Menyimpan barang baru
    void save(BarangBangunan barang);

    // Memperbarui barang berdasarkan ID
    boolean update(String id, String namaBaru, Double hargaBaru);

    // Menghapus barang berdasarkan ID
    boolean delete(String id);

    // Mengambil semua barang
    List<BarangBangunan> findAll();

    // Mencari barang berdasarkan ID
    BarangBangunan findById(String id);

    // Mencari barang berdasarkan nama
    List<BarangBangunan> findByName(String name);

    // Menyortir barang berdasarkan kriteria
    List<BarangBangunan> sort(String kriteria);

    // Menghitung jumlah barang yang ada
    int count();
}
