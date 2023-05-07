package assignments.assignment3.nota;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[0];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int hariKerja;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    private int kompensasi;
    private boolean terlambat;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggalMasuk) {
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggalMasuk;
        this.id = totalNota;
        this.sisaHariPengerjaan = NotaGenerator.getHariPaket(paket);
        this.hariKerja = sisaHariPengerjaan;
        this.baseHarga = NotaGenerator.getHargaPaket(paket);
        addService(new CuciService());
        totalNota++;
    }

    /**
     * Menambahkan service yang dipakai ke array services.
     */
    public void addService(LaundryService service) {
        services = Arrays.copyOf(services, services.length + 1);
        services[services.length - 1] = service;
    }

    /**
     * Mengubah status pengerjaan nota setelah dikerjakan oleh employee.
     */
    public String kerjakan() {
        for (LaundryService service: services) {

            //Mengerjakan service jika belum dikerjakan.
            if (!service.isDone()) {
                String mengerjakan = service.doWork();
                return String.format("Nota %d : %s", getIdNota(), mengerjakan);
            }
        }

        //Mengubah boolean isDone jika seluruh service yang dipakai telah dikerjakan.
        if (isServiceDone()) {
                this.isDone = true;
            }   
        return String.format("Nota %d : Sudah selesai.", getIdNota());
    }

    /**
     * Memastikan apakah seluruh service sudah dikerjakan atau belum.
     */
    public boolean isServiceDone() {
        for (LaundryService service: services) {
            if (!service.isDone()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Mengurangi sisa hari pengerjaan jika ke hari selanjutnya.
     */
    public void toNextDay() {
        this.sisaHariPengerjaan--;
    }

    /**
     * Menghitung harga laundry.
     */
    public long calculateHarga() {
        long harga = 0;

        //Harga cucian
        harga += getHarga() * getBerat();

        //Harga service yang dipakai
        for (LaundryService service: services) {
            harga += service.getHarga(getBerat());
        }

        if (!isDone() && getSisaHariPengerjaan() < 0) {
            this.terlambat = true;
            this.kompensasi = Math.abs(this.sisaHariPengerjaan);
        }

        if (kompensasi > 0) {
            harga -= kompensasi * 2000;
        }
        
        //Menjadikan harga 0 jika kompensasi membuat harga menjadi negatif.
        if (harga < 0) {
            harga = 0;
        }
        return harga;
    }

    /**
     * Mendapatkan status nota laundry.
     */
    public String getNotaStatus() {
        if (isServiceDone()) {
            return String.format("Nota %d : Sudah selesai.", getIdNota());
        }
        return String.format("Nota %d : Belum selesai.", getIdNota());
    }
    
    /**
     * Mengeluarkan output nota laundry.
     */
    @Override
    public String toString() {
        //Menginisiasi class SimpleDateFormat dan Calendar untuk menghitung tanggal selesai.
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        
        //Menyesuaikan tanggalMasuk ke format Calendar.
        int year = Integer.parseInt(tanggalMasuk.substring(6));
        int month = Integer.parseInt(tanggalMasuk.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalMasuk.substring(0, 2));
        cal.set(year, month, date);
        
        //Menambah hari sesuai dengan paket laundry.
        cal.add(Calendar.DATE, hariKerja);
        
        String nota = "";
        nota += String.format("[ID Nota = %d]\n", getIdNota());
        nota += String.format("ID    : %s\n", member.getId());
        nota += String.format("Paket : %s\n", getPaket());
        nota += "Harga :\n";
        nota += String.format("%d kg x %d = %d\n", getBerat(), getHarga(), getBerat() * getHarga());
        nota += String.format("tanggal terima  : %s\n", getTanggal());
        nota += String.format("tanggal selesai : %s\n", fmt.format(cal.getTime()));
        nota += String.format("--- SERVICE LIST ---\n");
        for (LaundryService service: services) {
            nota += String.format("-%s @ Rp.%d\n", service.getServiceName(), service.getHarga(getBerat()));
        }
        nota += String.format("Harga Akhir: %d ", calculateHarga());
        if (this.terlambat) {
            nota += String.format("Ada kompensasi keterlambatan %d * 2000 hari", Math.abs(sisaHariPengerjaan));
        }
        nota += "\n";
        return nota;
    }
    
    /**
     * Menentukan apakah service laundry sudah selesai apa belum.
     */
    public boolean isDone() {
        this.isDone = true;
        for (LaundryService service: services) {
            if (!service.isDone()) {
                this.isDone = false;
            }
        }
        return this.isDone;
    }
    
    /**
     * Method getter.
     */
    public String getPaket() {
        return this.paket;
    }
    
    public int getBerat() {
        return this.berat;
    }
    
    public String getTanggal() {
        return this.tanggalMasuk;
    }
    
    public int getSisaHariPengerjaan() {
        return this.sisaHariPengerjaan;
    }
    
    public LaundryService[] getServices() {
        return this.services;
    }
    
    public long getHarga() {
        return this.baseHarga;
    }

    public int getIdNota() {
        return this.id;
    }
}