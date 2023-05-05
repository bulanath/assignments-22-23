package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    public boolean selesaiCuci = false;
    @Override
    public String doWork() {
        // TODO
        selesaiCuci = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return selesaiCuci;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
