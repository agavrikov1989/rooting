package rooting.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import rooting.service.SlotService;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @RequestMapping(value = "/planning", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void planning(long stockId) {
        slotService.planning(stockId);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void clearConveyor(long stockId) {
        slotService.clearConveyor(stockId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView add(
            @RequestParam long stockId,
            @RequestParam int day,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeTo) {
        slotService.save(stockId, day, timeFrom, timeTo);
        return new ModelAndView("redirect:/stock?id=" + stockId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        slotService.delete(id);
    }
}
