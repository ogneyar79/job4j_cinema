package persistencebase;

import model.AccountTicket;

import java.sql.*;
import java.util.*;

public class AccountTicketEntity implements ICruidEntity<AccountTicket> {

    private final IStoreConnection storeHallCinema;

    public AccountTicketEntity(IStoreConnection storeHallCinema) {
        this.storeHallCinema = storeHallCinema;
    }

    public AccountTicketEntity() {
        this.storeHallCinema = CinemaStore.instOf();
    }

    @Override
    public Collection<AccountTicket> findAll() {
        List<AccountTicket> accounts = Collections.emptyList();
        try (Connection conn = storeHallCinema.getConnection();
             Statement st = conn.createStatement();
        ) {
            ResultSet rs = st.executeQuery("SELECT * FROM ticketAccounts");
            while (rs.next()) {
                accounts.add(new AccountTicket(rs.getInt("id"), rs.getInt("ticketNumber"), rs.getString("userPhone")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    public Collection<AccountTicket> getTicketsUserAccounts(String phone) {
        List<AccountTicket> accounts = Collections.emptyList();
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM ticketAccounts where userPhone=?")
        ) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new AccountTicket(rs.getInt("id"), rs.getInt("ticketNumber"), rs.getString("userPhone")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    public AccountTicket getAccountByTicket(int ticketNumber) {
        AccountTicket account = new AccountTicket(0, 0, "0");
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM ticketAccounts where ticketNumber=?")
        ) {
            ps.setInt(1, ticketNumber);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountTicket(rs.getInt("id"), rs.getInt("ticketNumber"), rs.getString("userPhone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public AccountTicket create(AccountTicket account) {
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO ticketAccounts(userPhone,ticketNumber) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, account.getUserPhone());
            ps.setInt(2, account.getNumberTicket());
            ps.executeUpdate();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    return new AccountTicket(id.getInt(1), id.getInt(3), id.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public void save(AccountTicket account) {

    }

    @Override
    public Optional<AccountTicket> find(String element) {
        return Optional.empty();
    }

    @Override
    public AccountTicket delete(int ticketNumber) {
        AccountTicket model = this.getAccountByTicket(ticketNumber);
        if (model == null) {
            return new AccountTicket(0, 0, "0");
        }
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM ticketAccounts where ticketNumber =?")
        ) {
            ps.setInt(1, ticketNumber);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
