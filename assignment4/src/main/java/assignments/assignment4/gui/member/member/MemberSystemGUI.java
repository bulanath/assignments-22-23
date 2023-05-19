package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     **/
    @Override
    protected JButton[] createButtons() {
        JButton laundryLabel = new JButton("Saya ingin laundry");
        JButton notaLabel = new JButton("Lihat detail nota saya");
        return new JButton[]{laundryLabel, notaLabel};
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
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     **/
    private void showDetailNota() {
        //Dialog yang dikeluarkan jika Member belum pernah membuat nota.
        if (loggedInMember.getNotaList().length == 0) {
            JOptionPane.showMessageDialog(this, "Anda belum pernah laundry di CuciCuci T_T", "Nota Kosong", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String notaStr = "";
        for (Nota nota: loggedInMember.getNotaList()) {
            notaStr += nota.toString();
            notaStr += "\n";
        }

        //Membuat text area untuk output nota.
        JTextArea notaOutput = new JTextArea(notaStr);
        notaOutput.setEditable(false);

        //Inisialisasi JScrollPane agar dialog bisa di-scroll.
        JScrollPane scroll = new JScrollPane(notaOutput);
        scroll.setFont(new Font("monospaced", Font.PLAIN, 12));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane optionPane = new JOptionPane(scroll, JOptionPane.INFORMATION_MESSAGE);
        optionPane.setPreferredSize(new Dimension(400, 300));

        JDialog dialog = optionPane.createDialog(this, "Detail Nota");
        dialog.setVisible(true);
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     **/
    private void createNota() {
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }
}