package ocado.task.sobiesiak;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DataParserTest {
    private DataParser dataParser;

    @BeforeEach
    void setUp() {
        dataParser = new DataParser();
    }

    @Test
    void testParseOrders() throws Exception {
        List<Order> orders = dataParser.parseOrders("src/test/resources/advanced-optimize-order-count/orders.json");
        Assertions.assertEquals(5, orders.size());

        Order order1 = orders.get(0);
        Assertions.assertEquals("order-1", order1.getorderId());
        Assertions.assertEquals(new BigDecimal("5.00"), order1.getOrderValue());
        Assertions.assertEquals(Duration.parse("PT15M"), order1.getPickingTime());
        Assertions.assertEquals(LocalTime.parse("09:15:00"), order1.getCompleteBy());

        Order order2 = orders.get(1);
        Assertions.assertEquals("order-2", order2.getorderId());
        Assertions.assertEquals(new BigDecimal("5.00"), order2.getOrderValue());
        Assertions.assertEquals(Duration.parse("PT30M"), order2.getPickingTime());
        Assertions.assertEquals(LocalTime.parse("10:00:00"), order2.getCompleteBy());

        Order order5 = orders.get(4);
        Assertions.assertEquals("order-5", order5.getorderId());
        Assertions.assertEquals(new BigDecimal("5.00"), order5.getOrderValue());
        Assertions.assertEquals(Duration.parse("PT30M"), order5.getPickingTime());
        Assertions.assertEquals(LocalTime.parse("10:00:00"), order5.getCompleteBy());
    }

    @Test
    void testParseStore() throws Exception {
        Store store = dataParser.parseStore("src/test/resources/advanced-optimize-order-count/store.json");
        Assertions.assertEquals(LocalTime.parse("09:00:00"), store.getPickingStartTime());
        Assertions.assertEquals(LocalTime.parse("10:00:00"), store.getPickingEndTime());

        List<String> pickers = store.getPickers();
        Assertions.assertEquals(2, pickers.size());
        Assertions.assertEquals("P1", pickers.get(0));
        Assertions.assertEquals("P2", pickers.get(1));
    }
}