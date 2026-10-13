package models;

public class Shelter {

    private String shelterId;
    private String name;
    private String location;
    private int capacity;
    private int occupied;

    public Shelter(String shelterId,
                   String name,
                   String location,
                   int capacity,
                   int occupied) {

        this.shelterId = shelterId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.occupied = occupied;
    }

    public String getShelterId() {
        return shelterId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public int getAvailableCapacity() {
        return capacity - occupied;
    }

    @Override
    public String toString() {

        return "\nShelter ID       : " + shelterId +
                "\nName             : " + name +
                "\nLocation         : " + location +
                "\nCapacity         : " + capacity +
                "\nOccupied         : " + occupied +
                "\nAvailable        : " + getAvailableCapacity();
    }
}