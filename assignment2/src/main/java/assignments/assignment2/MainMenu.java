package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import assignments.assignment1.NotaGenerator;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    //Menginisiasi yang diperlukan dalam class MainMenu
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList;
    private static ArrayList<Member> memberList;
    private static int idNota = 0;

    /**
     * Method utama program
     */
    public static void main(String[] args) {
        notaList = new ArrayList<Nota>();
        memberList = new ArrayList<Member>();

        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    /**
     * Method untuk validasi input numerik
     */
    private static boolean isNumeric(String str) {
        //Handling untuk input bilangan negatif agar tidak langsung return false
        if (str.startsWith("-")) {
            if (str.length() == 1) {
                return false;
            }
            for (int i = 1; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Method untuk melakukan Generate User
     */
    private static void handleGenerateUser() {
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda:");
        String noHP = input.nextLine();

        //While loop untuk handling jika input noHP tidak sesuai
        while (!isNumeric(noHP) || !noHP.matches("[0-9]+")) {
            System.out.println("Field nomor hp hanya menerima digit.");
            noHP = input.nextLine();
        }

        //Membuat id user menggunakan generateId dari class NotaGenerator
        String idUser = NotaGenerator.generateId(nama, noHP);

        //For loop untuk mencari jika ada id user yang duplikat
        boolean duplicate = false;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getId().equals(idUser)) {
                String namaUser = memberList.get(i).getNama();
                String hpUser = memberList.get(i).getNoHP();
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", namaUser, hpUser);
                duplicate = true;
            }
        }

        //Menambahkan data user ke class Member jika tidak duplikat
        if (duplicate == false) {
            Member member = new Member(nama, noHP, idUser);
            memberList.add(member);
            System.out.printf("Berhasil membuat member dengan %s!\n", idUser);
        }
    }

    /**
     * Method untuk melakukan Generate Nota
     */
    private static void handleGenerateNota() {
        //Menginisiasi variabel yang diperlukan dalam method
        String tanggalMasuk = fmt.format(cal.getTime());
        String paket = "";
        int indexMember = 0;
        int sisaHariPengerjaan = 0;

        System.out.println("Masukan ID member:");
        String idMember = input.nextLine();

        //Mencari index idMember pada array memberList
        boolean findMember = false;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getId().equals(idMember)) {
                indexMember = i;
                findMember = true;
            }
        }

        //Selection jika idMember terdapat pada array memberList atau tidak
        if (findMember == true) {
            //While loop untuk handling input paket sampai sesuai
            while (true) {
                System.out.println("Masukkan paket laundry:");
                paket = input.nextLine();

                //Mengambil hari pengerjaan laundry sesuai paket dari class NotaGenerator
                sisaHariPengerjaan = NotaGenerator.getHariPaket(paket);
                
                //Mengeluarkan menu paket jika user menginput "?"
                if (paket.equals("?")) {
                    NotaGenerator.showPaket();
                    continue;
                }
                
                //Output jika input paket tidak sesuai
                if (NotaGenerator.getHargaPaket(paket) < 0) {
                    System.out.printf("Paket %s tidak diketahui\n", paket);
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                } else {
                    break;
                }
            }
                            
            System.out.println("Masukkan berat cucian Anda [Kg]: ");
            String beratInput = input.nextLine();

            //While loop untuk handling jika input berat bukan int > 1
            while (!isNumeric(beratInput) || Integer.parseInt(beratInput) < 1) {
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                beratInput = input.nextLine();
            }
            int berat = Integer.parseInt(beratInput);
            
            //Selection untuk handling jika input berat cucian < 2 kg
            if (berat < 2) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
            
            //Melakukan increment setelah user melakukan laundry
            memberList.get(indexMember).setBonusCounter();
            
            //Mendapat diskon dan me-reset bonusCounter jika user sudah laundry ketiga kalinya
            boolean getDiscount = false;
            if (memberList.get(indexMember).getBonusCounter() == 3) {
                getDiscount = true;
                memberList.get(indexMember).resetBonusCounter();
            }

            //Menambahkan nota yang telah dibuat ke class Nota
            Nota nota = new Nota(memberList.get(indexMember), paket, berat, tanggalMasuk, idNota, sisaHariPengerjaan, getDiscount);
            notaList.add(nota);
            
            //Mengeluarkan output nota sesuai format
            System.out.println("Berhasil menambahkan nota!");
            System.out.println(nota);

            //Melakukan increment untuk idNota setiap ada user yang menggunakan laundry
            idNota ++;
        }
        else {
            System.out.printf("Member dengan ID %s tidak ditemukan!\n", idMember);
        }
    }

    /**
     * Method untuk mengeluarkan output notaList
     */
    private static void handleListNota() {
        System.out.printf("Terdapat %d nota dalam sistem.\n", notaList.size());

        //For loop untuk menentukan output sesuai status laundry user
        for (int i = 0; i < notaList.size(); i++) {
            int idNota = notaList.get(i).getIdNota();
            if (notaList.get(i).getIsReady()) {
                System.out.printf("- [%d] Status\t\t: Sudah dapat diambil!\n", idNota);
            } else {
                System.out.printf("- [%d] Status\t\t: Belum bisa diambil :(\n", idNota);
            }
        }
    }

    /**
     * Method untuk mengeluarkan output memberList
     */
    private static void handleListUser() {
        System.out.printf("Terdapat %d member dalam sistem.\n", memberList.size());

        //For loop untuk mengeluarkan output setiap user dengan id dan nama
        for (int i = 0; i < memberList.size(); i++) {
            String idMember = memberList.get(i).getId();
            String namaMember = memberList.get(i).getNama();
            System.out.printf("- %s : %s\n", idMember, namaMember);
        }
    }

    /**
     * Method untuk mengambil cucian laundry
     */
    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil:");
        String idNotaInput = input.nextLine();
        
        //While loop jika input id nota bukan numerik
        while (!isNumeric(idNotaInput)) {
            System.out.println("ID nota berbentuk angka!");
            idNotaInput = input.nextLine();
        }
        
        int indexNota = -1;
        int idNota = Integer.parseInt(idNotaInput);

        //For loop untuk mencari index nota yang sesuai pada notaList
        for (int i = 0; i < notaList.size(); i++) {
            if ((notaList.get(i).getIdNota()) == idNota) {
                indexNota = i;
            }
        }

        //Output jika id nota tidak terdapat pada notaList
        if (indexNota == -1) {
            System.out.printf("Nota dengan ID %d tidak ditemukan!\n", idNota);
        } else {

            //Output jika id nota terdapat pada notaList dan sudah selesai dicuci
            if (notaList.get(indexNota).getIsReady()) {
                System.out.printf("Nota dengan ID %d berhasil diambil!\n", idNota);
                notaList.remove(indexNota);
            }
            //Output jika id nota terdapat pada notaList namun belum selesai dicuci
            else {
                System.out.printf("Nota dengan ID %d gagal diambil!\n", idNota);
            }
        }
    }

    /**
     * Method untuk mengubah hari pada MainMenu
     */
    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");

        //Menambahkan 1 untuk mengubah ke hari selanjutnya
        cal.add(Calendar.DATE, 1);

        //For loop untuk mengeluarkan output status laundry yang dapat diambil
        for (int i = 0; i < notaList.size(); i++) {
            //Melakukan decrement kepada setiap cucian
            notaList.get(i).setSisaHariPengerjaan();

            //Mencari laundry yang sudah selesai di cuci
            if (notaList.get(i).getSisaHariPengerjaan() <= 0) {
                notaList.get(i).setIsReady();
                int idNota = notaList.get(i).getIdNota();
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", idNota);
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    /**
     * Method untuk menampilkan menu
     */
    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }
}

/**
 * Resources:
 * https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
 * https://www.javatpoint.com/understanding-toString()-method
 * https://stackoverflow.com/questions/12575990/calendar-date-to-yyyy-mm-dd-format-in-java
 * And many more from StackOverflow, GeeksforGeeks, etc regarding the topic of this TP
 */