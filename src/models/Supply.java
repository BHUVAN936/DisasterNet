package models;

public class Supply {

    private String id;
    private String name;
    private int quantity;
    private int cost;
    private int priority;

    public Supply(String id,
                  String name,
                  int quantity,
                  int cost,
                  int priority) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {

        return id + " | " +
                name +
                " | Quantity: " + quantity +
                " | Cost: " + cost +
                " | Priority: " + priority;
    }
}