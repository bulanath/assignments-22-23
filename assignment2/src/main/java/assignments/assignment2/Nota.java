package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import assignments.assignment1.NotaGenerator;

public class Nota {
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    private boolean getDiscount;

    public Nota(Member member, String paket, int berat, String tanggalMasuk, int idNota, int sisaHariPengerjaan, boolean getDiscount) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = idNota;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.getDiscount = getDiscount;
    }

    public int getIdNota() {
        return this.idNota;
    }

    public int getSisaHariPengerjaan() {
        return this.sisaHariPengerjaan;
    }

    public void setSisaHariPengerjaan() {
        this.sisaHariPengerjaan--;
    }

    public boolean getIsReady() {
        return this.isReady;
    }

    public void setIsReady() {
        this.isReady = true;
    }

    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(tanggalMasuk.substring(6));
        int month = Integer.parseInt(tanggalMasuk.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalMasuk.substring(0, 2));
        cal.set(year, month, date);
        cal.add(Calendar.DATE, sisaHariPengerjaan);

        long harga = NotaGenerator.getHargaPaket(paket);

        String nota = "";
        nota += String.format("[ID Nota = %d]\n", idNota);
        nota += String.format("ID	: %s\n", this.member.getId());
        nota += String.format("Paket : %s\n", paket);
        nota += "Harga :\n";
        nota += String.format("%d kg x %d = %d", berat, harga, berat * harga);
        if (getDiscount == true) {
            nota += String.format(" = %d (Discount member 50%%!!!)", (berat * harga) / 2);
        }
        nota += "\n";
        nota += String.format("Tanggal Terima  : %s\n", tanggalMasuk);
        nota += String.format("Tanggal Selesai : %s\n", fmt.format(cal.getTime()));
        nota += "Status\t\t: Belum bisa diambil :(";
        return nota;
    }
}