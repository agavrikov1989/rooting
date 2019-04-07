package rooting.model;

/**
 * @author agavrikov
 * @date 22.01.19
 */
public class Stock {

    private long id;
    private Point location;

    public Stock() {
    }

    public Stock(long id, Point location) {
        this.id = id;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
