package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        //Set up layout.
        super(new BorderLayout());
        this.loginManager = loginManager;

        //Set up main panel.
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     **/
    private void initGUI() {
        //Inisialisasi elemen yang dibutuhkan.
        nameLabel = new JLabel("Masukan nama Anda:");
        nameTextField = new JTextField(50);
        phoneLabel = new JLabel("Masukan nomor handphone Anda:");
        phoneTextField = new JTextField(50);
        passwordLabel = new JLabel("Masukan password Anda:");
        passwordField = new JPasswordField(50);
        registerButton = new JButton("Register");
        backButton = new JButton("Kembali");

        //Set up gbc.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Menambahkan elemen ke panel.
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(nameLabel, gbc);
        gbc.gridy = 1;
        mainPanel.add(nameTextField, gbc);
        gbc.gridy = 2;
        mainPanel.add(phoneLabel, gbc);
        gbc.gridy = 3;
        mainPanel.add(phoneTextField, gbc);
        gbc.gridy = 4;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridy = 5;
        mainPanel.add(passwordField, gbc);

        //Set up gbc untuk button dan menambahkannya ke panel.
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 6;
        mainPanel.add(registerButton, gbc);
        gbc.gridy = 7;
        mainPanel.add(backButton, gbc);

        //Menambahkan action listener untuk button menggunakan lambda.
        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> handleBack());
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     **/
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    **/
    private void handleRegister() {
        //Mengecek apakah ada field yang kosong.
        char[] passwordChar = passwordField.getPassword();
        String passwordStr = new String(passwordChar);
        String nameStr = nameTextField.getText();
        String phoneStr = phoneTextField.getText();
        if (nameStr.isEmpty() || phoneStr.isEmpty() || passwordStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, String.format("Semua field di atas wajib diisi!"), "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Mengecek apakah nomor handphone berupa digit atau tidak.
        boolean cekPhone = phoneStr.matches(".*\\D.*");
        if (cekPhone) {
            JOptionPane.showMessageDialog(this, String.format("Nomor handphone harus berisi angka!"), "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
            return;
        }
        
        //Membuat objek Member & mengecek apakah member belum ada atau duplikat.
        Member registeredMember = loginManager.register(nameStr, phoneStr, passwordStr);
        if (registeredMember == null) {
            JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada!", nameStr, phoneStr), "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, String.format("Berhasil membuat user dengan ID %s!", registeredMember.getId()), "Registrasi Sukses", JOptionPane.INFORMATION_MESSAGE);
        }

        //Kembali ke HomeGUI dan clear text field.
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }
}