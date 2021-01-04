package persistencebase;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class CinemaStore implements IStoreConnection {
    private final BasicDataSource pool = new BasicDataSource();

    public BasicDataSource getPool() {
        return pool;
    }
    public Connection getConnection() throws SQLException {
        return this.pool.getConnection();
    }

    private CinemaStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("C:\\Users\\Администратор\\IdeaProjects\\Ajax\\job4j_cinema\\job4j_cinema\\db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
      //  String st = "jdbc:postgresql://127.0.0.1:5432/cinema";
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final CinemaStore INST = new CinemaStore();
    }

    public static CinemaStore instOf() {
        return Lazy.INST;
    }
}
