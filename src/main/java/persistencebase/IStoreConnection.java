package persistencebase;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public interface IStoreConnection  {

    BasicDataSource getPool();

    Connection getConnection() throws SQLException;


}
