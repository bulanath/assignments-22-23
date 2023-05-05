package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Arrays;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //TODO: implement skip hari
        for (Nota nota: notaList) {
            nota.toNextDay();
            nota.setSisaHariPengerjaan();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        //TODO: implement add nota
        notaList = Arrays.copyOf(notaList, notaList.length + 1);
        notaList[notaList.length - 1] = nota;
    }
}