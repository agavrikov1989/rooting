package rooting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/ping")
    public String ping() {

        jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return "OK";
    }
}
