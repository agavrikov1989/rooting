package rooting.model;

import java.sql.Time;

/**
 * @author agavrikov
 * @date 29.08.2019
 */
public class Slot {

    private long id;
    private long stockId;
    private int day;
    private Time timeFrom;
    private Time timeTo;
    private int conveyor;

    public Slot() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    public int getConveyor() {
        return conveyor;
    }

    public void setConveyor(int conveyor) {
        this.conveyor = conveyor;
    }
}
