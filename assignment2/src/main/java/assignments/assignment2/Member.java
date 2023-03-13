package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp, String id) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getNama() {
        return this.nama;
    }

    public String getNoHP() {
        return this.noHp;
    }

    public String getId() {
        return this.id;
    }
}
