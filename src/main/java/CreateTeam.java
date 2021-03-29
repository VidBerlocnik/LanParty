import javax.swing.*;

public class CreateTeam
  {
    private JPanel panel;
    private JTextField teamsTextField;
    private JButton addTeamButton;
    private Teams teams;
    private JFrame jframe = new JFrame("LAN Party Create Teams");

    public CreateTeam(){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setActionListeners();
    }

    private void setActionListeners()
    {
        addTeamButton.addActionListener(e ->
     {
         String name = teamsTextField.getText();
         Teams teams = new Teams(name);
         jframe.setVisible(false);
     });
    };
  };