/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package depot.system.model;

import java.util.*;


/**
 *
 * @author dania
 */
public class Customer {
    private String id;
    private String name;
    private List<Parcel> parcels;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.parcels = new ArrayList<>();
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for parcels
    public List<Parcel> getParcels() {
        return parcels;
    }

    // Add a parcel to the customer
    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}