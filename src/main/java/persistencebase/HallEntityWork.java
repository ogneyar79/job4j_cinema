package persistencebase;

import model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HallEntityWork {
    private final IStoreConnection storeHallCinema;

    public HallEntityWork(IStoreConnection storeHallCinema) {
        this.storeHallCinema = storeHallCinema;
    }


    public HallEntityWork() {
        this.storeHallCinema = CinemaStore.instOf();
    }

    public Collection<Seat> findAll() {
        List<Seat> seats = new ArrayList<>();

        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM hall")
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                seats.add(new Seat(rs.getInt("id"), rs.getBoolean("occupation")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seats;
    }

    public Seat getSeat(int id) {
        Seat seat = new Seat(0, false);
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM hall where id =?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seat = new Seat(rs.getInt(1), rs.getBoolean(2));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seat;
    }

    public void changeOccupation(int idNumber) {
        boolean changer = this.getSeat(idNumber).changeOccupation();
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement(" UPDATE hall set occupation = ? where id = ? ")) {
            ps.setBoolean(1, changer);
            ps.setInt(2, idNumber);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
