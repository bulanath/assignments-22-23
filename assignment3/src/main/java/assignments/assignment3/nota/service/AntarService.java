package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    private boolean selesaiAntar = false;

    /**
     * Akan mengembalikan String yang menandakan bahwa Nota tersebut sedang dikerjakan.
     * Jika pernah dipanggil minimal sekali akan membuat method isDone mengembalikan true.
     */
    @Override
    public String doWork() {
        selesaiAntar = true;
        return "Sedang mengantar...";
    }

    /**
     * Akan bernilai true ketika method doWork() pernah dipanggil minimal sekali, selain itu akan bernilai false.
     */
    @Override
    public boolean isDone() {
        return selesaiAntar;
    }

    /**
     * Menghitung harga service antar.
     */
    @Override
    public long getHarga(int berat) {
        long harga = 2000;
        if (berat > 4) {
            harga += ((berat-4) * 500);
        }
        return harga;
    }

    /**
     * Mengembalikan nama service.
     */
    @Override
    public String getServiceName() {
        return "Antar";
    }
}
