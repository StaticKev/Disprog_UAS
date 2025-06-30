package TicketApp.models;

import TicketApp.util.DBConnection;
import TicketApp.util.WSLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class User extends Model {
    // <editor-fold>
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Purchase> purchasedTicket;
    private List<Notification> notifications;
    private List<Ticket> favorite;
    // </editor-fold>

    // <editor-fold>
    public User() {
        setUsername("");
        setEmail("");
        setPassword("");
        this._getConnection();
    }
    
    public User(String _username, String _password){
        setUsername(_username);
        setPassword(_password);
        this._getConnection();
    }

    public User(String _username, String _password, String _email) {
        setUsername(_username);
        setPassword(_password);
        setEmail(_email);
        this._getConnection();
    }

    public User(int _id, String _username, String _password, String _email){
        setId(_id);
        setUsername(_username);
        setPassword(_password);
        setEmail(_email);
    }
    // </editor-fold>
    
    // <editor-fold>
        public static User read_User(User user) {
        User result = null;
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = new User(
                            rs.getInt("id"),
                            user.getUsername(),
                            user.getPassword(),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        
        return result;
    }
    
    public Boolean read_User() {
        try {
            if (!Model.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) Model.conn.prepareStatement("SELECT * FROM users where username = ? and password = ?");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                result = sql.executeQuery();

                if (result.next()) {
                    sql.close();
                    return true;
                } else {
                    sql.close();
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println("Ada error di Insert data: " + ex.getMessage());
        }
        return false;
    }
    public Boolean read_UserIsExist(){
        try {
            if (!Model.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) Model.conn.prepareStatement("SELECT * FROM users where email = ?");
                sql.setString(1, this.email);
                ResultSet res = sql.executeQuery();
                boolean count = res.next();
                res.close();
                sql.close();
                if (count) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Ada error di Insert data: " + ex.getMessage());
        }
        return false;
    }
    @Override
    public void insertData() {
        try {
            if (!Model.conn.isClosed()) {
                PreparedStatement insertsql = (PreparedStatement) Model.conn.prepareStatement("Insert INTO users(username, email, password)"
                        + "VALUES (?, ?, ?)");
                insertsql.setString(1, this.username);
                insertsql.setString(2, this.email);
                insertsql.setString(3, this.password);
                int result = insertsql.executeUpdate();
                System.out.println(result);
                System.out.println(this.username + "," + this.email + "," + this.password);
                if (result > 0) {
                    insertsql.close();
                } else {
                    insertsql.close();
                }
            } else {
                System.out.println("Error");
            }
        } catch (Exception ex) {
            System.out.println("Ada error di Insert data: " + ex.getMessage());
        }
    }

    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Object> viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    // </editor-fold>

    // <editor-fold>
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Purchase> getPurchasedTicket() {
        return purchasedTicket;
    }

    public void setPurchasedTicket(List<Purchase> purchasedTicket) {
        this.purchasedTicket = purchasedTicket;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Ticket> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Ticket> favorite) {
        this.favorite = favorite;
    }
    // </editor-fold>

}