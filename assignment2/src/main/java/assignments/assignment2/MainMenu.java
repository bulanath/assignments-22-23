package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import assignments.assignment1.NotaGenerator;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList;
    private static ArrayList<Member> memberList;
    private static int idNota = 0;

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

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    private static void handleGenerateUser() {
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda:");
        String noHP = input.nextLine();
        while (!isNumeric(noHP)) {
            System.out.println("Field nomor hp hanya menerima digit.");
            noHP = input.nextLine();
        }

        String idMember = NotaGenerator.generateId(nama, noHP);

        boolean duplicate = false;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getId().equals(idMember)) {
                String namaMember = memberList.get(i).getNama();
                String hpMember = memberList.get(i).getNoHP();
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", namaMember, hpMember);
                duplicate = true;
            }
        }

        if (duplicate == false) {
            Member member = new Member(nama, noHP, idMember);
            memberList.add(member);
            System.out.printf("Berhasil membuat member dengan %s!\n", idMember);
        }
    }

    private static void handleGenerateNota() {
        String tanggalMasuk = fmt.format(cal.getTime());
        String paket = "";
        int indexMember = 0;
        int sisaHariPengerjaan = 0;

        System.out.println("Masukan ID member:");
        String idMember = input.nextLine();

        boolean findMember = false;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getId().equals(idMember)) {
                indexMember = i;
                findMember = true;
            }
        }

        if (findMember == true) {
            while (true) {
                System.out.println("Masukkan paket laundry:");
                paket = input.nextLine();
                sisaHariPengerjaan = NotaGenerator.getHariPaket(paket);
    
                if (paket.equals("?")) {
                    NotaGenerator.showPaket();
                    continue;
                }
    
                if (NotaGenerator.getHargaPaket(paket) < 0) {
                    System.out.printf("Paket %s tidak diketahui\n", paket);
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                } else {
                    break;
                }
            }
                            
            System.out.println("Masukkan berat cucian Anda [Kg]: ");
            String beratInput = input.nextLine();
            while (!isNumeric(beratInput) || Integer.parseInt(beratInput) < 1) {
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                beratInput = input.nextLine();
            }
            int berat = Integer.parseInt(beratInput);
    
            if (berat < 2) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
            
            memberList.get(indexMember).setBonusCounter();

            boolean getDiscount = false;
            if (memberList.get(indexMember).getBonusCounter() == 3) {
                getDiscount = true;
                memberList.get(indexMember).resetBonusCounter();
            }

            Nota nota = new Nota(memberList.get(indexMember), paket, berat, tanggalMasuk, idNota, sisaHariPengerjaan, getDiscount);
            notaList.add(nota);
            
            System.out.println("Berhasil menambahkan nota!");
            System.out.println(nota);

            idNota ++;
        }
        else {
            System.out.printf("Member dengan ID %s tidak ditemukan!\n", idMember);
        }
    }

    private static void handleListNota() {
        System.out.printf("Terdapat %d nota dalam sistem.\n", notaList.size());
        for (int i = 0; i < notaList.size(); i++) {
            int idNota = notaList.get(i).getIdNota();
            if (notaList.get(i).getIsReady()) {
                System.out.printf("- [%d] Status\t\t: Sudah dapat diambil!\n", idNota);
            } else {
                System.out.printf("- [%d] Status\t\t: Belum bisa diambil :(\n", idNota);
            }
        }
    }

    private static void handleListUser() {
        System.out.printf("Terdapat %d member dalam sistem.\n", memberList.size());
        for (int i = 0; i < memberList.size(); i++) {
            String idMember = memberList.get(i).getId();
            String namaMember = memberList.get(i).getNama();
            System.out.printf("- %s : %s\n", idMember, namaMember);
        }
    }

    private static void handleAmbilCucian() {
        int indexNota = 0;

        System.out.println("Masukan ID nota yang akan diambil:");
        String idNotaInput = input.nextLine();

        while (!isNumeric(idNotaInput)) {
            System.out.println("ID nota berbentuk angka!");
            idNotaInput = input.nextLine();
        }
        
        int idNota = Integer.parseInt(idNotaInput);
        for (int i = 0; i < notaList.size(); i++) {
            if ((notaList.get(i).getIdNota()) == idNota) {
                indexNota = i;
                if (notaList.get(indexNota).getIsReady()) {
                    System.out.printf("Nota dengan ID %d berhasil diambil!\n", idNota);
                    notaList.remove(indexNota);
                } else {
                    System.out.printf("Nota dengan ID %d gagal diambil!\n", idNota);
                }
            }
        }
        System.out.printf("Nota dengan ID %d tidak ditemukan!\n", idNota);
    }

    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DATE, 1);

        for (int i = 0; i < notaList.size(); i++) {
            notaList.get(i).setSisaHariPengerjaan();
            if (notaList.get(i).getSisaHariPengerjaan() <= 0) {
                notaList.get(i).setIsReady();
                int idNota = notaList.get(i).getIdNota();
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", idNota);
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

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

//#TODO: PESAN AMBIL LAUNDRY, HANDLING PESAN INPUT NOTA