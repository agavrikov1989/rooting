package rooting.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rooting.model.Order;
import rooting.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/v0/order/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @RequestMapping(value = "/v0/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveOrder(@RequestParam int stockId,
                          @RequestParam double latitude, @RequestParam double longitude,
                          @RequestParam double length, @RequestParam double width, @RequestParam double height,
                          @RequestParam double weigth,
                          @RequestParam(value = "from", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeFrom,
                          @RequestParam(value = "to", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeTo
    ) {
        orderService.saveOrder(stockId, latitude, longitude, length, width, height, weigth, timeFrom, timeTo);
    }
}
