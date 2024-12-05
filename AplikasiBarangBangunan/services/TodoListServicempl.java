package todoapp.services;

import org.springframework.stereotype.Service;
import todoapp.entities.BarangBangunan;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BarangBangunanServiceImpl implements BarangBangunanService {
    private final List<BarangBangunan> dataBarang = new ArrayList<>();

    @Override
    public void tambahBarang(BarangBangunan barang) {
        dataBarang.add(barang);
        System.out.println("Barang berhasil ditambahkan: " + barang);
    }

    @Override
    public boolean editBarang(String id, String namaBaru, Double hargaBaru) {
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
    public boolean hapusBarang(String id) {
        boolean removed = dataBarang.removeIf(barang -> barang.getId().equals(id));
        if (removed) {
            System.out.println("Barang dengan ID " + id + " berhasil dihapus.");
        } else {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
        }
        return removed;
    }

    @Override
    public List<BarangBangunan> getSemuaBarang() {
        return new ArrayList<>(dataBarang);
    }

    @Override
    public BarangBangunan cariBarangById(String id) {
        return dataBarang.stream()
                .filter(barang -> barang.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BarangBangunan> cariBarangByNama(String nama) {
        return dataBarang.stream()
                .filter(barang -> barang.getNama().toLowerCase().contains(nama.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BarangBangunan> sortirBarang(String kriteria) {
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
    public int hitungJumlahBarang() {
        return dataBarang.size();
    }

    @Override
    public void eksporData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (BarangBangunan barang : dataBarang) {
                writer.write(barang.getId() + "," + barang.getNama() + "," + barang.getHarga());
                writer.newLine();
            }
            System.out.println("Data berhasil diekspor ke " + filePath);
        } catch (IOException e) {
            System.err.println("Gagal mengekspor data: " + e.getMessage());
        }
    }

    @Override
    public void imporData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0];
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    dataBarang.add(new BarangBangunan(id, nama, harga));
                }
            }
            System.out.println("Data berhasil diimpor dari " + filePath);
        } catch (IOException e) {
            System.err.println("Gagal mengimpor data: " + e.getMessage());
        }
    }
}
