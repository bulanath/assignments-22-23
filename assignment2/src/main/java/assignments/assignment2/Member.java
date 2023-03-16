package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp, String id) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
    }

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

    public void setBonusCounter() {
        this.bonusCounter ++;
    }

    public void resetBonusCounter() {
        this.bonusCounter = 0;
    }
}