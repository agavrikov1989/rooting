package rooting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rooting.service.OrderService;
import rooting.service.SlotService;
import rooting.service.StockService;

/**
 * @author agavrikov
 * @date 30.01.19
 */
@Configuration
public class ServiceConfig {

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public StockService stockService() {
        return new StockService();
    }

    @Bean
    public SlotService slotService() {
        return new SlotService();
    }
}
