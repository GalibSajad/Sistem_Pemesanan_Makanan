package uts_sistempemesananmakanan;

public class Menu {
    protected String nama;
    protected int harga;

    public Menu(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }
}
