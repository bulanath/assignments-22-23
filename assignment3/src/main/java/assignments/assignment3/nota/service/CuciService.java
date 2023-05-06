package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    public boolean selesaiCuci = false;
    @Override
    public String doWork() {
        selesaiCuci = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        return selesaiCuci;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
