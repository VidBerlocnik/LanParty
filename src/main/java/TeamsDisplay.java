import javax.swing.*;
import java.util.ArrayList;

public class TeamsDisplay {
    private JPanel panel;
    private JList teamsList;
    private JComboBox gameComboBox;
    private JLabel label1;

    public TeamsDisplay(){
        JFrame jframe = new JFrame("Display teams");
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
        showTeams();
    }

    private void setActionListeners() {

    }
    private void fillComboBox(){

    }
    private void showTeams(){
        ArrayList<String> teams =  Database.GetTeams();

        DefaultListModel demoList = new DefaultListModel();

        for (String team: teams
             ) {
            demoList.addElement(team);
        }
        teamsList = new JList(demoList);

    }
}
