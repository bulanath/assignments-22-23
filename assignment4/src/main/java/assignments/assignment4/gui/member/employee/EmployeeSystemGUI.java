package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";
    private Icon bored = new ImageIcon(getClass().getResource("../../img/bored.png"));
    private Icon smiling = new ImageIcon(getClass().getResource("../../img/smiling.png"));
    private Icon info = new ImageIcon(getClass().getResource("../../img/info.png"));

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     **/
    @Override
    protected JButton[] createButtons() {
        JButton cuciLabel = new JButton("It's nyuci time");
        JButton notaLabel = new JButton("Display List Nota");
        return new JButton[]{cuciLabel, notaLabel};
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     **/
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     **/
    private void displayNota() {
        String notaStr = "";
        for (Nota nota: NotaManager.notaList) {
            notaStr += nota.getNotaStatus();
            notaStr += "\n";
        }

        //Output jika notaList masih kosong.
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE, bored);
            return;
        }
        JOptionPane.showMessageDialog(this, notaStr, "List Nota", JOptionPane.INFORMATION_MESSAGE, info);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     **/
    private void cuci() {
        JOptionPane.showMessageDialog(this, String.format("Stand back! %s beginning to nyuci! \n", loggedInMember.getNama()), "Nyuci Time", JOptionPane.INFORMATION_MESSAGE, smiling);
        
        String notaStr = "";
        for (Nota nota: NotaManager.notaList) {
            notaStr += nota.kerjakan();
            notaStr += "\n";
        }
        
        //Output jika notaList masih kosong.
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "Nothing to cuci here", "Nyuci Result", JOptionPane.ERROR_MESSAGE, bored);
            return;
        }
        JOptionPane.showMessageDialog(this, notaStr, "Nyuci Result", JOptionPane.INFORMATION_MESSAGE, info);
    }
}
