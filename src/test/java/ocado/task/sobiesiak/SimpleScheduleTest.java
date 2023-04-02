package ocado.task.sobiesiak;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class SimpleScheduleTest {
    @Test
    void testScheduleTasks() {
        JsonParser parser = new DataParser();
        try {
//            create orders and store
            List<Order> orders = parser.parseOrders("src/test/resources/advanced-optimize-order-count/orders.json");
            Store store = parser.parseStore("src/test/resources/advanced-optimize-order-count/store.json");

//          create expected tasks
            List<Task> expectedTasks = new ArrayList<>();
            expectedTasks.add(new Task(store.getPickers().get(0), orders.get(1).getorderId(), LocalTime.of(9, 0)));
            expectedTasks.add(new Task(store.getPickers().get(0), orders.get(4).getorderId(), LocalTime.of(9, 30)));
            expectedTasks.add(new Task(store.getPickers().get(1), orders.get(0).getorderId(), LocalTime.of(9, 0)));
            expectedTasks.add(new Task(store.getPickers().get(1), orders.get(2).getorderId(), LocalTime.of(9, 15)));

//          create a schedule object and call scheduleTasks method
            Schedule schedule = new SimpleSchedule();
            List<Task> generatedTasks = schedule.scheduleTasks(orders, store);

//            verify that the generatedTasks matches expectedTasks
            assertEquals(expectedTasks.size(), generatedTasks.size());
            for (int i = 0; i < expectedTasks.size(); i++) {
                assertEquals(expectedTasks.get(i).getPicker(), generatedTasks.get(i).getPicker());
                assertEquals(expectedTasks.get(i).getOrderId(), generatedTasks.get(i).getOrderId());
                assertEquals(expectedTasks.get(i).getPickingStartTime(), generatedTasks.get(i).getPickingStartTime());
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }


    }
}