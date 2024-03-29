package assignments.assignment4.gui;

import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

import static assignments.assignment3.nota.NotaManager.toNextDay;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        //Set up layout.
        super(new BorderLayout()); 

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
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");
        dateLabel = new JLabel(String.format("Hari ini: %s", fmt.format(cal.getTime())));
    
        //Menambah size titleLable.
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        
        //Set up gbc.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        
        //Menambahkan elemen ke panel.
        gbc.insets = new Insets(10, 10, 20, 10);
        mainPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(20, 10, 20, 10);
        mainPanel.add(loginButton, gbc);
        mainPanel.add(registerButton, gbc);
        mainPanel.add(toNextDayButton, gbc);
    
        gbc.insets = new Insets(15, 0, 0, 0);
        mainPanel.add(dateLabel, gbc);

        //Menambahkan action listener untuk button menggunakan lambda.
        registerButton.addActionListener(e -> handleToRegister());
        loginButton.addActionListener(e -> handleToLogin());
        toNextDayButton.addActionListener(e -> handleNextDay());
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     **/
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);        
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     **/
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     **/
    private void handleNextDay() {
        toNextDay();
        dateLabel.setText(String.format("Hari ini: %s", fmt.format(cal.getTime())));
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "Next Day", JOptionPane.INFORMATION_MESSAGE);
    }
}