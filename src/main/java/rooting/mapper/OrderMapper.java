package rooting.mapper;

import org.springframework.jdbc.core.RowMapper;
import rooting.model.Order;
import rooting.model.Point;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author agavrikov
 * @date 31.01.19
 */
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setStockId(rs.getLong("stock_id"));
        order.setDestination(
                new Point(
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                )
        );
        order.setLength(rs.getDouble("length"));
        order.setLength(rs.getDouble("width"));
        order.setLength(rs.getDouble("height"));
        order.setLength(rs.getDouble("weigth"));
        order.setFrom(rs.getTimestamp("timestamp_from").toLocalDateTime());
        order.setTo(rs.getTimestamp("timestamp_to").toLocalDateTime());
        order.setCreationTime(rs.getTimestamp("creation_time").toLocalDateTime());
        return order;
    }
}
