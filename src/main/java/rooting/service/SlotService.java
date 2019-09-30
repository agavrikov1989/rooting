package rooting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            " WHERE stock_id = ? " +
            " ORDER BY conveyor, day, time_from";
        return jdbcTemplate.query(sql, new SlotMapper(), stockId);
    }

    public List<Slot> getDaySlots(long stockId, int day) {
        String sql =
                "SELECT id, stock_id, day, time_from, time_to, conveyor " +
                        "  FROM slot " +
                        " WHERE stock_id = ? AND day = ? " +
                        " ORDER BY time_from, time_to, conveyor";
        return jdbcTemplate.query(sql, new SlotMapper(), stockId, day);
    }

    public List<Slot> getSlots(long stockId, int conveyor) {
        String sql =
                "SELECT id, stock_id, day, time_from, time_to, conveyor " +
                        "  FROM slot " +
                        " WHERE stock_id = ? AND conveyor = ? " +
                        " ORDER BY day, time_from, time_to, id";
        return jdbcTemplate.query(sql, new SlotMapper(), stockId, conveyor);
    }

    public boolean save(long stockId, int day, LocalTime timeFrom, LocalTime timeTo) {
        String sql =
                "INSERT INTO slot " +
                "       (id, stock_id, day, time_from, time_to, conveyor) " +
                "VALUES (nextval('" + SEQUENCE + "'), ?, ?, ?, ?, NULL)";
        return jdbcTemplate.update(sql, stockId, day, timeFrom, timeTo) > 0;
    }

    public void delete(long id) {
        String sql = "DELETE FROM slot WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void merge(Slot slot) {
        String sql =
                "INSERT INTO slot " +
                "       (id, stock_id, day, time_from, time_to, conveyor) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) " +
                "DO UPDATE SET (id, stock_id, day, time_from, time_to, conveyor) = " +
                "       (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                slot.getId(), slot.getStockId(), slot.getDay(), slot.getTimeFrom(), slot.getTimeTo(), slot.getConveyor(),
                slot.getId(), slot.getStockId(), slot.getDay(), slot.getTimeFrom(), slot.getTimeTo(), slot.getConveyor());
    }

    public List<Slot> planning(long stockId) {
        List<Slot> slots = getSlots(stockId);
        slots.forEach(this::merge);
        return slots;
    }

    public void clearConveyor(long stockId) {
        String sql =
            "UPDATE slot " +
            "   SET conveyor = NULL " +
            " WHERE stock_id = ?";
        jdbcTemplate.update(sql, stockId);
    }
}
