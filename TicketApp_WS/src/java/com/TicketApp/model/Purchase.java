package com.TicketApp.model;

import com.TicketApp.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    // <editor-fold>
    private int id;
    private int qty;
    private LocalDateTime pOn;
    private Ticket ticket;
    // </editor-fold>
    
    // <editor-fold>
    public Purchase(int qty, Ticket ticket) {
        this.qty = qty;
        pOn = LocalDateTime.now();
        this.ticket = ticket;
    }
    public Purchase(int id, int qty, LocalDateTime pOn, Ticket ticket) {
        this.id = id;
        this.qty = qty;
        this.pOn = pOn;
        this.ticket = ticket;
    }
    // </editor-fold>
    
    // <editor-fold>
    public int getId() { return id; }

    public int getQty() { return qty; }

    public LocalDateTime getpOn() { return pOn; }

    private void setpOn(LocalDateTime pOn) { this.pOn = pOn; }

    public Ticket getTicket() { return ticket; }
    
    private void setTicket(Ticket ticket) { this.ticket = ticket; }
    // </editor-fold>
    
    // <editor-fold>
    public static List<Purchase> read_PurchaseByUser(User user) {
        List<Purchase> result = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM purchase p INNER JOIN tickets t ON (p.ticket_id = t.id) WHERE p.users_id = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getId());
                
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
//                    Ticket t = new Ticket(
//                            rs.getInt("id"),
//                            
//                    );
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public static boolean insert_Purchase(Purchase purchase) {
        return false;
    }
    
    public static boolean update_Purchase(Purchase purchase) {
        return false;
    }
    // </editor-fold>
}