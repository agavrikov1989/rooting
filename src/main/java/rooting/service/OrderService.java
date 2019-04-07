package rooting.service;

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

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Order getOrder(int id) {
        String sql = "SELECT * FROM stock_order WHERE id = ?";
        return jdbcTemplate.query(sql, new OrderMapper(), id).stream().findFirst().orElse(null);
    }

    public List<Order> getOrders(long stockId) {
        String sql =
            "SELECT id, stock_id, latitude, longitude," +
            "       length, width, height," +
            "       weigth, " +
            "       timestamp_from, timestamp_to," +
            "       creation_time " +
            "  FROM stock_order " +
            " WHERE stock_id = ?" +
            " LIMIT 10000";
        return jdbcTemplate.query(sql, new OrderMapper(), stockId);
    }

    public void saveOrder(int stockId, double latitude, double longitude,
                          double length, double width, double height,
                          double weigth,
                          LocalDateTime timeFrom, LocalDateTime timeTo) {
        String sql =
            "INSERT INTO stock_order " +
                    "(id, stock_id, latitude, longitude," +
                    " length, width, height," +
                    " weigth," +
                    " timestamp_from, timestamp_to," +
                    " creation_time) " +
            "VALUES (nextval('order_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";

        jdbcTemplate.update(sql, stockId, latitude, longitude,
            length, width, height,
            weigth,
            timeFrom, timeTo);
    }
}
