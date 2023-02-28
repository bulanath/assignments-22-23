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
        boolean mainMenu = true;

        while (mainMenu) {
            try {
                printMenu();
                System.out.print("Pilihan : ");
                int pilihan = input.nextInt();
                input.nextLine();
                System.out.println("================================");
                
                if (pilihan == 1) {
                    System.out.println("Masukkan nama Anda:");
                    String nama = input.nextLine();
                    System.out.println("Masukkan nomor handphone Anda:");
                    String nomorHP = input.next();
                    System.out.println("ID Anda : "+generateId(nama, nomorHP));
                } else if (pilihan == 2) {
                    System.out.println("Masukkan nama Anda:");
                    String nama = input.nextLine();
                    System.out.println("Masukkan nomor handphone Anda:");
                    String nomorHP = input.next();
                    System.out.println("Masukkan tanggal terima:");
                    String tanggalTerima = input.next();
                    System.out.println("Masukkan paket laundry:");
                    String paket = input.next();
                    System.out.println("Masukkan berat cucian Anda [Kg]:");
                    int berat = input.nextInt();
                    System.out.println(generateNota(generateId(nama, nomorHP), paket, berat, tanggalTerima));
                } else if (pilihan == 0) {
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    mainMenu = false;
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

    public static int getLetterPosition(char letter) {
        int position;

        if (Character.isLetter(letter)) {
            position = letter - 64;
        } else {
            position = letter - 48;
        }
        return position;
    }

    public static String checkSum(String idNota) {
        int idValue = 0;

        for (int i = 0; i < idNota.length(); i++) {
            if (idNota.charAt(i) == '-') {
                idValue += 7;
            } else {
                idValue += getLetterPosition(idNota.charAt(i));
            } 
        }
        
        String kodeChecksum = Integer.toString(idValue);

        if (idValue >= 100) {
            kodeChecksum = kodeChecksum.substring(kodeChecksum.length()-2);
        } else if (idValue >= 0 && idValue < 10) {
            kodeChecksum = "0" + kodeChecksum;
        } else {
            kodeChecksum = kodeChecksum;
        }

        return kodeChecksum;
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP) {
        // TODO: Implement generate ID sesuai soal.
        String namaDepan, idNota;

        if (nama.contains(" ")) {
            namaDepan = nama.substring(0, nama.indexOf(' ')).toUpperCase();
        } else {
            namaDepan = nama.toUpperCase();
        }

        idNota = namaDepan+"-"+nomorHP;
        return idNota+"-"+checkSum(idNota);
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

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {
        // TODO: Implement generate nota sesuai soal.
        return null;
    }
}