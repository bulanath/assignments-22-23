package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    public boolean selesaiAntar = false;

    @Override
    public String doWork() {
        // TODO
        selesaiAntar = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return selesaiAntar;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
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
