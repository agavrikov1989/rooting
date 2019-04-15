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
        ResultSetWrapper rsWrapper = new ResultSetWrapper(rs);
        order.setId(rs.getLong("id"));
        order.setStockId(rs.getLong("stock_id"));
        order.setCreationTime(rsWrapper.getLocalDateTime("creation_time"));
        order.setDestination(
                new Point(
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                )
        );
        order.setWeight(rsWrapper.getOptional("weight", Double.class));
        order.setCapacity(rsWrapper.getOptional("capacity", Double.class));
        order.setDeliveryTimeFrom(rsWrapper.getLocalDateTime("delivery_time_from"));
        order.setDeliveryTimeTo(rsWrapper.getLocalDateTime("delivery_time_to"));
        order.setLoadInterval(rsWrapper.getPGInterval("load_interval"));
        order.setUploadingInterval(rsWrapper.getPGInterval("uploading_interval"));
        return order;
    }
}
