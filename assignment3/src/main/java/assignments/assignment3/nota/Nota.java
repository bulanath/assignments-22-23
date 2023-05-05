package assignments.assignment3.nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[0];
    private long baseHarga;
    private long harga;
    private int sisaHariPengerjaan;
    private int hariKerja;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    private boolean terlambat;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggalMasuk) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggalMasuk;

        sisaHariPengerjaan = NotaGenerator.getHariPaket(paket);
        hariKerja = sisaHariPengerjaan;
        harga = NotaGenerator.getHargaPaket(paket);
    }

    public void addService(LaundryService service) {
        //TODO
        services = Arrays.copyOf(services, services.length + 1);
        services[services.length - 1] = service;
    }

    public String kerjakan() {
        // TODO
        String mengerjakan = "";
        for (LaundryService service: services) {
            if (service instanceof AntarService) {
                LaundryService antar = new AntarService();
                mengerjakan = antar.doWork();
            } else if (service instanceof CuciService) {
                LaundryService cuci = new CuciService();
                mengerjakan = cuci.doWork();
            } else {
                LaundryService setrika = new SetrikaService();
                mengerjakan = setrika.doWork();
            }
        }
        return mengerjakan;
    }

    public void toNextDay() {
        // TODO: masih error
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
    }

    public long calculateHarga() {
        // TODO
        baseHarga += getHarga() * getBerat();

        for (LaundryService service: services) {
            baseHarga += service.getHarga(getBerat());
        }

        if (!isDone) {
            if (sisaHariPengerjaan < 0) {
                baseHarga -= (Math.abs(getSisaHariPengerjaan()) * 2000);
                terlambat = true;
            }
        }
    
        return baseHarga;
    }

    public String getNotaStatus() {
        if (!isDone()) {
            return "Belum selesai.";
        }
        return "Sudah selesai.";
    }

    public void setIdNota(int id) {
        this.id = id;
    }

    public void setSisaHariPengerjaan() {
        this.sisaHariPengerjaan--;
    }
    
    @Override
    public String toString() {
        //TODO
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
        nota += String.format("ID   : %s\n", this.member.getId());
        nota += String.format("Paket : %s\n", getPaket());
        nota += "Harga :\n";
        nota += String.format("%d kg x %d = %d\n", getBerat(), getHarga(), getBerat() * getHarga());
        nota += String.format("Tanggal Terima  : %s\n", getTanggal());
        nota += String.format("Tanggal Selesai : %s\n", fmt.format(cal.getTime()));
        nota += String.format("--- SERVICE LIST ---\n");
        for (LaundryService service: services) {
            nota += String.format("- %s @ Rp.%d\n", service.getServiceName(), service.getHarga(getBerat()));
        }
        nota += String.format("Harga Akhir: %d ", calculateHarga());
        if (terlambat) {
            nota += String.format("Ada kompensasi keterlambatan %d * 2000 hari\n", Math.abs(sisaHariPengerjaan));
        }
        return nota;
    }
    
    // Dibawah ini adalah getter
    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }
    
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices() {
        return services;
    }

    public long getHarga() {
        return harga;
    }

    public int getIdNota() {
        return id;
    }
}