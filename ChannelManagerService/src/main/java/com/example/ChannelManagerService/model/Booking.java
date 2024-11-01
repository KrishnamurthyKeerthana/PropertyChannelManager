package com.example.ChannelManagerService.model;

public class Booking {
    private String propertyId;
    private String guestName;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfGuests;

    // Constructors
    public Booking() {}

    public Booking(String propertyId, String guestName, String checkInDate, String checkOutDate, int numberOfGuests) {
        this.propertyId = propertyId;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
    }

    // Getters and Setters
    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }

    @Override
    public String toString() {
        return "Booking{" +
                "propertyId='" + propertyId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
