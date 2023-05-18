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
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
        idLabel = new JLabel("Masukan ID Anda:");
        idTextField = new JTextField(50);
        passwordLabel  = new JLabel("Masukan password Anda:");
        passwordField = new JPasswordField(50);
        loginButton = new JButton("Login");
        backButton = new JButton("Kembali");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        mainPanel.add(idLabel, gbc);
        gbc.gridy = 1;
        mainPanel.add(idTextField, gbc);
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridy = 3;
        mainPanel.add(passwordField, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 4;
        mainPanel.add(loginButton, gbc);
        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);

        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());
    } 

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String idStr = idTextField.getText();
        char[] passwordChar = passwordField.getPassword();
        String passwordStr = new String(passwordChar);
        boolean login = MainFrame.getInstance().login(idStr, passwordStr);

        if (!login) {
            JOptionPane.showMessageDialog(this, "Maaf, password atau username yang Anda masukan salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        } else {
            if (loginManager.getSystem(idStr) instanceof EmployeeSystem) {
                MainFrame.getInstance().navigateTo(EmployeeSystemGUI.KEY);
            } else if (loginManager.getSystem(idStr) instanceof MemberSystem) {
                MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
            }
        }

        idTextField.setText("");
        passwordField.setText("");
    }
}