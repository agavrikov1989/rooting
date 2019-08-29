package rooting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;

import rooting.mapper.SlotMapper;
import rooting.model.Slot;

/**
 * @author agavrikov
 * @date 26.01.19
 */
@Component
public class SlotService {

    private static final String SEQUENCE = "slot_seq";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Slot> getSlots(long stockId) {
        String sql =
            "SELECT id, stock_id, day, time_from, time_to, conveyor " +
            "  FROM slot " +
            " WHERE stock_id = ?";
        return jdbcTemplate.query(sql, new SlotMapper(), stockId);
    }

    public void saveOrder(long id, long stockId, int day,
            Time timeFrom, Time timeTo, int conveyor) {
        String sql =
                "INSERT " +
                "  INTO slot " +
                "       (id, stock_id, day, time_from, time_to, conveyor) " +
                "VALUES (nextval('" + SEQUENCE + "'), ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, stockId, day, timeFrom, timeTo, conveyor);
    }
}
