package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    public boolean selesaiAntar = false;

    @Override
    public String doWork() {
        selesaiAntar = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return selesaiAntar;
    }

    @Override
    public long getHarga(int berat) {
        long harga = 2000;
        if (berat > 4) {
            harga += ((berat-4) * 500);
        }
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
