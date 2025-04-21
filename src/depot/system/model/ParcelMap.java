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

public class ParcelMap {
    private Map<String, Parcel> parcels;

    public ParcelMap() {
        parcels = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getId(), parcel);
    }

    public Parcel getParcel(String parcelId) {
        return parcels.get(parcelId);
    }

    // Return all parcels as an Iterable collection
    public Iterable<Parcel> getAllParcels() {
        return parcels.values();
    }

    // Check if the parcel map is empty
    public boolean isEmpty() {
        return parcels.isEmpty();
    }
}