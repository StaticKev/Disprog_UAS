package com.TicketApp.model;

import com.TicketApp.Utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
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
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        purchasedTicket = new ArrayList<>(); // HARUS DIGANTI
        notifications = new ArrayList<>(); // HARUS DIGANTI
        favorite = new ArrayList<>(); // HARUS DIGANTI
    }
    // </editor-fold>
    
    // <editor-fold>
    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public List<Purchase> getPurchasedTicket() { return purchasedTicket; }

    public List<Notification> getNotifications() { return notifications; }
    
    public List<Ticket> getFavorite() { return favorite; }
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
                while(rs.next()) {
                    result = new User(
                            rs.getInt("id"),
                            user.getUsername(),
                            user.getPassword(),
                            rs.getString("email")
                    );
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public static boolean read_AccountIsExist(String email, String username) {
        boolean result = false;
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) AS `c` FROM users WHERE email = ? AND username = ?;";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, username);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) result = rs.getInt("c") != 0;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public static boolean insert_User(User user) {
        boolean result = false;
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO `users` (`username`, `password`, `email`) VALUES (?, ?, ?);";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());

                int ru = ps.executeUpdate();
                System.out.println(ru);
                result = ru != 0;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    // </editor-fold>
}