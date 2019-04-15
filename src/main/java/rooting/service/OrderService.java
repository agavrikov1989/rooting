package rooting.service;

import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rooting.mapper.OrderMapper;
import rooting.model.Order;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author agavrikov
 * @date 26.01.19
 */
@Component
public class OrderService {

    private static final String SEQUENCE = "order_seq";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Order getOrder(int id) {
        String sql = "SELECT id, stock_id, creation_time, latitude, longitude, " +
                     "       weight, capacity, delivery_time_from, delivery_time_to, " +
                     "       load_interval, uploading_interval " +
                     "  FROM stock_order " +
                     " WHERE id = ?";
        return jdbcTemplate.query(sql, new OrderMapper(), id).stream().findFirst().orElse(null);
    }

    public List<Order> getOrders(long stockId) {
        String sql =
            "SELECT id, stock_id, creation_time, latitude, longitude, " +
            "       weight, capacity, delivery_time_from, delivery_time_to, " +
            "       load_interval, uploading_interval " +
            "  FROM stock_order " +"  FROM stock_order " +
            " WHERE stock_id = ?";
        return jdbcTemplate.query(sql, new OrderMapper(), stockId);
    }

    public void saveOrder(int stockId, double latitude, double longitude,
                          Double weigth, Double capacity,
                          LocalDateTime deliveryTimeFrom, LocalDateTime deliveryTimeTo,
                          PGInterval loadInterval, PGInterval uploadingInterval) {
        String sql =
            "INSERT " +
            "  INTO stock_order " +
            "       (id, stock_id, latitude, longitude, " +
            "        weight, capacity, delivery_time_from, delivery_time_to, " +
            "        load_interval, uploading_interval) " +
            "VALUES (nextval('" + SEQUENCE + "'), ?, ?, ?, " +
                    "?, ?, ?, ?," +
                    "?, ?)";

        jdbcTemplate.update(
            sql, stockId, latitude, longitude,
            weigth, capacity, deliveryTimeFrom, deliveryTimeTo,
            loadInterval, uploadingInterval
        );
    }
}
