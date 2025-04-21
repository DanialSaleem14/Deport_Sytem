/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package depot.system.model;

/**
 *
 * @author dania
 */
public class Parcel {
    private String id;
    private double weight;
    private String dimensions;
    private int daysInDepot;
    private String status;

    public Parcel(String id, double weight, String dimensions, int daysInDepot) {
        this.id = id;
        this.weight = weight;
        this.dimensions = dimensions;
        this.daysInDepot = daysInDepot;
        this.status = "Pending";
    }

    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public String getStatus() {
        return status;
    }

    public void markAsProcessed() {
        this.status = "Processed";
    }

    @Override
    public String toString() {
        return "Parcel ID: " + id + ", Weight: " + weight + ", Dimensions: " + dimensions
                + ", Days in Depot: " + daysInDepot + ", Status: " + status;
    }
}