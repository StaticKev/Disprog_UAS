package TicketApp.models;

import TicketApp.util.DBConnection;
import TicketApp.util.WSLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;

public class Notification {
    // <editor-fold>
    private int id;
    private String message;
    private LocalDateTime sentOn;
    private boolean read;
    // </editor-fold>
    
    // <editor-fold>
    public Notification(String message) {
        this.message = message;
        sentOn = LocalDateTime.now();
        read = false;
    }
    public Notification(int id, String message, LocalDateTime sentOn, boolean read) {
        this.id = id;
        this.message = message;
        this.sentOn = sentOn;
        this.read = read;
    }
    // </editor-fold>

    // <editor-fold>
    public int getId() { return id; }

    public String getMessage() { return message; }

    public LocalDateTime getSentOn() { return sentOn; }

    public boolean getRead() { return read; }

    private void setRead(boolean read) { this.read = read; }
    // </editor-fold>
    
    // <editor-fold>
    public static List<Notification> read_notificationByUid(User user) {
        List<Notification> result = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM notifications WHERE users_id = ?;";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getId());
                
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    result.add(new Notification(
                            rs.getInt("id"),
                            rs.getString("message"),
                            rs.getObject("sent_on", LocalDateTime.class),
                            rs.getBoolean("read")
                    ));
                }
            }
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        
        return result;
    }
    
    public static boolean insert_notification(Notification n, User u) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO notifications (message, sent_on, read, users_id) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, n.getMessage());
                ps.setTimestamp(2, Timestamp.valueOf(n.getSentOn()));
                ps.setBoolean(3, n.getRead());
                ps.setInt(4, u.getId());
                
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        
        return false;
    }
    
    public static boolean update_notification(Notification n) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE notifications SET read = 1 WHERE id = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, n.getId());
                
                return ps.executeUpdate() == 1;
            } 
            
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        
        return false;
    }
    // </editor-fold>
}