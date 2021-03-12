import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
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
}
