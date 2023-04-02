package ocado.task.sobiesiak;

import java.util.List;

public interface JsonParser {
    List<Order> parseOrders(String filePath) throws Exception;

    Store parseStore(String filePath) throws Exception;


}
