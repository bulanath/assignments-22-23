package assignments.assignment1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        boolean mainMenu = true;

        while (mainMenu) {
            printMenu();
            System.out.print("Pilihan : ");
            String pilihan = input.next();
            input.nextLine();
            System.out.println("================================");

            //selection untuk menjalankan tiap pilihan menu
            if (pilihan.equals("1")) {
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();

                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHP = input.next();

                //while loop untuk handling jika nomor hp bukan integer
                while (true) {
                    if (validasiInt(nomorHP, nomorHP.length()) == false) {
                        System.out.println("Nomor hp hanya menerima digit");
                        nomorHP = input.next();  
                    } else {
                        break;
                    }
                    validasiInt(nomorHP, nomorHP.length());
                }
                
                System.out.println("ID Anda : "+generateId(nama, nomorHP));
            } else if (pilihan.equals("2")) {
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();

                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHP = input.next();

                //while loop untuk handling nomor jika nomor hp bukan integer
                while (true) {
                    if (validasiInt(nomorHP, nomorHP.length()) == false) {
                        System.out.println("Nomor hp hanya menerima digit");
                        nomorHP = input.next();  
                    } else {
                        break;
                    }
                    validasiInt(nomorHP, nomorHP.length());
                }

                System.out.println("Masukkan tanggal terima:");
                String tanggalTerima = input.next();

                System.out.println("Masukkan paket laundry:");
                String paket = input.next();

                //while loop untuk handling jika jenis paket tidak diketahui
                while (true) {
                    if (validasiPaket(paket) == false && !paket.equals("?")) {
                        System.out.println(String.format("Paket %s tidak diketahui", paket));
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    } else if (validasiPaket(paket) == true) {
                        break;
                    }
                    System.out.println("Masukkan paket laundry:");
                    paket = input.next();

                    //mencetak jenis paket jika user input "?"
                    if (paket.equals("?")) {
                        showPaket();
                    } 
                    validasiPaket(paket);
                }  
                
                System.out.println("Masukkan berat cucian Anda [Kg]:");
                String beratStr = input.next();

                //while loop untuk handling jika berat cucian bukan integer
                while (true) {
                    if (validasiInt(beratStr, beratStr.length()) == false) {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        beratStr = input.next();  
                    } else {
                        break;
                    }
                    validasiInt(beratStr, beratStr.length());
                }
                int berat = Integer.parseInt(beratStr);

                //membulatkan berat menjadi 2kg jika berat < 2kg
                if (berat < 2) {
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    berat = 2;
                }

                System.out.println("Nota Laundry\n"+generateNota(generateId(nama, nomorHP), paket, berat, tanggalTerima));
            } else if (pilihan.equals("0")) {
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                mainMenu = false;
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
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
     * Method untuk melakukan validasi bila input bertipe integer atau tidak.
     */
    public static boolean validasiInt(String angka, int n) {
        for (int i = 0; i < n; i++) {
            if (angka.charAt(i) < '0' || angka.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Method untuk melakukan validasi input paket laundry.
     */
    public static boolean validasiPaket(String paket) {
        if (paket.equalsIgnoreCase("express") || paket.equalsIgnoreCase("fast") || paket.equalsIgnoreCase("reguler")) {
            return true;
        }
        return false;
    }

    /**
     * Method untuk mencari nilai karakter sesuai ketentuan soal.
     */
    public static int getCharValue(char ch) {
        int position;

        //selection untuk mendeteksi apakah char berupa huruf atau digit
        if (Character.isLetter(ch)) {
            position = ch - 64;
        } else {
            position = ch - 48;
        }
        return position;
    }

    /**
     * Method untuk menghitung Checksum.
     */
    public static String checkSum(String idNota) {
        int idValue = 0;

        //for loop untuk mencari checksum
        for (int i = 0; i < idNota.length(); i++) {
            if (idNota.charAt(i) == '-') {
                idValue += 7;
            } else {
                idValue += getCharValue(idNota.charAt(i));
            } 
        }
        String kodeChecksum = Integer.toString(idValue);

        //handling digit jika checksum memiliki 1 digit atau 3 digit
        if (idValue >= 100) {
            kodeChecksum = kodeChecksum.substring(kodeChecksum.length()-2);
        } else if (idValue >= 0 && idValue < 10) {
            kodeChecksum = "0" + kodeChecksum;
        } 
        return kodeChecksum;
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     */
    public static String generateId(String nama, String nomorHP) {
        String namaDepan, idNota;

        //selection untuk mengambil nama depan saja jika kata nama > 1
        if (nama.contains(" ")) {
            namaDepan = nama.substring(0, nama.indexOf(' ')).toUpperCase();
        } else {
            namaDepan = nama.toUpperCase();
        }
        idNota = namaDepan+"-"+nomorHP;
        return idNota+"-"+checkSum(idNota);
    }

    /**
     * Method untuk membuat Nota.
     */
    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {
        int hargaKg = 0;
        int totalHarga = 0;
        int hariPengerjaan = 0;
        String nota = "";
        
        //selection untuk menghitung harga laundry sesuai paket
        if (paket.equalsIgnoreCase("express")) {
            hargaKg = 12000;
            totalHarga = berat * 12000;
            hariPengerjaan = 1;
        } else if (paket.equalsIgnoreCase("fast")) {
            hargaKg = 10000;
            totalHarga = berat * 10000;
            hariPengerjaan = 2;
        } else if (paket.equalsIgnoreCase("reguler")) {
            hargaKg = 7000;
            totalHarga = berat * 7000;
            hariPengerjaan = 3;
        }
        
        //formatting output nota laundry
        nota += "ID    : "+id+"\n";
        nota += "Paket : "+paket+"\n";
        nota += "Harga :\n";
        nota += berat+" kg x "+hargaKg+" = "+totalHarga+"\n";
        nota += "Tanggal Terima  : "+tanggalTerima+"\n";
        nota += "Tanggal Selesai : "+hitungTanggal(tanggalTerima, hariPengerjaan);
        return nota;
    }

    /**
     * Method untuk menghitung tanggal selesai menggunakan laundry.
     */
    public static String hitungTanggal(String tanggalTerima, int hariPengerjaan) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggal = LocalDate.parse(tanggalTerima, formatter);
        LocalDate tanggalBaru = tanggal.plusDays(hariPengerjaan);
        String tanggalSelesai = tanggalBaru.format(formatter);
        return tanggalSelesai;
    }
}

/**
 * Resources:
 * https://stackoverflow.com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
 * https://learnjava.co.in/how-to-add-a-number-of-days-to-a-date-in-java/
 * https://www.geeksforgeeks.org/localdate-parse-method-in-java-with-examples/
 * https://www.geeksforgeeks.org/how-to-check-if-string-contains-only-digits-in-java/
 * https://javahungry.blogspot.com/2021/03/not-equal-example-opposite-of-equals.html
 * https://linuxhint.com/check-if-character-is-number-in-java/
 */