package db;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleConnection extends HikariCPDataSource{
    private OracleConnection() {
        super();
    }
    public static Connection getOracleConnection() throws SQLException {
        return getConnection();
    }
}
