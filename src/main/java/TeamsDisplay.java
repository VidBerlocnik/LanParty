import javax.swing.*;
import java.util.ArrayList;

public class TeamsDisplay {
    private JPanel panel;
    private JList<String> teamsList;
    private JComboBox<String> gameComboBox;
    private JLabel label1;
    private JList playersList;
    private JLabel label2;
    private JLabel label3;
    private JButton partiesButton;
    private JButton statsButton;
    JFrame jframe = new JFrame("Display teams");
    User loggedUser;

    public TeamsDisplay(User Loggeduser){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
        fillComboBox();
        showTeams();
        loggedUser = Loggeduser;
    }

    private void setActionListeners() {
        gameComboBox.addActionListener(e -> showTeams());

        teamsList.addListSelectionListener(e -> showPlayers());

        partiesButton.addActionListener(e -> {
            new PartiesDisplay(loggedUser);
            jframe.setVisible(false);
        });

        statsButton.addActionListener(e -> new Statistics(loggedUser));
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

        System.out.println("teams demoList: " + demoList);
    }

    private void showPlayers(){
        String teamName = teamsList.getSelectedValue();

        ArrayList<String> players =  Database.GetPlayers(teamName);

        DefaultListModel<String> demoList = new DefaultListModel<>();

        for (String player: players
        ) {
            demoList.addElement(player);
        }
        //teamsList = new JList(demoList);
        playersList.setModel(demoList);

        System.out.println("players demoList: " + demoList);
    }
}
