import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
    private JFrame jframe = new JFrame("LAN Party registration");


    public Registration(){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
    }

    private void setActionListeners() {
        registerButton.addActionListener(e -> {
            String username = usernameTextField.getText();

            char[] password = passwordField1.getPassword();
            String pass = "";
            for (char letter: password
            ) {
                pass = pass + letter;
            }

            //password encription
            pass = doHashing(pass);


            String dateofbirth = dateOfBirthTextField.getText(); //If user inserts in wrong format it crashes

            String email = emailTextField.getText();
            Character gender = null;

            if(genderRadioButton1.isSelected()){
                gender = 'm';
            }
            else if(genderRadioButton2.isSelected()){
                gender = 'f';
            }

            User user = new User(username, pass, dateofbirth, email, gender);
            Database.Register(user);
            new Login();
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
}
