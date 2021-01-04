package persistencebase;

import model.Seat;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserEntity implements ICruidEntity<User> {
    private final IStoreConnection storeHallCinema;

    public UserEntity(IStoreConnection storeHallCinema) {
        this.storeHallCinema = storeHallCinema;
    }

    public UserEntity() {
        this.storeHallCinema = CinemaStore.instOf();
    }

    @Override
    public User delete(int id) throws ParseException {
        return null;
    }

    public Collection<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users")
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("lastName"), rs.getString("phone")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public User getUserByPhone(String phone) {
        User user = new User(0, "", "", "0");
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where phone =?")) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User getUser(int id) {
        User user = new User(0, "", "", "0");
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where id =?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void save(User user) {
        if (user.getId() == 0) {
            create(user);
        } else {
            update(user);
        }
    }

    @Override
    public Optional<User> find(String elementPhone) {
        try (Connection conn = storeHallCinema.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where phone =?")) {
            ps.setString(1, elementPhone);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return Optional.of(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean saveIfNot(User user) {
        if (!this.find(user.getPhone()).isPresent()) {
            this.save(user);
            return true;
        }
        return false;
    }

    private void update(User user) {
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement ps = cn.prepareStatement(" UPDATE users set name = ?, set lastname = ?,phone = ? where id = ? ")
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User create(User user) {
        try (Connection cn = storeHallCinema.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO users(name,lastname,phone) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user = new User(id.getInt(1), id.getString(2), id.getString(3), id.getString(4));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
