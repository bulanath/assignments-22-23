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
    private boolean terlambat;
    static public int totalNota = 0;

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

    public void addService(LaundryService service) {
        services = Arrays.copyOf(services, services.length + 1);
        services[services.length - 1] = service;
    }

    public String kerjakan() {
        for (LaundryService service: services) {
            if (!service.isDone()) {
                String mengerjakan = service.doWork();
                return String.format("Nota %d : %s", getIdNota(), mengerjakan);
            }
        }
        if (isServiceDone()) {
                this.isDone = true;
            }   
        return String.format("Nota %d : Sudah selesai.", getIdNota());
    }

    public boolean isServiceDone() {
        for (LaundryService service: services) {
            if (!service.isDone()) {
                return false;
            }
        }
        return true;
    }

    public void toNextDay() {
        this.sisaHariPengerjaan--;
    }

    public long calculateHarga() {
        long harga = 0;

        harga += getHarga() * getBerat();

        for (LaundryService service: services) {
            harga += service.getHarga(getBerat());
        }

        if (!isDone || this.terlambat == true) {
            if (this.sisaHariPengerjaan < 0) {
                this.terlambat = true;
                harga -= (Math.abs(getSisaHariPengerjaan()) * 2000);
            }
        }

        if (harga < 0) {
            harga = 0;
        }
    
        return harga;
    }

    public String getNotaStatus() {
        if (isServiceDone()) {
            return String.format("Nota %d : Sudah selesai.", getIdNota());
        }
        return String.format("Nota %d : Belum selesai.", getIdNota());
    }
    
    @Override
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
    
    
    // Dibawah ini adalah getter
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
    
    public boolean isDone() {
        this.isDone = true;
        for (LaundryService service: services) {
            if (!service.isDone()) {
                this.isDone = false;
            }
        }
        return this.isDone;
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