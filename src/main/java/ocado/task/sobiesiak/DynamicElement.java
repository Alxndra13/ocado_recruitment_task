package ocado.task.sobiesiak;

import java.util.*;

class DynamicElement {
    public List<Order> orders;
    public int sumDuration;

    public DynamicElement() {
        this.orders = new ArrayList<>();
        this.sumDuration = 0;

    }
}