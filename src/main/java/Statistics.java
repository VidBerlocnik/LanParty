import javax.swing.*;

public class Statistics {
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel numberOfPartiesLabel;
    private JLabel numberOfTeamsLabel;
    private JLabel label3;
    private JLabel averageTeamsLabel;
    private JButton backButton;
    private JFrame jframe = new JFrame("LAN Party Statistics");
    User loggedUser;

    public Statistics(User loggedUserr){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        loggedUser = loggedUserr;
        setActionListeners();
    }

    private void setActionListeners(){
        backButton.addActionListener(e -> new TeamsDisplay(loggedUser));
    }
}
