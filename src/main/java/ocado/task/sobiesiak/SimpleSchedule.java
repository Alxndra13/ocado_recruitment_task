package ocado.task.sobiesiak;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class SimpleSchedule implements Schedule {
    @Override
    public List<Task> scheduleTasks(List<Order> orders, Store store) {
        List<Task> tasks = new ArrayList<>();

//        loop through each picker in the store
        for (String picker : store.getPickers()) {
            DynamicElement result;
            int cols = (int) Duration.between(store.getPickingStartTime(),
                    store.getPickingEndTime()).toMinutes() + 1;
            int rows = orders.size() + 1;
            DynamicElement[][] dp = new DynamicElement[rows][cols];

//            initialize the table with DynamicElements having zeros
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    dp[i][j] = new DynamicElement();
                }
            }

//            filling dynamic programming table
            for (int i = 1; i < rows; i++) {
                Order currOrder = orders.get(i - 1);
                int duration = (int) (currOrder.getPickingTime()).toMinutes();
                int deadline = (int) Duration.between(store.getPickingStartTime(),
                        currOrder.getCompleteBy()).toMinutes();

                for (int j = 1; j < cols; j++) {
//                    cant add this order to picker alone
                    if (duration > j || deadline < j) {

//                        choose between dp[i-1][j] and dp[i][j-1] to determine optimal combination
//                        firstly by bigger number of orders
                        if (dp[i - 1][j].orders.size() > dp[i][j - 1].orders.size()) {
                            dp[i][j] = dp[i - 1][j];
                        } else if (dp[i - 1][j].orders.size() == dp[i][j - 1].orders.size()
                                && dp[i - 1][j].sumDuration >= dp[i][j - 1].sumDuration) {
//                            secondly we choose longer sumDuration
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            dp[i][j] = dp[i][j - 1];
                        }

                    }
//                    we could add the order
                    else {
//                        temporarily add current order to the list and check if we could add it to any previous orders in dp
                        dp[i][j].orders.add(currOrder);

//                        add this order to the previous combination where it already fits
                        if (j - duration >= 0) {
                            DynamicElement previous = dp[i - 1][j - duration];
                            dp[i][j].orders.addAll(previous.orders);
                            dp[i][j].sumDuration += previous.sumDuration;

//                            check if this combination is better than the previous one without current order
                            if (dp[i - 1][j].orders.size() > dp[i][j].orders.size()) {
                                dp[i][j] = dp[i - 1][j];
                            } else if (dp[i - 1][j].orders.size() == dp[i][j].orders.size()
                                    && dp[i - 1][j].sumDuration > dp[i][j].sumDuration) {
                                dp[i][j] = dp[i - 1][j];
                            }
                        }
                    }
                }
            }

//            our list of orders to be picked by the current picker is the last cell in dp table
            result = dp[rows - 1][cols - 1];
//            adding result into tasks
            LocalTime pickAt = store.getPickingStartTime();
            for (int i = result.orders.size() - 1; i >= 0; i--) {
                Order order = result.orders.get(i);
                Task task = new Task(picker, order.getorderId(), pickAt);
                pickAt = pickAt.plus(order.getPickingTime());
                tasks.add(task);
            }

//            remove done orders
            Set<Order> resultOrderSet = new HashSet<>(result.orders);
            orders.removeAll(resultOrderSet);
        }
        return tasks;
    }
}


















