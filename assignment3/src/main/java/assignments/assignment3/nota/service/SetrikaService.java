package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean selesaiSetrika = false;

    @Override
    public String doWork() {
        // TODO
        selesaiSetrika = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return selesaiSetrika;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long harga = berat * 1000;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
