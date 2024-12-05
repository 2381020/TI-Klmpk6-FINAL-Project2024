package todoapp.services;

import todoapp.entities.BarangBangunan;
import java.util.List;

public interface BarangBangunanService {

    void tambahBarang(BarangBangunan barang);

    boolean editBarang(String id, String namaBaru, Double hargaBaru);

    boolean hapusBarang(String id);

    List<BarangBangunan> getSemuaBarang();

    BarangBangunan cariBarangById(String id);

    List<BarangBangunan> cariBarangByNama(String nama);

    List<BarangBangunan> sortirBarang(String kriteria);

    int hitungJumlahBarang();

    void eksporData(String filePath);

    void imporData(String filePath);
}
