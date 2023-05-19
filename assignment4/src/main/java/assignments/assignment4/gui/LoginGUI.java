package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
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
        idLabel = new JLabel("Masukan ID Anda:");
        idTextField = new JTextField(50);
        passwordLabel  = new JLabel("Masukan password Anda:");
        passwordField = new JPasswordField(50);
        loginButton = new JButton("Login");
        backButton = new JButton("Kembali");

        //Set up gbc.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Menambahkan elemen ke panel.
        gbc.gridy = 0;
        gbc.gridx = 0;
        mainPanel.add(idLabel, gbc);
        gbc.gridy = 1;
        mainPanel.add(idTextField, gbc);
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridy = 3;
        mainPanel.add(passwordField, gbc);

        //Set up gbc untuk button dan menambahkannya ke panel.
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 4;
        mainPanel.add(loginButton, gbc);
        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);

        //Menambahkan action listener untuk button menggunakan lambda.
        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());
    } 

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     **/
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     **/
    private void handleLogin() {
        String idStr = idTextField.getText();
        char[] passwordChar = passwordField.getPassword();
        String passwordStr = new String(passwordChar);

        //Mengecek apakah id dan password pengguna benar.
        boolean login = MainFrame.getInstance().login(idStr, passwordStr);

        if (!login) {
            JOptionPane.showMessageDialog(this, "Password atau username yang Anda masukan salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        } else {
            //Menuju ke GUI EmployeeSystem/MemberSystem sesuai dengan instance milik user.
            if (loginManager.getSystem(idStr) instanceof EmployeeSystem) {
                MainFrame.getInstance().navigateTo(EmployeeSystemGUI.KEY);
            } else if (loginManager.getSystem(idStr) instanceof MemberSystem) {
                MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
            }
        }

        //Clear text field.
        idTextField.setText("");
        passwordField.setText("");
    }
}