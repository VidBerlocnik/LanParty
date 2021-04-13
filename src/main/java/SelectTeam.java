import javax.swing.*;
import java.util.ArrayList;

public class SelectTeam {
    private JPanel panel;
    private JList teamsList;
    private JComboBox gameComboBox;
    private JLabel label1;
    private JButton joinTeamButton;
    private JButton createTeamButton;
    private User user;
    private JFrame jframe = new JFrame("Select team");

    public SelectTeam(User uuser){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        fillComboBox();
        setActionListeners();
        user = uuser;
        showTeams();
    }


    private void setActionListeners() {
        gameComboBox.addActionListener(e -> showTeams());

        joinTeamButton.addActionListener(e -> {
            int teamId = Database.GetTeamId(teamsList.getSelectedValue().toString());
            Database.JoinTeam(teamId, user);
            new TeamsDisplay(user);
         });
        createTeamButton.addActionListener(e -> {
            new CreateTeam(user);
        });
    }

    private void fillComboBox(){
        ArrayList<String> games =  Database.GetGames();

        DefaultComboBoxModel<String> demoList = new DefaultComboBoxModel<>();

        for (String game: games
        ) {
            demoList.addElement(game);
        }
        gameComboBox.setModel(demoList);

        System.out.println("games demoList: " + demoList);
    }

    private void showTeams(){
        String gameName = gameComboBox.getSelectedItem().toString();

        ArrayList<String> teams =  Database.GetTeams(gameName);

        DefaultListModel<String> demoList = new DefaultListModel<>();

        for (String team: teams
        ) {
            demoList.addElement(team);
        }
        //teamsList = new JList(demoList);
        teamsList.setModel(demoList);

        //System.out.println("teams demoList: " + demoList);
    }

}
