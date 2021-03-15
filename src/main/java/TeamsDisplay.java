import javax.swing.*;

public class TeamsDisplay {
    private JPanel panel;
    private JList teamsList;
    private JComboBox gameComboBox;
    private JLabel label1;

    public TeamsDisplay{
        JFrame jframe = new JFrame("Display teams");
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
    }

}
