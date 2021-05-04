import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    public static Connection connect() {
        DB db = new DB();
        String url = db.url;
        String user = db.user;
        String password = db.password;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            Messages.databaseConnectionFailed(db.database, e.getMessage());
        }
        return con;
    }

    public static int GetGameID(String gameName){
        int vrni = 0;
        String cmd = "SELECT id FROM games WHERE(name = '" + gameName + "');";

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                vrni = set.getInt("id");
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return vrni;
    }

    public static int GetTeamId(String teamName){
        int teamId = 0;
        String cmd = "SELECT id FROM teams WHERE(name = '" + teamName + "');";

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                teamId = set.getInt("id");
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
        return teamId;
    }


    public static ArrayList<String> GetTeams(String gameName) {
        String cmd = "SELECT * FROM teams t INNER JOIN games g ON t.game_id = g.id " +
                "WHERE(t.game_id = "+ GetGameID(gameName) + ")";
        ArrayList<String> teams = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                teams.add(set.getString("name"));
            }
        }
            catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return teams;
    }

    public static ArrayList<String> GetGames() {
        String cmd = "SELECT * FROM games";
        ArrayList<String> games = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                games.add(set.getString("name"));
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return games;
    }

    public static User VerifyLogin(String username, String password){
        String cmd = "SELECT * FROM users WHERE((username = '" + username + "') AND (password = '" + password + "'));";
        User returnedUser = new User();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                returnedUser.Id = set.getInt("id");
                returnedUser.Username = set.getString("username");
                returnedUser.TeamId = set.getInt("team_id");
                returnedUser.Password = set.getString("password");
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return returnedUser;
    }

    public static void Register(User user){
        String cmd = "INSERT INTO users(username, email, password, birth_date, gender)" +
                "VALUES ('" + user.Username + "', '" + user.Email + "', '" + user.Password + "', '" + user.DateOfBirth + "', '" + user.Gender + "');";

        try (Connection con = connect();
             Statement st = con.createStatement()) {
            ResultSet set = st.executeQuery(cmd);
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> GetPlayers(String teamName){
        ArrayList<String> players = new ArrayList<>();

        String cmd = "SELECT username FROM users INNER JOIN teams t on users.team_id = t.id WHERE (t.name = '" + teamName + "');";

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                players.add(set.getString("username"));
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return players;
    }

    public static void JoinTeam(int teamId, User user){
        String cmd = "UPDATE users SET team_id = " + teamId + " WHERE(id = " + user.Id + ");";

        try (Connection con = connect();
             Statement st = con.createStatement()) {
            ResultSet set = st.executeQuery(cmd);
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Party> GetParties(){
        String cmd = "SELECT * FROM parties;";
        ArrayList<Party> parties = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                Integer id = set.getInt("id");
                String name = set.getString("name");
                String date = set.getString("date");
                Party party = new Party(id, name, date);
                parties.add(party);
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return parties;
    }

    public static void JoinParty(Integer teamId, Integer partyId){
        String cmd = "INSERT INTO party_team (party_id, team_id) VALUES (" + partyId + ", " + teamId + ");";
        ArrayList<Party> parties = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                Integer id = set.getInt("id");
                String name = set.getString("name");
                String date = set.getString("date");
                Party party = new Party(id, name, date);
                parties.add(party);
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static Integer GetPartyId(String partyName, String partyDate){
        String cmd = "SELECT id FROM parties WHERE (name = '" + partyName + "') AND (date = '" + partyDate + "');";
        Integer id = 0;

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                id = set.getInt("id");
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
        return id;
    }

    public static void CreateTeam(String teamName, Integer gameId, String logoPath){
        String cmd = "INSERT INTO teams (name, game_id, logo)" +
                "VALUES ('" + teamName + "', " + gameId + ", '" + logoPath + "')";

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
    }
 
  public static Integer GetPartiesNumber(){
        String cmd = "SELECT parties FROM statistics;";
        Integer x = 0;

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                x = set.getInt("parties");
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }

        return x;
    }
  
  public static Integer GetTeamsParticipation(){
        String cmd = "SELECT teamsparticipating FROM statistics;";
        Integer y = 0;

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                y = set.getInt("teamsparticipating");
            }
        }
        catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println(e.getMessage());
        }
        return y;
    }
}
