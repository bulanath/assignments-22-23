package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;

import java.util.Arrays;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> generateNota();
            case 2 -> printNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        memberList = Arrays.copyOf(memberList, memberList.length + 1);
        memberList[memberList.length - 1] = member;
    }

    /**
     * Menginisialisasi object Nota dan generate nota laundry.
     */
    public void generateNota() {
        String tanggalMasuk = fmt.format(cal.getTime());

        System.out.println("Masukkan paket laundry:");
        NotaGenerator.showPaket();
        String paket = in.nextLine();

        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        String beratInput = in.nextLine();
        int berat = Integer.parseInt(beratInput);

        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }
        
        //Inisialisasi object nota.
        Nota nota = new Nota(loginMember, berat, paket, tanggalMasuk);

        System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\n"+
        "Hanya tambah 1000 / kg :0\n"+
        "[Ketik x untuk tidak mau]: ");
        String setrikaCucian = in.nextLine();

        //Menambahkan service setrika laundry ke object nota.
        if (!setrikaCucian.equalsIgnoreCase("x")) {
            LaundryService setrika = new SetrikaService();
            nota.addService(setrika);
        }
        
        System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\n"+
        "Cuma 2000 / 4kg, kemudian 500 / kg\n"+
        "[Ketik x untuk tidak mau]: ");
        String antarCucian = in.nextLine();

        //Menambahkan service antar laundry ke object nota.
        if (!antarCucian.equalsIgnoreCase("x")) {
            LaundryService antar = new AntarService();
            nota.addService(antar);
        }
        
        //Menambahkan nota ke array.
        super.loginMember.addNota(nota);
        NotaManager.addNota(nota);
        System.out.println("Nota berhasil dibuat!");
    }

    /**
     * Mencetak nota laundry milik member.
     */
    public void printNota() {
        for (Nota nota: super.loginMember.getNotaList()) {
            System.out.println(nota);
        }
    }
}