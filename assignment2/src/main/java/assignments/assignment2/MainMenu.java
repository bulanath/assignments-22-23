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

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda:");
        String noHP = input.nextLine();

        //VALIDASI MASIH ERROR
        while (true) {
            if (NotaGenerator.validasiInt(noHP, noHP.length()) == false) {
                System.out.println("Field nomor hp hanya menerima digit.");
                noHP = input.nextLine();
            } else {
                break;
            } 
            NotaGenerator.validasiInt(noHP, noHP.length());
        }

        //METHOD GENERATE ID MASIH SALAH
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
        // TODO: handle ambil cucian
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.printf("Terdapat %d member dalam sistem.\n", memberList.size());
        for (int i = 0; i < memberList.size(); i++) {
            String idMember = memberList.get(i).getId();
            String namaMember = memberList.get(i).getNama();
            System.out.printf("- %s : %s\n", idMember, namaMember);
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
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
