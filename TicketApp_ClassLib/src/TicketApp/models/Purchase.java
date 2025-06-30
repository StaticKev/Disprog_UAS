package TicketApp.models;

import TicketApp.util.DBConnection;
import TicketApp.util.WSLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Purchase {
    // <editor-fold>
    private int id;
    private int qty;
    private Status status;
    private LocalDateTime pOn;
    private Ticket ticket;
    // </editor-fold>
    
    // <editor-fold>
    public Purchase(int qty, Ticket ticket) {
        this.qty = qty;
        this.status = Status.BOUGHT;
        pOn = LocalDateTime.now();
        this.ticket = ticket;
    }
    public Purchase(int id, int qty, Status status, LocalDateTime pOn, Ticket ticket) {
        this.id = id;
        this.qty = qty;
        this.status = status;
        this.pOn = pOn;
        this.ticket = ticket;
    }
    // </editor-fold>
    
    // <editor-fold>
    public int getId() { return id; }

    public int getQty() { return qty; }

    public LocalDateTime getpOn() { return pOn; }
    
    public Status getStatus() { return status; }
    
    public void setStatus(Status status) { this.status = status; }

    private void setpOn(LocalDateTime pOn) { this.pOn = pOn; }

    public Ticket getTicket() { return ticket; }
    
    private void setTicket(Ticket ticket) { this.ticket = ticket; }
    // </editor-fold>
    
    // <editor-fold>
    public static List<Purchase> read_PurchaseByUser(User user) {
        List<Purchase> result = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT p.id AS purchase_id, p.qty, p.purchased_on, "
                    + "p.status, p.ticket_id, t.id AS ticket_id, "
                    + "t.title, t.location, t.price, t.event_date, t.category, "
                    + "t.sale_start, t.sale_ends, t.flash_sale_price, "
                    + "t.flash_sale_on, t.flash_sale_ends "
                    + "FROM purchase p "
                    + "INNER JOIN tickets t ON p.ticket_id = t.id "
                    + "WHERE p.users_id = ?;";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getId());
                
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Ticket t = new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("location"),
                        rs.getDouble("price"),
                        rs.getObject("event_date", LocalDate.class),
                        Category.valueOf(rs.getString("category")),
                        rs.getObject("sale_start", LocalDateTime.class),
                        rs.getObject("sale_ends", LocalDateTime.class),
                        rs.getDouble("flash_sale_price"),
                        rs.getObject("flash_sale_on", LocalDateTime.class),
                        rs.getObject("flash_sale_ends", LocalDateTime.class)
                    );
                    
                    result.add(new Purchase(
                            rs.getInt("purchase_id"),
                            rs.getInt("qty"),
                            Status.valueOf(rs.getString("status")),
                            rs.getObject("purchased_on", LocalDateTime.class),
                            t
                    ));
                }
            }
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        
        return result;
    }
    
    public static boolean insert_Purchase(Purchase purchase, User user) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO purchase (qty, purchased_on, status, users_id, ticket_id) VALUES (?, ?, ?, ?, ?);";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, purchase.getQty());
                ps.setTimestamp(2, Timestamp.valueOf(purchase.getpOn()));
                ps.setString(3, purchase.getStatus().toString());
                ps.setInt(4, user.getId());
                ps.setInt(5, purchase.getTicket().getId());
                
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        return false;
    }
    
    public static boolean update_Purchase(Purchase purchase) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE purchase SET status = ? WHERE id = ?;";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, purchase.getStatus().toString());
                ps.setInt(2, purchase.getId());
                
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            WSLogger.log(e, Level.SEVERE);
        }
        
        return false;
    }
    // </editor-fold>
}