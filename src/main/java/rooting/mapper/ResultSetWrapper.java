package rooting.mapper;

import org.postgresql.util.PGInterval;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author agavrikov
 * @date 15.04.19
 */
public final class ResultSetWrapper {

    private final ResultSet rs;

    public ResultSetWrapper(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getResultSet() {
        return rs;
    }

    public LocalDateTime getLocalDateTime(String columnName) throws SQLException {
        Timestamp value = rs.getTimestamp(columnName);
        return rs.wasNull() ? null : value.toLocalDateTime();
    }

    public PGInterval getPGInterval(String columnName) throws SQLException {
        Object value = rs.getObject(columnName);
        return rs.wasNull() ? null : new PGInterval(value.toString());
    }

    public <T> T getOptional(String columnName, Class<T> clazz) throws SQLException {
        T value = rs.getObject(columnName, clazz);
        return rs.wasNull() ? null : value;
    }
}
