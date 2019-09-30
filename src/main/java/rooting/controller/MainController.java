package rooting.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import rooting.service.SlotService;

/**
 * @author agavrikov
 * @date 26.01.19
 */
@RestController
public class MainController {

    @Autowired
    private SlotService slotService;

    @GetMapping(value = "/stock", produces = "text/html")
    public ModelAndView getMain(@RequestParam long id) {
        ModelAndView model = new ModelAndView("rooting/stock");
        model.addObject("stockId", id);
        model.addObject("days", days());
        model.addObject("slots", slotService.getSlots(id));
        return model;
    }

    @GetMapping(value = "/stock", produces = "text/html")
    public ModelAndView getStockSlot(@RequestParam long id, @RequestParam int page) {
        ModelAndView model = new ModelAndView("rooting/stock");
        model.addObject("stockId", id);
        model.addObject("days", days());
        model.addObject("slots", slotService.getSlots(id));
        return model;
    }

    @GetMapping(value = "/conveyor", produces = "text/html")
    public ModelAndView getConveyor(@RequestParam long stockId, @RequestParam int id) {
        ModelAndView model = new ModelAndView("rooting/conveyor");
        model.addObject("id", id);
        model.addObject("days", days());
        model.addObject("slots", slotService.getSlots(stockId, id));
        return model;
    }

    @GetMapping(value = "/day", produces = "text/html")
    public ModelAndView getDaySlots(@RequestParam long stockId, @RequestParam int id) {
        ModelAndView model = new ModelAndView("rooting/day");
        model.addObject("id", id);
        model.addObject("stockId", stockId);
        model.addObject("days", days());
        model.addObject("slots", slotService.getDaySlots(stockId, id));
        return model;
    }

    private Map<Integer, String> days() {
        Map<Integer, String> days = new HashMap<>();
        days.put(1, "Понедельник");
        days.put(2, "Вторник");
        days.put(3, "Среда");
        days.put(4, "Четверг");
        days.put(5,  "Пятница");
        days.put(6, "Суббота");
        days.put(7, "Воскресенье");
        return days;
    }
}
