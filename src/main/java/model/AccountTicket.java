package model;

public class AccountTicket {
    private final int id;
    private final int numberTicket;
    private final String userPhone;


    public AccountTicket(int id, int numberTicket, String userPhone) {
        this.id = id;
        this.numberTicket = numberTicket;
        this.userPhone = userPhone;
    }

    public int getId() {
        return id;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
