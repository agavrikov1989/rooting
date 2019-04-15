package rooting.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author agavrikov
 * @date 17.11.18
 */
public class Courier {

    private final static double MAX_WEIGTH = 400.0;
    private final static int MAX_WORKING_TIME = (int) TimeUnit.HOURS.toMinutes(8);
    private final static double MIDDLE_SPEED = 33.0834464;
    private final static int UPLOADING_TIME = 5; //время разгрузки заказа

    private double weight = 0; //грузоподьемность курьера
    private int workingTime = 0; //время работы курьера в минутах
    private double dist = 0; //расстояние, которое курьер преодалел

    private Point location; //текущее местонахождение курьера
    private List<Order> orders = new ArrayList<>(); //заказы, которые курьер развез

    public Courier(Stock stock) {
        this.location = stock.getLocation();
    }

    private boolean between(LocalDateTime from, LocalDateTime to, LocalDateTime x) {
        return (from.isBefore(x) || from.equals(x)) && (x.isBefore(to) && x.equals(to));
    }

    public boolean canAddOrder(Order order) {
        if (weight + order.getWeight() > MAX_WEIGTH) {
            return false;
        }
        if (workingTime + calcWorkingTime(order.getDestination()) > MAX_WORKING_TIME) {
            return false;
        }
        if (order.isScheduled()) {
            return false;
        }

        return true;
    }

    //добавляем заказ курьеру
    public void addOrder(Order order) {
        if (!canAddOrder(order)) {
            return;
        }
        weight += order.getWeight();
        workingTime = workingTime + calcWorkingTime(order.getDestination()) + UPLOADING_TIME;
        dist += location.dist(order.getDestination());
        order.setScheduled(true);
        orders.add(order);
        location = order.getDestination();
    }
    
    private int calcWorkingTime(Point point) {
        return (int) Math.ceil(location.dist(point) / MIDDLE_SPEED * TimeUnit.HOURS.toMinutes(1));
    }

    private double calcDist(Point point) {
        return location.dist(point);
    }

    @Override
    public String toString() {
        return "Courier{" +
                "weight=" + weight +
                ", workingTime=" + workingTime +
                ", dist=" + dist +
                ", orders=" + orders +
                '}';
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
