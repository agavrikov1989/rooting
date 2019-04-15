package rooting.model;

import org.postgresql.util.PGInterval;

import java.time.LocalDateTime;

/**
 * @author agavrikov
 * @date 24.01.19
 */
public class Order {

    private long id;
    private long stockId;
    private Point destination;
    private LocalDateTime creationTime;
    private Double weight;
    private Double capacity;
    private LocalDateTime deliveryTimeFrom;
    private LocalDateTime deliveryTimeTo;
    private PGInterval loadInterval;
    private PGInterval uploadingInterval;

    private boolean scheduled;

    public Order() {
    }

    public Order(long id, long stockId, Point destination,
                 Double weight, Double capacity,
                 LocalDateTime deliveryTimeFrom, LocalDateTime deliveryTimeTo,
                 PGInterval loadInterval, PGInterval uploadingInterval) {
        this.id = id;
        this.stockId = stockId;
        this.destination = destination;
        this.creationTime = LocalDateTime.now();
        this.weight = weight;
        this.capacity = capacity;
        this.deliveryTimeFrom = deliveryTimeFrom;
        this.deliveryTimeTo = deliveryTimeTo;
        this.loadInterval = loadInterval;
        this.uploadingInterval = uploadingInterval;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDeliveryTimeFrom() {
        return deliveryTimeFrom;
    }

    public void setDeliveryTimeFrom(LocalDateTime deliveryTimeFrom) {
        this.deliveryTimeFrom = deliveryTimeFrom;
    }

    public LocalDateTime getDeliveryTimeTo() {
        return deliveryTimeTo;
    }

    public void setDeliveryTimeTo(LocalDateTime deliveryTimeTo) {
        this.deliveryTimeTo = deliveryTimeTo;
    }

    public PGInterval getLoadInterval() {
        return loadInterval;
    }

    public void setLoadInterval(PGInterval loadInterval) {
        this.loadInterval = loadInterval;
    }

    public PGInterval getUploadingInterval() {
        return uploadingInterval;
    }

    public void setUploadingInterval(PGInterval uploadingInterval) {
        this.uploadingInterval = uploadingInterval;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }
}
