package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    //Menginisiasi atribut yang diperlukan dalam class Member
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp, String id) {
        //Membuat constructor untuk class Member
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
    }

    /**
     * Method getter untuk mengakses atribut dari class lain
     */
    public String getNama() {
        return this.nama;
    }

    public String getNoHP() {
        return this.noHp;
    }

    public String getId() {
        return this.id;
    }

    public int getBonusCounter() {
        return this.bonusCounter;
    }

    /**
     * Method untuk menghitung bonus setelah generate nota laundry
     */
    public void setBonusCounter() {
        this.bonusCounter ++;
    }

    /**
     * Method untuk me-reset hitungan bonus jika mendapat diskon
     */
    public void resetBonusCounter() {
        this.bonusCounter = 0;
    }
}