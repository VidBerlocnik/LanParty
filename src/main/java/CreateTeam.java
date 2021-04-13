import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CreateTeam {
    private JPanel panel;
    private JTextField teamNameTextField;
    private JLabel label1;
    private JButton createTeamButton;
    private JButton addLogoButton;
    private JLabel label2;
    private JLabel imagePathLabel;
    private JComboBox gameComboBox;
    private JLabel label3;
    private JFrame jframe = new JFrame("LAN Create team");
    private User user;

    public CreateTeam(){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        fillComboBox();
        setActionListeners();
    }

    public CreateTeam(User userr){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        fillComboBox();
        setActionListeners();
        user = userr;
    }

    private void setActionListeners(){
        createTeamButton.addActionListener(e -> {
            if(!teamNameTextField.getText().isEmpty()){
                if(imagePathLabel.getText() != "Image not selected"){
                    String teamName = teamNameTextField.getText();
                    String logoPath = "Dodaj kodo";
                    Integer gameId = Database.GetGameID(gameComboBox.getSelectedItem().toString());
                    Database.CreateTeam(teamName, gameId, logoPath);
                    Integer teamId = Database.GetTeamId(teamName);
                    Database.JoinTeam(teamId, user);
                    //get logo path and save image
                }
            }
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
}
