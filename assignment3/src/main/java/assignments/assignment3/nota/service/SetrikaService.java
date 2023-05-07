package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean selesaiSetrika = false;

    /**
     * Akan mengembalikan String yang menandakan bahwa Nota tersebut sedang dikerjakan.
     * Jika pernah dipanggil minimal sekali akan membuat method isDone mengembalikan true.
     */
    @Override
    public String doWork() {
        selesaiSetrika = true;
        return "Sedang menyetrika...";
    }

    /**
     * Akan bernilai true ketika method doWork() pernah dipanggil minimal sekali, selain itu akan bernilai false.
     */
    @Override
    public boolean isDone() {
        return selesaiSetrika;
    }

    /**
     * Menghitung harga service setrika.
     */
    @Override
    public long getHarga(int berat) {
        long harga = berat * 1000;
        return harga;
    }

    /**
     * Mengembalikan nama service.
     */
    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
