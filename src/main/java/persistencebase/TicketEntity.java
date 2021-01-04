package persistencebase;

import model.Ticket;
import model.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class TicketEntity implements ICruidEntity<Ticket> {
    private final IStoreConnection storeHallCinema;

    public TicketEntity(IStoreConnection storeHallCinema) {
        this.storeHallCinema = storeHallCinema;
    }

    public TicketEntity() {
        this.storeHallCinema = CinemaStore.instOf();
    }

    @Override
    public Collection<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets")
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(rs.getInt("id"), rs.getTimestamp("datePayment"), rs.getTimestamp("dateSession")
                        , rs.getInt("placeNumber"), rs.getInt("number")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tickets;
    }

    public Ticket getTicketByNumber(int number) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp emptyTime = new Timestamp(dateFormat.parse("00/00/2000").getTime());

        Ticket ticket = new Ticket(0, emptyTime, emptyTime, 0, 000);
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM tickets where number = ?")) {
            ps.setInt(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ticket = new Ticket(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket;
    }

    public Ticket create(Ticket ticket) {
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO tickets(datePayment,dateSession,placeNumber,number) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setTimestamp(1, ticket.getDatePayment());
            ps.setTimestamp(2, ticket.getDateSession());
            ps.setInt(3, ticket.getPlaceNumber());
            ps.setInt(4, ticket.getNumber());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    ticket = new Ticket(id.getInt(1), id.getTimestamp(2), id.getTimestamp(3), id.getInt(4), id.getInt(5));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public void save(Ticket ticket) {
    }

    @Override
    public Optional<Ticket> find(String element) {
        return Optional.empty();
    }

    @Override
    public Ticket delete(int id) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp emptyTime = new Timestamp(dateFormat.parse("00/00/2000").getTime());
        Ticket ticket = new Ticket(0, emptyTime, emptyTime, 0, 000);
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement result = cn.prepareStatement("SELECT * FROM tickets where id = ?");
             PreparedStatement ps = cn.prepareStatement("DELETE FROM tickets where id =?");
        ) {
            result.setInt(1, id);
            ps.setInt(1, id);
            result.execute();
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ticket = new Ticket(rs.getInt(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
