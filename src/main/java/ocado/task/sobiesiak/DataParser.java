package ocado.task.sobiesiak;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonString;

public class DataParser implements JsonParser {
    @Override
    public List<Order> parseOrders(String filePath) throws Exception {
        List<Order> orders = new ArrayList<>();
        JsonReader reader = Json.createReader(new FileReader(filePath));
        JsonArray jsonOrders = reader.readArray();
        for (JsonObject jsonOrder : jsonOrders.getValuesAs(JsonObject.class)) {
            String orderId = jsonOrder.getString("orderId");
            BigDecimal orderValue = new BigDecimal(jsonOrder.getString("orderValue"));
            Duration pickingTime = Duration.parse(jsonOrder.getString("pickingTime"));
            LocalTime completeBy = LocalTime.parse(jsonOrder.getString("completeBy"));

            orders.add(new Order(orderId, orderValue, pickingTime, completeBy));
        }
        return orders;
    }

    @Override
    public Store parseStore(String filePath) throws Exception {
        List<String> pickers = new ArrayList<>();
        JsonReader reader = Json.createReader(new FileReader(filePath));
        JsonObject jsonStore = reader.readObject();
        LocalTime pickingStartTime = LocalTime.parse(jsonStore.getString("pickingStartTime"));
        LocalTime pickingEndTime = LocalTime.parse(jsonStore.getString("pickingEndTime"));
        jsonStore.getJsonArray("pickers").forEach(picker -> pickers.add(((JsonString) picker).getString()));
        return new Store(pickers, pickingStartTime, pickingEndTime);
    }
}
