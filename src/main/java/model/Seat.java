package model;

import java.util.Objects;

public class Seat {
    private final int idNumber;
    public boolean occupation;


    public Seat(int idNumber, boolean occupation) {
        this.idNumber = idNumber;
        this.occupation = occupation;
    }

    public void setOccupation(boolean occupation) {
        this.occupation = occupation;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public boolean isOccupation() {
        return occupation;
    }

    public Boolean changeOccupation() {
        if (this.isOccupation()) {
            this.setOccupation(false);
            return false;
        } else this.setOccupation(true);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return idNumber == seat.idNumber &&
                occupation == seat.occupation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, occupation);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "idNumber=" + idNumber +
                ", occupation=" + occupation +
                '}';
    }

}
