package rooting.mapper;

import org.springframework.jdbc.core.RowMapper;
import rooting.model.Slot;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author agavrikov
 * @date 31.01.19
 */
public class SlotMapper implements RowMapper<Slot> {
    @Override
    public Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Slot slot = new Slot();
        ResultSetWrapper rsWrapper = new ResultSetWrapper(rs);
        slot.setId(rs.getLong("id"));
        slot.setStockId(rs.getLong("stock_id"));
        slot.setDay(rs.getInt("day"));
        slot.setTimeFrom(rs.getTime("time_from"));
        slot.setTimeTo(rs.getTime("time_to"));
        slot.setConveyor(rs.getInt("conveyor"));
        return slot;
    }
}
