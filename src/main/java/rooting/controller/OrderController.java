package rooting.controller;

import java.time.LocalDateTime;

import org.postgresql.util.PGInterval;
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
                          @RequestParam(required = false) Double weigth, @RequestParam(required = false) Double capacity,
                          @RequestParam(value = "deliveryTimeFrom", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deliveryTimeFrom,
                          @RequestParam(value = "deliveryTimeTo", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deliveryTimeTo,
                          @RequestParam(value = "load", required = false, defaultValue = "0 mins") PGInterval loadInterval,
                          @RequestParam(value = "uploading", required = false, defaultValue = "0 mins") PGInterval uploadingInterval) {
        orderService.saveOrder(
                stockId, latitude, longitude,
                weigth, capacity,
                deliveryTimeFrom, deliveryTimeTo,
                loadInterval, uploadingInterval);
    }
}
