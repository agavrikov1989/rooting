package rooting.model;

import java.time.LocalDateTime;

/**
 * @author agavrikov
 * @date 24.01.19
 */
public class Order {

    private long id;
    private long stockId;
    private Point destination;
    private double length;
    private double width;
    private double height;
    private double weigth;
    private boolean scheduled;
    private LocalDateTime from;
    private LocalDateTime to;
    private LocalDateTime creationTime;

    public Order() {
    }

    public Order(long id, long stockId, Point destination,
                 double length,
                 double width, double height, double weigth,
                 LocalDateTime from, LocalDateTime to,
                 LocalDateTime creationTime) {
        this.id = id;
        this.stockId = stockId;
        this.destination = destination;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weigth = weigth;
        this.scheduled = false;
        this.from = from;
        this.to = to;
        this.creationTime = creationTime;
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

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", destination=" + destination +
                ", weigth=" + weigth +
                '}';
    }
}
