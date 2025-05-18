package uts_sistempemesananmakanan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

public class Pesanan {
    private ArrayList<ItemPesanan> itemPesanan;
    private boolean pakaiDiskon;

    public Pesanan(boolean pakaiDiskon) {
        this.pakaiDiskon = pakaiDiskon;
        itemPesanan = new ArrayList<>();
    }

    public void tambahItem(Menu menu, int jumlah) {
        itemPesanan.add(new ItemPesanan(menu, jumlah));
    }

    public void setPakaiDiskon(boolean pakaiDiskon) {
        this.pakaiDiskon = pakaiDiskon;
    }

    public void cetakStruk(String namaPemesan, String metodePembayaran) {
        double total = 0;
        StringBuilder struk = new StringBuilder();

        LocalDateTime sekarang = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        struk.append("=== Struk Pemesanan ===\n");
        struk.append("Nama Pemesan : ").append(namaPemesan).append("\n");
        struk.append("Tanggal      : ").append(sekarang.format(formatter)).append("\n");
        struk.append("Pembayaran   : ").append(metodePembayaran).append("\n");

        for (ItemPesanan item : itemPesanan) {
            struk.append(item).append("\n");
            total += item.getTotal();
        }

        double diskon = pakaiDiskon ? total * 0.10 : 0;
        double totalAkhir = total - diskon;

        struk.append("------------------------\n");
        struk.append("Total Sebelum Diskon : ").append(rupiahFormat.format(total)).append("\n");
        struk.append("Diskon 10%           : ").append(rupiahFormat.format(diskon)).append("\n");
        struk.append("Total Pembayaran     : ").append(rupiahFormat.format(totalAkhir)).append("\n");
        struk.append("========================\nTerima kasih!\n");

        System.out.println(struk);

        try (FileWriter writer = new FileWriter("struk_" + namaPemesan.replace(" ", "_") + ".txt")) {
            writer.write(struk.toString());
            System.out.println("Struk disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }
}
