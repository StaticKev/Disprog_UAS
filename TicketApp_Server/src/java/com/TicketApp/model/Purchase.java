package com.TicketApp.model;

import java.time.LocalDateTime;
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
        return null;
    }
    
    public static boolean insert_Purchase(Purchase purchase) {
        return false;
    }
    
    public static boolean update_Purchase(Purchase purchase) {
        return false;
    }
    // </editor-fold>
}
