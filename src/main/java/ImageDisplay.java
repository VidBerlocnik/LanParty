import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ImageDisplay {
    private JPanel panel;
    private JLabel lPhoto;
    private JFrame jframe = new JFrame("Image display");

    public ImageDisplay(String teamName){
        jframe.setContentPane(panel);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.pack();
        jframe.setSize(600,400); // change these
        jframe.setVisible(true);
        setImage(teamName);
    }

    public static Connection connect() {
        String url = "jdbc:postgresql://ec2-52-213-167-210.eu-west-1.compute.amazonaws.com:5432/d7cv2ut5vo5cij";
        String user = "ldxwthgxjzmesj";
        String password = "a52368b3eb287186c1da8c9123ade07b361bc07ab097fbe7463a56188c8bbb0c";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            Messages.databaseConnectionFailed("d7cv2ut5vo5cij", e.getMessage());
        }
        return con;
    }

    public void setImage(String teamName){
        String cmd = "SELECT logo FROM teams WHERE(name = '" + teamName + "');";

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            String image = null;
            while (set.next()) {
                image = set.getString("logo");
            }

            ImageIcon slika = new ImageIcon("src\\img\\" + image);

            Image imagee = slika.getImage();
            Image newimg = imagee.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(newimg);

            lPhoto.setIcon(img);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
