package com.TicketApp.model;

import com.TicketApp.Utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    public static boolean insert_Ticket(Ticket ticket) {
        return false;
    }
    // </editor-fold>
}