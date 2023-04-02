package ocado.task.sobiesiak;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Invalid number of arguments");
            return;
        }

        try {
            JsonParser parser = new DataParser();
            List<Order> orders = parser.parseOrders(args[1]);
            Store store = parser.parseStore(args[0]);
            Schedule schedule = new SimpleSchedule();
            List<Task> tasks = schedule.scheduleTasks(orders, store);
            for(Task task : tasks){
                System.out.println(task);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}