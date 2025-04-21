/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package depot.system.model;

/**
 *
 * @author dania
 */
public class Worker {
    public void processParcel(Customer customer) {
        if (customer != null && !customer.getParcels().isEmpty()) {
            Parcel parcel = customer.getParcels().remove(0); // Remove the first parcel from the customer
            double fee = calculateFee(parcel);
            parcel.markAsProcessed();
            Log.getInstance().logEvent("Processed parcel " + parcel.getId() + " for customer " + customer.getName() + ". Fee: $" + fee);
        }
    }

   public double calculateFee(Parcel parcel) {
    double baseFee = parcel.getWeight() * 2.0; // Base fee based on weight
    double storageFee = parcel.getDaysInDepot() * 1.5; // Fee based on storage time
    return baseFee + storageFee;
}

}