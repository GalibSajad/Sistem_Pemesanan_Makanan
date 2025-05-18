import java.util.Scanner;
import uts_sistempemesananmakanan.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Makanan[] daftarMakanan = {
            new Makanan("Nasi Goreng", 15000),
            new Makanan("Ayam Geprek", 18000),
            new Makanan("Bakso", 12000)
        };

        Minuman[] daftarMinuman = {
            new Minuman("Es Teh", 5000),
            new Minuman("Jus Jeruk", 8000),
            new Minuman("Kopi", 7000)
        };

        String namaPemesan = "";
        Pesanan pesanan = new Pesanan(false);
        String metodeBayar = "";
        boolean pakaiDiskon = false;
        boolean pesananDibatalkan = false;

        while (true) {
            System.out.println("\n=== SELAMAT DATANG DI SISTEM PEMESANAN MAKANAN ===");
            System.out.println("1. Masukkan nama pemesan");
            System.out.println("2. Pilih kategori dan pesan");
            System.out.println("3. Pilih metode pembayaran");
            System.out.println("4. Tambah diskon 10%");
            System.out.println("5. Batalkan pesanan");
            System.out.println("6. Cetak struk dan selesai");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine();

            if (pilihan == 0) {
                System.out.println("Terima kasih! Program selesai.");
                break;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pemesan: ");
                    namaPemesan = input.nextLine();
                    System.out.println("Nama pemesan berhasil diinput.");
                    break;

                case 2:
                    if (namaPemesan.isEmpty()) {
                        System.out.println("Silakan masukkan nama pemesan dulu di nomor 1.");
                        break;
                    }
                    System.out.println("Pilih kategori:");
                    System.out.println("1. Makanan");
                    System.out.println("2. Minuman");
                    System.out.print("Pilihan kategori: ");
                    int kategori = input.nextInt();
                    input.nextLine();

                    Menu[] menuDipilih = null;
                    if (kategori == 1) menuDipilih = daftarMakanan;
                    else if (kategori == 2) menuDipilih = daftarMinuman;
                    else {
                        System.out.println("Kategori tidak tersedia.");
                        break;
                    }

                    System.out.println("Daftar " + (kategori == 1 ? "Makanan" : "Minuman") + ":");
                    for (int i = 0; i < menuDipilih.length; i++) {
                        System.out.println((i + 1) + ". " + menuDipilih[i].getNama() + " - Rp" + menuDipilih[i].getHarga());
                    }

                    System.out.print("Pilih menu (nomor), pisahkan dengan koma jika lebih dari satu (contoh: 2,3): ");
                    String pilihanMenu = input.nextLine();
                    String[] nomorMenus = pilihanMenu.split(",");

                    for (String nomorStr : nomorMenus) {
                        nomorStr = nomorStr.trim();
                        int nomorMenu = -1;
                        try {
                            nomorMenu = Integer.parseInt(nomorStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Input nomor menu tidak valid: " + nomorStr);
                            continue;
                        }

                        if (nomorMenu < 1 || nomorMenu > menuDipilih.length) {
                            System.out.println("Nomor menu tidak tersedia: " + nomorMenu);
                            continue;
                        }

                        System.out.print("Jumlah untuk " + menuDipilih[nomorMenu - 1].getNama() + ": ");
                        int jumlah = input.nextInt();
                        input.nextLine();

                        if (jumlah > 0) {
                            pesanan.tambahItem(menuDipilih[nomorMenu - 1], jumlah);
                            pesananDibatalkan = false;
                            System.out.println("Item " + menuDipilih[nomorMenu - 1].getNama() + " sebanyak " + jumlah + " berhasil ditambahkan.");
                        } else {
                            System.out.println("Jumlah tidak valid untuk menu " + menuDipilih[nomorMenu - 1].getNama());
                        }
                    }
                    break;

                case 3:
                    System.out.println("Pilih metode pembayaran:");
                    System.out.println("1. Tunai");
                    System.out.println("2. Kartu");
                    System.out.print("Pilihan: ");
                    int bayar = input.nextInt();
                    input.nextLine();
                    if (bayar == 1) metodeBayar = "Tunai";
                    else if (bayar == 2) metodeBayar = "Kartu";
                    else {
                        System.out.println("Metode pembayaran tidak valid.");
                        break;
                    }
                    System.out.println("Metode pembayaran: " + metodeBayar);
                    break;

                case 4:
                    System.out.print("Apakah ingin menggunakan diskon 10%? (y/n): ");
                    String diskonInput = input.nextLine();
                    pakaiDiskon = diskonInput.equalsIgnoreCase("y");
                    pesanan.setPakaiDiskon(pakaiDiskon);
                    System.out.println("Diskon di-set ke: " + (pakaiDiskon ? "Ya" : "Tidak"));
                    break;

                case 5:
                    System.out.print("Apakah Anda yakin ingin membatalkan pesanan? (y/n): ");
                    String batalInput = input.nextLine();
                    if (batalInput.equalsIgnoreCase("y")) {
                        pesanan = new Pesanan(false);
                        pesananDibatalkan = true;
                        System.out.println("Pesanan dibatalkan dan siap untuk pemesanan baru.");
                    } else {
                        System.out.println("Pesanan tidak dibatalkan.");
                    }
                    break;

                case 6:
                    if (pesananDibatalkan) {
                        System.out.println("Pesanan telah dibatalkan. Tidak dapat mencetak struk.");
                        break;
                    }
                    if (namaPemesan.isEmpty()) {
                        System.out.println("Nama pemesan belum dimasukkan.");
                        break;
                    }
                    if (metodeBayar.isEmpty()) {
                        System.out.println("Metode pembayaran belum dipilih.");
                        break;
                    }

                    System.out.println("\n=== STRUK PEMESANAN ===");
                    pesanan.setPakaiDiskon(pakaiDiskon);
                    pesanan.cetakStruk(namaPemesan, metodeBayar);
                    System.out.println("Pembayaran berhasil. Terima kasih!");
                    return;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }

        input.close();
    }
}
