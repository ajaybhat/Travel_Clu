package com.airwatch.database_classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Travel")
public class Trip
        implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "travel_id", columnDefinition = "INT")
    private int id;
    @Column(name = "booker_name", nullable = false, updatable = false, columnDefinition = "VARCHAR(40)")
    private String bookerName;
    @Column(name = "source", nullable = false, updatable = false, columnDefinition = "VARCHAR(40)")
    private String source;
    @Column(name = "destination", nullable = false, updatable = false, columnDefinition = "VARCHAR(40)")
    private String destination;
    @Column(name = "start_date", nullable = false, updatable = false, columnDefinition = "DATE")
    private String startDate;
    @Column(name = "return_date", nullable = true, updatable = false, columnDefinition = "DATE")
    private String returnDate;
    @Column(name = "passengers", updatable = true, columnDefinition = "INT")
    private int numPassengers;

    public String getBookerName() {
        return this.bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getNumPassengers() {
        return this.numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public String toString() {
        return this.bookerName + "\tbooked a trip for " + this.startDate + " from " + this.source + " to " + this.destination + new String(this.returnDate == null ? "" : new StringBuilder().append(" and back on ").append(this.returnDate).toString()).concat(new StringBuilder().append(" with a total of ").append(this.numPassengers).append(" passengers").toString());
    }
}
