package ocado.task.sobiesiak;

import java.time.LocalTime;

public class Task {
    private String picker;
    private String orderId;
    private LocalTime pickingStartTime;

    public Task(String picker, String orderId, LocalTime pickingStartTime) {
        this.picker = picker;
        this.orderId = orderId;
        this.pickingStartTime = pickingStartTime;
    }

    @Override
    public String toString() {
        return (this.picker + " " + this.orderId + " " + this.pickingStartTime.toString());
    }

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(LocalTime pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }
}
