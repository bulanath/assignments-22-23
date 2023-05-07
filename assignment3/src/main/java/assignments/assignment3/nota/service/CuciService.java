package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    private boolean selesaiCuci = false;

    /**
     * Akan mengembalikan String yang menandakan bahwa Nota tersebut sedang dikerjakan.
     * Jika pernah dipanggil minimal sekali akan membuat method isDone mengembalikan true.
     */
    @Override
    public String doWork() {
        selesaiCuci = true;
        return "Sedang mencuci...";
    }

    /**
     * Akan bernilai true ketika method doWork() pernah dipanggil minimal sekali, selain itu akan bernilai false.
     */
    @Override
    public boolean isDone() {
        return selesaiCuci;
    }

    /**
     * Menghitung harga service cuci.
     */
    @Override
    public long getHarga(int berat) {
        return 0;
    }

    /**
     * Mengembalikan nama service.
     */
    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
