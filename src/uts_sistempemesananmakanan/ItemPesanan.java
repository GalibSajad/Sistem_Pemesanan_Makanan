package uts_sistempemesananmakanan;

import java.text.NumberFormat;
import java.util.Locale;

public class ItemPesanan {
    private Menu menu;
    private int jumlah;

    public ItemPesanan(Menu menu, int jumlah) {
        if (menu == null || jumlah <= 0) {
            throw new IllegalArgumentException("Menu tidak boleh null dan jumlah harus lebih dari 0.");
        }
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public int getTotal() {
        return menu.getHarga() * jumlah;
    }

    @Override
    public String toString() {
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return menu.getNama() + " x " + jumlah + " = " + rupiahFormat.format(getTotal());
    }
}
