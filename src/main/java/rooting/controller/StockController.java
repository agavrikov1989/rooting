package rooting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rooting.algorithm.OrderScheduler;
import rooting.model.Courier;
import rooting.model.Order;
import rooting.model.Stock;
import rooting.service.OrderService;
import rooting.service.StockService;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/v0/stock/")
    public List<Stock> getStocks() {
        return stockService.getStocks();
    }

    @RequestMapping(value = "/v0/stock/{id}")
    public Stock getStock(@PathVariable int id) {
        return stockService.getStock(id);
    }

    @RequestMapping(value = "/v0/stock", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addOrder(@RequestParam double latitude, @RequestParam double longitude) {
        stockService.saveStock(latitude, longitude);
    }

    @RequestMapping(value = "/v0/stock/{id}/orders")
    public List<Order> getStockOrders(@PathVariable int id) {
        return orderService.getOrders(id);
    }

    @RequestMapping(value = "/v0/stock/{id}/rooting")
    public List<Courier> calcRooting(@PathVariable long id) {
        long start = System.currentTimeMillis();
        Stock stock = stockService.getStock(id);
        if (stock == null) {
            return null;
        }
        List<Order> orders = orderService.getOrders(id);
        OrderScheduler scheduler = new OrderScheduler(stock, orders);
        List<Courier> couriers = scheduler.planning();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return couriers;
    }
}
