import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private JFrame jframe = new JFrame("LAN Party login");

    public Login(){
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
            pass = doHashing(pass);

            User user = Database.VerifyLogin(usernameTextField.getText(), pass);
            if(user.Username == null){
                Messages.infoBox("Username and password does not match. If you don't have and account yet please register.", "Login failed");
            }
            else{
                if(hasTeam(user)){
                    new TeamsDisplay(user);
                    System.out.println("User has a team!");
                }
                else{
                    new SelectTeam(user);
                    System.out.println("User doesn't have a team!");
                }
                jframe.setVisible(false);
            }
        });

        registerButton.addActionListener(e -> {
            new Registration();
            jframe.setVisible(false);
        });
    }

    public static String doHashing (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean hasTeam(User user){
        if(user.TeamId == 0){
            return false;
        }
        else if(user.TeamId > 0){
            return true;
        }
        return false;
    }
}
