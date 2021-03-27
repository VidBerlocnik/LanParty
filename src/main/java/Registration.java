import javax.swing.*;
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

            //Gets password from textbox and converts from char to string
            char[] password = passwordField1.getPassword();
            String pass = "";
            for (char letter: password
            ) {
                pass = pass + letter;
            }

            String email = emailTextField.getText();
            Character gender = null;
            String dateofbirth = dateOfBirthTextField.getText();

            if(genderRadioButton1.isSelected()){
                gender = 'm';
            }
            else if(genderRadioButton2.isSelected()){
                gender = 'f';
            }

            if(dateFormatCheck(dateofbirth)){
                dateofbirth = dateFormatConvert(dateofbirth);
                User user = new User(username, pass, dateofbirth, email, gender);
                Database.Register(user);
                new Login();
                jframe.setVisible(false);
            }
            else{
                Messages.infoBox("Date is written in wrong format. Please check format and try again.", "Wrong date format");
            }
        });
    }

    private boolean dateFormatCheck(String stringDate){
        String[] num = stringDate.split("-");
        int day = Integer.parseInt(num[0]);
        int month = Integer.parseInt(num[1]);
        int year = Integer.parseInt(num[2]);

        if(day > 31 || day < 1){
            return false;
        }
        else if(month > 12 || month < 1){
            return false;
        }
        else if(year > 2020 || year < 1900){
            return false;
        }
        else{
            return true;
        }
    }

    private String dateFormatConvert(String date){
        String dateReturn;
        String[] num = date.split("-");
        int day = Integer.parseInt(num[0]);
        int month = Integer.parseInt(num[1]);
        int year = Integer.parseInt(num[2]);

        dateReturn = Integer.toString(year) + "/" +Integer.toString(month) + "/" + Integer.toString(day);
        return dateReturn;
    }
}
