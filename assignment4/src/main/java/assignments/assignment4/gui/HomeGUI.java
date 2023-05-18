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
    private Icon sleep = new ImageIcon(getClass().getResource("img/sleep.png"));

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

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
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");
        dateLabel = new JLabel(String.format("Hari ini: %s", fmt.format(cal.getTime())));
    
        // Increase the font size of the titleLabel
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        gbc.insets = new Insets(10, 10, 20, 10); //Add spacing around the components
        mainPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(20, 10, 20, 10); //Add spacing around the components
        mainPanel.add(loginButton, gbc);
        mainPanel.add(registerButton, gbc);
        mainPanel.add(toNextDayButton, gbc);
    
        gbc.insets = new Insets(15, 0, 0, 0); //Add spacing above the dateLabel
        mainPanel.add(dateLabel, gbc);

        registerButton.addActionListener(e -> handleToRegister());
        loginButton.addActionListener(e -> handleToLogin());
        toNextDayButton.addActionListener(e -> handleNextDay());
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);        
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();
        dateLabel.setText(String.format("Hari ini: %s", fmt.format(cal.getTime())));
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "Next Day", JOptionPane.INFORMATION_MESSAGE, sleep);
    }
}