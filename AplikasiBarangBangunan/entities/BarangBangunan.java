package todoapp.entities;

public class BarangBangunan {
    private String id;
    private String nama;
    private double harga;

    // Constructor
    public BarangBangunan(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Override toString for easy display
    @Override
    public String toString() {
        return "BarangBangunan{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                '}';
    }
}
