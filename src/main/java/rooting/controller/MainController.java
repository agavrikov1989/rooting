package rooting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author agavrikov
 * @date 26.01.19
 */
@RestController
public class MainController {

    @GetMapping(value = "/main", produces = "text/html")
    public ModelAndView getInbox() {
        ModelAndView model = new ModelAndView("rooting/main");
        return model;
    }
}
