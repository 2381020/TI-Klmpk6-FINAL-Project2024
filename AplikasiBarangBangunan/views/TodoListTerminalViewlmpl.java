package todoapp.views;

import org.springframework.stereotype.Component;
import todoapp.entities.BarangBangunan;
import todoapp.services.BarangBangunanService;

import java.util.*;

@Component
public class TodoListTerminalViewImpl implements TodoListView {
    private final Scanner scanner = new Scanner(System.in);
    private final List<BarangBangunan> data = new ArrayList<>();
    private final BarangBangunanService todoListService;
    private boolean loggedIn = true; // Simulasi user login
    private final String loggedInPassword = "password123"; // Simulasi password

    public TodoListTerminalViewImpl(BarangBangunanService todoListService) {
        this.todoListService = todoListService;
    }

    private String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Tambah Data Barang");
            System.out.println("2. Edit Data Barang");
            System.out.println("3. Hapus Data Barang");
            System.out.println("4. Tampilkan Semua Barang");
            System.out.println("5. Cari Barang Berdasarkan ID");
            System.out.println("6. Cari Barang Berdasarkan Nama");
            System.out.println("7. Sortir Barang");
            System.out.println("8. Hitung Jumlah Barang");
            System.out.println("9. Ekspor Data Barang");
            System.out.println("10. Impor Data Barang");
            System.out.println("11. Reset Form");
            System.out.println("12. Validasi Input");
            System.out.println("13. Logout dan Keluar");
            System.out.println("================");

            String selectedMenu = input("Pilih Menu");
            switch (selectedMenu) {
                case "1" -> tambahData();
                case "2" -> editData();
                case "3" -> hapusData();
                case "4" -> tampilkanData();
                case "5" -> cariDataByID();
                case "6" -> cariDataByNama();
                case "7" -> menuSortir();
                case "8" -> hitungJumlahData();
                case "9" -> eksporData();
                case "10" -> imporData();
                case "11" -> resetForm();
                case "12" -> validasiInput();
                case "13" -> {
                    logout();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private void tambahData() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga Barang: ");
        double harga = Double.parseDouble(scanner.nextLine());
        data.add(new BarangBangunan(id, nama, harga));
        System.out.println("Barang berhasil ditambahkan.");
    }

    private void editData() {
        String id = input("Masukkan ID Barang yang ingin diedit");
        for (BarangBangunan item : data) {
            if (item.getId().equals(id)) {
                String namaBaru = input("Masukkan Nama Baru (kosongkan jika tidak ingin mengubah)");
                if (!namaBaru.isEmpty()) item.setNama(namaBaru);
                String hargaBaru = input("Masukkan Harga Baru (kosongkan jika tidak ingin mengubah)");
                if (!hargaBaru.isEmpty()) item.setHarga(Double.parseDouble(hargaBaru));
                System.out.println("Barang berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Barang dengan ID tersebut tidak ditemukan.");
    }

    private void hapusData() {
        String id = input("Masukkan ID Barang yang ingin dihapus");
        data.removeIf(item -> item.getId().equals(id));
        System.out.println("Barang berhasil dihapus.");
    }

    private void tampilkanData() {
        System.out.println("Data Barang:");
        for (BarangBangunan item : data) {
            System.out.println(item);
        }
    }

    private void cariDataByID() {
        String id = input("Masukkan ID Barang");
        data.stream()
                .filter(item -> item.getId().equals(id))
                .forEach(System.out::println);
    }

    private void cariDataByNama() {
        String nama = input("Masukkan Nama Barang");
        data.stream()
                .filter(item -> item.getNama().contains(nama))
                .forEach(System.out::println);
    }

    private void menuSortir() {
        System.out.println("1. Sortir berdasarkan ID");
        System.out.println("2. Sortir berdasarkan Nama");
        System.out.println("3. Sortir berdasarkan Harga");
        int pilihan = Integer.parseInt(input("Pilih opsi"));
        Comparator<BarangBangunan> comparator = switch (pilihan) {
            case 1 -> Comparator.comparing(BarangBangunan::getId);
            case 2 -> Comparator.comparing(BarangBangunan::getNama);
            case 3 -> Comparator.comparingDouble(BarangBangunan::getHarga);
            default -> null;
        };
        if (comparator != null) {
            data.sort(comparator);
            tampilkanData();
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private void hitungJumlahData() {
        System.out.println("Jumlah barang: " + data.size());
    }

    private void eksporData() {
        // Ekspor data ke file.
    }

    private void imporData() {
        // Impor data dari file.
    }

    private void resetForm() {
        System.out.println("Form berhasil di-reset.");
    }

    private void validasiInput() {
        System.out.println("Validasi input berhasil.");
    }

    private void logout() {
        loggedIn = false;
        System.out.println("Anda telah logout.");
    }

    @Override
    public void run() {
        showMainMenu();
    }
}
