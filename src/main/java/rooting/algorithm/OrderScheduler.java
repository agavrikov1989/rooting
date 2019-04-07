package rooting.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import rooting.model.Courier;
import rooting.model.Order;
import rooting.model.Point;
import rooting.model.Stock;

/**
 * @author agavrikov
 * @date 27.01.19
 */
public class OrderScheduler {

    private Stock stock; //база, с которой осуществляется доставка
    private List<Order> orders; //список заказов, которые надо будет развести

    public OrderScheduler(Stock stock, List<Order> orders) {
        this.stock = stock;
        this.orders = orders;
    }

    public List<Courier> planning() {
        List<Point> points =
                orders.stream().map(Order::getDestination).distinct().collect(Collectors.toList());
        Map<Point, List<Order>> orderMap = new HashMap<>();
        orders.forEach(order -> {
            orderMap.putIfAbsent(order.getDestination(), new ArrayList<>());
            orderMap.get(order.getDestination()).add(order);
        });


        TIntList randomInt = new TIntArrayList(IntStream.range(0, points.size() - 1)
                .toArray());
        randomInt.shuffle(new Random());

        LinkedHashMap<Point, List<Order>> orderPoints = new LinkedHashMap<>();
        for (int i = 0; i < randomInt.size(); ++i) {
            Point cur = points.get(randomInt.get(i));
            orderPoints.put(cur, orderMap.get(cur));
        }

        List<Courier> couriers = new ArrayList<>();
        int countOrders = 0;
        for (Map.Entry<Point, List<Order>> orderPoint : orderPoints.entrySet()) {
            orderPoint.getValue().forEach(order -> order.setScheduled(false));
            countOrders += orderPoint.getValue().size();
        }
        int countNotDelideredOrders = 0;
        while (countNotDelideredOrders < countOrders) {
            Courier courier = new Courier(stock);
            boolean breakOrders = false;
            for (Map.Entry<Point, List<Order>> orderPoint : orderPoints.entrySet()) {
                if (breakOrders) {
                    break;
                }
                for (Order order: orderPoint.getValue()) {
                    if (order.isScheduled()) {
                        continue;
                    }
                    if (courier.canAddOrder(order)) {
                        courier.addOrder(order);
                        order.setScheduled(true);
                        countNotDelideredOrders++;
                    } else {
                        breakOrders = true;
                        break;
                    }
                    if (countNotDelideredOrders == countOrders) {
                        breakOrders = true;
                        break;
                    }
                }
            }
            couriers.add(courier);
        }

        routingGoogleMaps(couriers);

        return couriers;
    }

    private void routingGoogleMaps(List<Courier> couriers) {
        for (Courier courier: couriers) {
            String mapUrl = "https://www.google.com/maps/dir/";
            mapUrl += String.format("%f,%f/", stock.getLocation().getLatitude(), stock.getLocation().getLongitude());
            for (int i = 0; i < courier.getOrders().size(); ++i) {
                Point cur = courier.getOrders().get(i).getDestination();
                mapUrl += String.format("%f,%f/", cur.getLatitude(), cur.getLongitude());
                if (i > 0 && i % 20 == 0) {
                    mapUrl += String.format("@%f,%f,18z/data=!3m1!4b1!4m2!4m1!3e0?hl=ru-RU", stock.getLocation().getLatitude(), stock.getLocation().getLongitude());
                    System.out.println(mapUrl);
                    System.out.println();
                    Point prev = courier.getOrders().get(i - 1).getDestination();
                    mapUrl = "https://www.google.com/maps/dir/";
                    mapUrl += String.format("%f,%f/", prev.getLatitude(), prev.getLongitude());
                }
            }
            mapUrl += String.format("@%f,%f,18z/data=!3m1!4b1!4m2!4m1!3e0?hl=ru-RU", stock.getLocation().getLatitude(), stock.getLocation().getLongitude());
            System.out.println(mapUrl);
            System.out.println();
            System.out.println("------------");
        }
    }
}
