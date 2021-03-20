import javax.swing.*;

public class Registration {
    private JPanel panel;
    private JButton registerButton;
    private JPasswordField passwordField1;
    private JTextField usernameTextField;
    private JLabel label1;
    private JLabel label3;
    private JTextField emailTextField;
    private JLabel label2;
    private JRadioButton genderRadioButton1;
    private JRadioButton genderRadioButton2;
    private JLabel label4;
    private JLabel label5;
    private JTextField dateOfBirthTextField;


    public Registration(){
        JFrame jframe = new JFrame("LAN Party registration");
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
    }

    private void setActionListeners() {
        registerButton.addActionListener(e -> {
            Database.Register();
        });
    }
}
