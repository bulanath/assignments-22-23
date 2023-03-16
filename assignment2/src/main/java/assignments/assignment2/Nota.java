package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import assignments.assignment1.NotaGenerator;

public class Nota {
    //Menginisiasi atribut yang diperlukan dalam class Nota
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    private boolean getDiscount;

    public Nota(Member member, String paket, int berat, String tanggalMasuk, int idNota, int sisaHariPengerjaan, boolean getDiscount) {
        //Membuat constructor untuk class Nota
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = idNota;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.getDiscount = getDiscount;
    }

    
    /**
     * Method getter untuk mengakses atribut dari class lain
     */
    public int getIdNota() {
        return this.idNota;
    }

    public int getSisaHariPengerjaan() {
        return this.sisaHariPengerjaan;
    }

    public boolean getIsReady() {
        return this.isReady;
    }

    /**
     * Method untuk mengurangi hari pengerjaan jika next day dijalankan
     */
    public void setSisaHariPengerjaan() {
        this.sisaHariPengerjaan --;
    }

    /**
     * Method untuk menunjukkan jika laundry sudah selesai dikerjakan
     */
    public void setIsReady() {
        this.isReady = true;
    }

    /**
     * Method untuk mengeluarkan output nota
     */
    public String toString() {
        //Menginisiasi class SimpleDateFormat dan Calendar untuk menghitung tanggalSelesai
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        //Menyesuaikan tanggalMasuk ke format Calendar
        int year = Integer.parseInt(tanggalMasuk.substring(6));
        int month = Integer.parseInt(tanggalMasuk.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalMasuk.substring(0, 2));
        cal.set(year, month, date);

        //Menambah hari sesuai dengan paket laundry
        cal.add(Calendar.DATE, sisaHariPengerjaan);

        //Mengambil harga sesuai dengan paket laundry
        long harga = NotaGenerator.getHargaPaket(paket);

        //Mengeluarkan output nota sesuai format
        String nota = "";
        nota += String.format("[ID Nota = %d]\n", idNota);
        nota += String.format("ID   : %s\n", this.member.getId());
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