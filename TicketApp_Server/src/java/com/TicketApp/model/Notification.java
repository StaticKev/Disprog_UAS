package com.TicketApp.model;

import java.time.LocalDateTime;

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

    public boolean isRead() { return read; }

    private void setRead(boolean read) { this.read = read; }
    // </editor-fold>
}
