package com.TicketApp.model;

import com.TicketApp.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    // <editor-fold>
    private int id;
    private String title;
    private String location;
    private double price;
    private LocalDate eventDate;
    private Category category;
    private LocalDateTime saleStart;
    private LocalDateTime saleEnd;
    private double fsPrice;
    private LocalDateTime fsOn;
    private LocalDateTime fsEnds;
    // </editor-fold>
    
    // <editor-fold>
    public Ticket(String title, String location, double price, 
            LocalDate eventDate, Category category, LocalDateTime saleStart, 
            LocalDateTime saleEnd) { 
        this.title = title;
        this.location = location;
        this.price = price;
        this.eventDate = eventDate;
        this.category = category;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
    }
    
    public Ticket(String title, String location, double price, 
            LocalDate eventDate, Category category, LocalDateTime saleStart, 
            LocalDateTime saleEnd, double fsPrice, LocalDateTime fsOn, 
            LocalDateTime fsEnds) { 
        this.title = title;
        this.location = location;
        this.price = price;
        this.eventDate = eventDate;
        this.category = category;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
        this.fsPrice = fsPrice;
        this.fsOn = fsOn;
        this.fsEnds = fsEnds;
    }

    public Ticket(int id, String title, String location, double price, 
            LocalDate eventDate, Category category, LocalDateTime saleStart, 
            LocalDateTime saleEnd, double fsPrice, LocalDateTime fsOn, 
            LocalDateTime fsEnds) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.eventDate = eventDate;
        this.category = category;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
        this.fsPrice = fsPrice;
        this.fsOn = fsOn;
        this.fsEnds = fsEnds;
    }
    // </editor-fold>
    
    // <editor-fold>
    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getLocation() { return location; }

    public double getPrice() { return price; }

    public LocalDate getEventDate() { return eventDate; }

    public Category getCategory() { return category; }

    public LocalDateTime getSaleStart() { return saleStart; }

    public LocalDateTime getSaleEnd() { return saleEnd; }

    public double getFsPrice() { return fsPrice; }

    public LocalDateTime getFsOn() { return fsOn; }

    public LocalDateTime getFsEnds() { return fsEnds; }
    // </editor-fold>
    
    // <editor-fold>
    public static List<Ticket> read_AllTicket() {
        List<Ticket> result = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tickets;";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {   
                    result.add(new Ticket(
                            rs.getInt("id"),
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
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public static List<Ticket> read_AllTicketByUid(int uid) {
        List<Ticket> result = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tickets t INNER JOIN favorite f ON (t.id = f.tickets_id) WHERE f.users_id = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, uid);
                
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    result.add(new Ticket(
                            rs.getInt("id"),
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
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public static boolean insert_Ticket(Ticket ticket) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO tickets (title, location, price, event_date, category, sale_start, sale_ends, flash_sale_price, flash_sale_on, flash_sale_ends) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, ticket.getTitle());
                ps.setString(2, ticket.getLocation());
                ps.setDouble(3, ticket.getPrice());
                ps.setDate(4, Date.valueOf(ticket.getEventDate()));
                ps.setString(5, ticket.getCategory().toString());
                ps.setTimestamp(6, Timestamp.valueOf(ticket.getSaleStart()));
                ps.setTimestamp(7, Timestamp.valueOf(ticket.getSaleEnd()));
                if (ticket.getFsPrice() != 0) ps.setDouble(8, ticket.getFsPrice());
                else ps.setNull(8, Types.DOUBLE);
                if (ticket.getFsOn() == null) ps.setTimestamp(9, Timestamp.valueOf(ticket.getFsOn()));
                else ps.setNull(9, Types.TIMESTAMP);
                if (ticket.getFsEnds() == null) ps.setTimestamp(10, Timestamp.valueOf(ticket.getFsEnds()));
                else ps.setNull(10, Types.TIMESTAMP);
                
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return false;
    }
    
    public static boolean insert_Favorite(User user, Ticket ticket) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO favorite (users_id, tickets_id) VALUES (?, ?);";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getId());
                ps.setInt(2, ticket.getId());
                
                return ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    // </editor-fold>
}