import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class MainForm {
    private JPanel panel;
    private JButton button1;

    public MainForm(){
        JFrame jframe = new JFrame("LAN Party");
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
    }

    private void setActionListeners() {
        button1.addActionListener(e ->
            new TeamsDisplay());
    }
}
