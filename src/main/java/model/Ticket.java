package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Ticket {
    private final int id;
    private final Timestamp datePayment;
    private final Timestamp dateSession;
    private final int placeNumber;
    private final int number;

    public Ticket(int id, Timestamp datePayment, Timestamp dateSession, int placeNumber) {
        this.id = id;
        this.datePayment = datePayment;
        this.dateSession = dateSession;
        this.placeNumber = placeNumber;
        number = this.hashCode();
    }

    public Ticket(int id, Timestamp datePayment, Timestamp dateSession, int placeNumber, int number) {
        this.id = id;
        this.datePayment = datePayment;
        this.dateSession = dateSession;
        this.placeNumber = placeNumber;
        this.number = number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(datePayment, dateSession, placeNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && placeNumber == ticket.placeNumber && number == ticket.number && datePayment.equals(ticket.datePayment) && dateSession.equals(ticket.dateSession);
    }

    public int getId() {
        return id;
    }

    public Timestamp getDatePayment() {
        return datePayment;
    }

    public Timestamp getDateSession() {
        return dateSession;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public int getNumber() {
        return number;
    }
}
