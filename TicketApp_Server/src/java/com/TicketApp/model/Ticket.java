package com.TicketApp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private double ebPrice;
    private LocalDateTime ebOn;
    private LocalDateTime ebEnds;
    private double fsPrice;
    private LocalDateTime fsOn;
    private LocalDateTime fsEnds;
    // </editor-fold>
    
    // <editor-fold>
    public Ticket(String title, String location, double price, 
            LocalDate eventDate, Category category, LocalDateTime saleStart, 
            LocalDateTime saleEnd, double ebPrice, LocalDateTime ebOn, 
            LocalDateTime ebEnds, double fsPrice, LocalDateTime fsOn, 
            LocalDateTime fsEnds) { 
        this.title = title;
        this.location = location;
        this.price = price;
        this.eventDate = eventDate;
        this.category = category;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
        this.ebPrice = ebPrice;
        this.ebOn = ebOn;
        this.ebEnds = ebEnds;
        this.fsPrice = fsPrice;
        this.fsOn = fsOn;
        this.fsEnds = fsEnds;
    }

    public Ticket(int id, String title, String location, double price, 
            LocalDate eventDate, Category category, LocalDateTime saleStart, 
            LocalDateTime saleEnd, double ebPrice, LocalDateTime ebOn, 
            LocalDateTime ebEnds, double fsPrice, LocalDateTime fsOn, 
            LocalDateTime fsEnds) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.eventDate = eventDate;
        this.category = category;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
        this.ebPrice = ebPrice;
        this.ebOn = ebOn;
        this.ebEnds = ebEnds;
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

    public double getEbPrice() { return ebPrice; }

    public LocalDateTime getEbOn() { return ebOn; }

    public LocalDateTime getEbEnds() { return ebEnds; }

    public double getFsPrice() { return fsPrice; }

    public LocalDateTime getFsOn() { return fsOn; }

    public LocalDateTime getFsEnds() { return fsEnds; }
    // </editor-fold>
    
    // <editor-fold>
    public static List<Ticket> read_AllTicket() {
        return null;
    }
    
    public static boolean insert_Ticket(Ticket ticket) {
        return false;
    }
    // </editor-fold>
}