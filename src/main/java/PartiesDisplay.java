import javax.swing.*;
import java.util.ArrayList;

public class PartiesDisplay {
    private JPanel panel;
    private JList partiesList;
    private JComboBox gameComboBox;
    private JLabel label1;
    private JButton teamJoinPartyButton;
    private JFrame jframe = new JFrame("LAN Parties list");

    public PartiesDisplay(){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        fillComboBox();
        setActionListeners();
    }
    private void setActionListeners(){
        gameComboBox.addActionListener(e -> showParties());

        teamJoinPartyButton.addActionListener(e -> teamJoinParty());
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

    private void showParties(){
        ArrayList<Party> parties = Database.GetParties();

        DefaultListModel<Party> demoList = new DefaultListModel<>();

        for (Party party: parties
        ) {
            demoList.addElement(party);
        }

        partiesList.setModel(demoList);
    }

    private void teamJoinParty(){

    }
}
