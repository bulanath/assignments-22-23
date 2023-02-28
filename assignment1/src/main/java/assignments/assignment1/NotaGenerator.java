package assignments.assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        while (true) {
            try {
                printMenu();
                System.out.print("Pilihan : ");
                int pilihan = input.nextInt();
                System.out.println("================================");
                if (pilihan == 1) {
                    System.out.println("Masukkan nama Anda:");
                    String nama = input.nextLine();
                    input.next();
                    System.out.println("Masukkan nomor handphone Anda:");
                    String nomorHP = input.nextLine();
                    input.next();
                    System.out.println(generateId(nama, nomorHP));
                } else if (pilihan == 2) {
                    System.out.println("Masukkan nama Anda:");
                    String nama = input.nextLine();
                    input.next();
                    System.out.println("Masukkan nomor handphone Anda:");
                    String nomorHandphone = input.nextLine();
                    input.next();
                    System.out.println("Masukkan tanggal terima:");
                    String tanggalTerima = input.nextLine();
                    input.next();
                    System.out.println("Masukkan paket laundry:");
                    String paketLaundry = input.nextLine();
                    input.next();
                    System.out.println("Masukkan berat cucian Anda [kg]:");
                    int beratCucian = input.nextInt();
                    generateNota(generateId(nama, nomorHandphone), paketLaundry, beratCucian, tanggalTerima);
                } else if (pilihan == 0) {
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break;
                } else {
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                }
            } catch (InputMismatchException e) {
                System.out.println("================================");
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                input.next();
            }
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP) {
        // TODO: Implement generate ID sesuai soal.
        String namaDepan = nama+nomorHP;
        return namaDepan;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        return null;
    }
}