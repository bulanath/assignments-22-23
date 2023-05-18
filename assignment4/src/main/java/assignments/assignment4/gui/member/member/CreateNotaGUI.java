package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JPanel mainPanel;
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        paketLabel = new JLabel("Paket Laundry:");
        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        showPaketButton = new JButton("Show Paket");
        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratTextField = new JTextField();
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)");
        createNotaButton = new JButton("Buat Nota");
        backButton = new JButton("Kembali");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.gridx = 0;
        mainPanel.add(paketLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(paketComboBox, gbc);
        gbc.gridx = 2;
        mainPanel.add(showPaketButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        mainPanel.add(beratLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(beratTextField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(setrikaCheckBox, gbc);
        gbc.gridy = 3;
        mainPanel.add(antarCheckBox, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 3;
        gbc.gridy = 4;
        mainPanel.add(createNotaButton, gbc);
        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);

        showPaketButton.addActionListener(e -> showPaket());
        createNotaButton.addActionListener(e -> createNota());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        String beratStr = beratTextField.getText();
        boolean cekBerat = beratStr.matches(".*\\D.*");
        if (cekBerat || Integer.parseInt(beratStr) < 1) {
            JOptionPane.showMessageDialog(this, String.format("Berat cucian harus berisi angka!"), "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
            return;
        }

        int beratInt = Integer.parseInt(beratStr);
        if (beratInt < 2) {
            JOptionPane.showMessageDialog(this, String.format("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg"), "Info", JOptionPane.INFORMATION_MESSAGE);
            beratInt = 2;
        } 

        String tanggalMasuk = fmt.format(cal.getTime());

        String paket = (String) paketComboBox.getSelectedItem();

        Member loggedinMember = memberSystemGUI.getLoggedInMember();

        Nota nota = new Nota(loggedinMember, beratInt, paket, tanggalMasuk);

        if (setrikaCheckBox.isSelected()) {
            nota.addService(new SetrikaService());
        }

        if (antarCheckBox.isSelected()) {
            nota.addService(new AntarService());
        }

        loggedinMember.addNota(nota);
        NotaManager.addNota(nota);

        JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }
}