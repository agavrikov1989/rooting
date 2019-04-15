package rooting.model;

import java.time.LocalDateTime;

/**
 * @author agavrikov
 * @date 22.01.19
 */
public class Stock {

    private long id;
    private LocalDateTime creationTime;
    private Point location;

    public Stock() {
    }

    public Stock(long id, Point location) {
        this.id = id;
        this.creationTime = LocalDateTime.now();
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
