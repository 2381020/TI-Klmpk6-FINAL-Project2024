package todoapp.repositories;

import entities.BarangBangunan;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryImpl implements todoapp.repositories.TodoListRepository {
    private final List<BarangBangunan> barangList = new ArrayList<>();

    @Override
    public List<BarangBangunan> getAllBarang() {
        return barangList;
    }

    @Override
    public BarangBangunan getBarangById(String id) {
        for (BarangBangunan barang : barangList) {
            if (barang.getId().equals(id)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public boolean addBarang(BarangBangunan barang) {
        return barangList.add(barang);
    }

    @Override
    public boolean updateBarang(BarangBangunan barang) {
        BarangBangunan existingBarang = getBarangById(barang.getId());
        if (existingBarang != null) {
            existingBarang.setNama(barang.getNama());
            existingBarang.setHarga(barang.getHarga());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBarang(String id) {
        BarangBangunan barang = getBarangById(id);
        return barangList.remove(barang);
    }
}
