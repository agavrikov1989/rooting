package rooting.model;

import java.sql.Time;
import java.time.LocalTime;

/**
 * @author agavrikov
 * @date 29.08.2019
 */
public class Slot implements Comparable<Slot> {

    private long id;
    private long stockId;
    private int day;
    private Time timeFrom;
    private Time timeTo;
    private Integer conveyor;

    public boolean isFree() {
        return conveyor == null;
    }

    public boolean beforeTimeTo(Slot slot) {
        return getTimeToMinute() < slot.getTimeToMinute();
    }

    public boolean after(Slot slot) {
        return getTimeFromMinute() >= slot.getTimeToMinute();
    }

    private long getTimeFromMinute() {
        LocalTime localTime = timeFrom.toLocalTime();
        return (day - 1) * 24L * 60L
                + localTime.getHour() * 60L
                + localTime.getMinute();
    }

    private long getTimeToMinute() {
        LocalTime localTime = timeTo.toLocalTime();
        return (timeFrom.after(timeTo) ? day : day - 1) * 24L * 60L
                + localTime.getHour() * 60L
                + localTime.getMinute();
    }

    @Override
    public int compareTo(Slot slot) {
        if (getTimeFromMinute() == slot.getTimeFromMinute()) {
            return Long.compare(getTimeToMinute(), slot.getTimeToMinute());
        }
        return Long.compare(getTimeFromMinute(), slot.getTimeFromMinute());
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", day=" + day +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                ", conveyor=" + conveyor +
                '}';
    }

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

    public Integer getConveyor() {
        if (conveyor == null) {
            System.out.println("id = " + id);
        }
        return conveyor;
    }

    public void setConveyor(Integer conveyor) {
        this.conveyor = conveyor;
    }
}
