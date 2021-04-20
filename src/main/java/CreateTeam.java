import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
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

    public static String fileName;
    public boolean fileIfDelete = true;

    public CreateTeam(){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        fillComboBox();
        setActionListeners();

        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
                jframe.dispose();
            }
        });
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
                    Integer gameId = Database.GetGameID(gameComboBox.getSelectedItem().toString());
                    Database.CreateTeam(teamName, gameId, fileName);
                    Integer teamId = Database.GetTeamId(teamName);
                    Database.JoinTeam(teamId, user);
                    jframe.setVisible(false);
                    new PartiesDisplay(user);
                }
            }
        });

        addLogoButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, JPEG, and PNG Images", "jpg", "jpeg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(panel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("You chose to open this file: "
                        + file.getName());
                BufferedImage image;
                try {
                    image = ImageIO.read(file);
                    ImageIO.write(image, "jpg",new File("src\\img\\" + file.getName()));
                    ImageIO.write(image, "jpeg",new File("src\\img\\" + file.getName()));
                    ImageIO.write(image, "png",new File("src\\img\\" + file.getName()));
                    fileName = file.getName();
                    fileIfDelete = true;
                    imagePathLabel.setText(fileName);
                } catch (IOException ex) {
                    Logger.getLogger(CreateTeam.class.getName()).log(Level.SEVERE, null, ex);

                    //izpis errorja ce ne zberes slike
                    JOptionPane.showMessageDialog(panel,
                            "Niste izbrali slike!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void onExit() {
        if (fileIfDelete)
        {
            File file = new File("%USERPROFILE%" + fileName);
            System.out.print(file);
            //file.delete();
        }
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
