import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login {
    private JPanel panel;
    private JPasswordField passwordField1;
    private JTextField usernameTextField;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton loginButton;
    private JButton registerButton;

    public Login(){
        JFrame jframe = new JFrame("LAN Party login");
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
    };

    private void setActionListeners(){
        loginButton.addActionListener(e -> {
            char[] password = passwordField1.getPassword();
            String pass = "";
            for (char letter: password
                 ) {
                pass = pass + letter;
            }

            User user = Database.VerifyLogin(usernameTextField.getText(), pass);
            if(user.Username == null){
                Messages.infoBox("Username and password does not match. If you don't have and account yet please register.", "Login failed");
            }
            else{
                new MainForm();
            }
        });

        registerButton.addActionListener(e -> {
            new Registration();
            //Add close login form
        });
    }
}