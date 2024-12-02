package todoapp.repositories;

import entities.BarangBangunan;
import java.util.List;

public interface TodoListRepository {
    List<BarangBangunan> getAllBarang();
    BarangBangunan getBarangById(String id);
    boolean addBarang(BarangBangunan barang);
    boolean updateBarang(BarangBangunan barang);
    boolean deleteBarang(String id);
}
