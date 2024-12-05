package todoapp.repositories;

import org.springframework.stereotype.Repository;
import todoapp.entities.BarangBangunan;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BarangBangunanRepositoryImpl implements BarangBangunanRepository {

    private final List<BarangBangunan> dataBarang = new ArrayList<>();

    @Override
    public void save(BarangBangunan barang) {
        dataBarang.add(barang);
        System.out.println("Barang berhasil disimpan: " + barang);
    }

    @Override
    public boolean update(String id, String namaBaru, Double hargaBaru) {
        for (BarangBangunan barang : dataBarang) {
            if (barang.getId().equals(id)) {
                if (namaBaru != null && !namaBaru.isBlank()) {
                    barang.setNama(namaBaru);
                }
                if (hargaBaru != null) {
                    barang.setHarga(hargaBaru);
                }
                System.out.println("Barang berhasil diperbarui: " + barang);
                return true;
            }
        }
        System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
        return false;
    }

    @Override
    public boolean delete(String id) {
        boolean removed = dataBarang.removeIf(barang -> barang.getId().equals(id));
        if (removed) {
            System.out.println("Barang dengan ID " + id + " berhasil dihapus.");
        } else {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
        }
        return removed;
    }

    @Override
    public List<BarangBangunan> findAll() {
        return new ArrayList<>(dataBarang);
    }

    @Override
    public BarangBangunan findById(String id) {
        return dataBarang.stream()
                .filter(barang -> barang.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BarangBangunan> findByName(String name) {
        return dataBarang.stream()
                .filter(barang -> barang.getNama().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BarangBangunan> sort(String kriteria) {
        Comparator<BarangBangunan> comparator;
        switch (kriteria.toLowerCase()) {
            case "id":
                comparator = Comparator.comparing(BarangBangunan::getId);
                break;
            case "nama":
                comparator = Comparator.comparing(BarangBangunan::getNama);
                break;
            case "harga":
                comparator = Comparator.comparingDouble(BarangBangunan::getHarga);
                break;
            default:
                throw new IllegalArgumentException("Kriteria sortir tidak valid: " + kriteria);
        }
        return dataBarang.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return dataBarang.size();
    }
}
