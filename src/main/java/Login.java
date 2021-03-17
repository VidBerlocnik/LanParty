import javax.swing.*;
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
    User user;

    public Login(){
        JFrame jframe = new JFrame("LAN Party login");
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
        setActionListeners();
    };

    private void setActionListeners() {
        loginButton.addActionListener(e -> {
            char[] password = passwordField1.getPassword();
            String pass = "";
            for (char letter: password
                 ) {
                pass = pass + letter;
            }

            user = new User(usernameTextField.getText(), pass);
            //System.out.println(password);
            System.out.println(user.Username);
            System.out.println(user.Password);
        });
    }
}
