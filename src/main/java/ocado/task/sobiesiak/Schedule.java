package ocado.task.sobiesiak;

import java.util.List;

public interface Schedule {
    List<Task> scheduleTasks(List<Order> orders, Store store);
}
