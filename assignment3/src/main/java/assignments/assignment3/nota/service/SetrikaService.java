package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean selesaiSetrika = false;

    @Override
    public String doWork() {
        selesaiSetrika = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return selesaiSetrika;
    }

    @Override
    public long getHarga(int berat) {
        long harga = berat * 1000;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
