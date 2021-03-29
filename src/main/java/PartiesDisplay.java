import javax.swing.*;
import java.util.ArrayList;

public class PartiesDisplay {
    private JPanel panel;
    private JList partiesList;
    private JComboBox gameComboBox;
    private JLabel label1;
    private JFrame jframe = new JFrame("LAN Parties list");

    public PartiesDisplay(){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
    }


}
