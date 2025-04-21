/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package depot.system.View.Controller;

import depot.system.View.DepotView;
import depot.system.model.Customer;
import depot.system.model.Log;
import depot.system.model.Parcel;
import depot.system.model.ParcelMap;
import depot.system.model.QueueOfCustomers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author dania
 */
public class DepotController {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;
    private DepotView view;

    public DepotController(QueueOfCustomers customerQueue, ParcelMap parcelMap, DepotView view) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.view = view;
        initActions();
    }

    // Method to load customers from file
   public void loadCustomersFromFile(String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        br.readLine(); // Skip header
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            // Ensure the correct order: ID first, then Name
            String name = values[0].trim();
            String id = values[1].trim();
            

            Customer customer = new Customer(id, name); // Correctly assign ID and Name
            customerQueue.enqueueCustomer(customer);
            view.getCustomerArea().append(customer + "\n");
        }
    } catch (IOException e) {
        System.out.println("Error loading customers: " + e.getMessage());
    }
}

    // Method to load parcels from file
    public void loadParcelsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Parcel parcel = new Parcel(values[0], Double.parseDouble(values[1]), values[2], Integer.parseInt(values[3]));
                parcelMap.addParcel(parcel); // Add parcel to the map
                view.getParcelListModel().addElement(parcel.toString()); // Add parcel to the list model
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error loading parcels: " + e.getMessage());
        }
    }

    // Initialize button actions
    private void initActions() {
        view.getProcessButton().addActionListener(e -> processNextParcel());
        view.getAddCustomerButton().addActionListener(e -> addCustomer());
        view.getAddParcelButton().addActionListener(e -> addParcel());
        view.getClearProcessedButton().addActionListener(e -> clearProcessedParcels());
        view.getGenerateReportButton().addActionListener(e -> generateReport());
        view.getCalculateFeeButton().addActionListener(e -> calculateFee());
    }

    private void processNextParcel() {
    if (customerQueue.isEmpty()) {
        JOptionPane.showMessageDialog(view, "No customers in the queue.");
        return;
    }

    // Get the first customer
    Customer customer = customerQueue.peekCustomer();

    if (customer.getParcels().isEmpty()) {
        JOptionPane.showMessageDialog(view, "No parcels to process for customer: " + customer.getId());
        customerQueue.dequeueCustomer(); // Remove customer if no parcels
        updateCustomerArea();
        return;
    }

    // Ask the user to enter a Parcel ID
    String parcelId = JOptionPane.showInputDialog(view, "Enter Parcel ID to process:");
    if (parcelId == null || parcelId.trim().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Parcel ID cannot be empty.");
        return;
    }

    // Check if the parcel exists for the current customer
    Parcel selectedParcel = null;
    for (Parcel parcel : customer.getParcels()) {
        if (parcel.getId().equalsIgnoreCase(parcelId)) {
            selectedParcel = parcel;
            break;
        }
    }

    if (selectedParcel == null) {
        JOptionPane.showMessageDialog(view, "Parcel ID not found for customer: " + customer.getId());
        return;
    }

    // Process the selected parcel
    double fee = calculateParcelFee(selectedParcel); // Calculate the fee
    selectedParcel.markAsProcessed(); // Mark it as processed
    customer.getParcels().remove(selectedParcel); // Remove it from the customer's list

    // Update the processing area
    view.getProcessingArea().setText("Processing Parcel:\n" + selectedParcel + "\nFee: $" + fee);

    // Log the event
    Log.getInstance().logEvent("Processed parcel " + selectedParcel.getId() + " for customer " + customer.getName() + ". Fee: $" + fee);

    // If no parcels remain for the customer, remove them from the queue
    if (customer.getParcels().isEmpty()) {
        customerQueue.dequeueCustomer();
    }

    // Update the customer and parcel areas
    updateCustomerArea();
    updateParcelArea();
}

    private void addCustomer() {
        String customerName = JOptionPane.showInputDialog(view, "Enter Customer Name:");
        if (customerName != null && !customerName.trim().isEmpty()) {
            Customer customer = new Customer("C" + (customerQueue.size() + 1), customerName);
            customerQueue.enqueueCustomer(customer);
            view.getCustomerArea().append(customer + "\n");
        }
    }

    private void addParcel() {
        String parcelId = JOptionPane.showInputDialog(view, "Enter Parcel ID:");
        if (parcelId != null && !parcelId.trim().isEmpty()) {
            Parcel parcel = new Parcel(parcelId, 2.5, "10x10x10", 3); // Dummy data
            parcelMap.addParcel(parcel); // Add parcel to the map
            view.getParcelListModel().addElement(parcel.toString()); // Add parcel to the list model
            JOptionPane.showMessageDialog(view, "Parcel added successfully!");
        } else {
            JOptionPane.showMessageDialog(view, "Parcel ID cannot be empty.");
        }
    }

    private void clearProcessedParcels() {
        view.getParcelListModel().clear(); // Clear the list model
        JOptionPane.showMessageDialog(view, "All processed parcels have been cleared.");
    }

    private void generateReport() {
        try {
            String logData = Log.getInstance().getLog();
            String filePath = "DepotReport.txt"; // File to save the report

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("Depot Management System - Processed Parcels Report\n");
                writer.write("--------------------------------------------------\n");
                writer.write(logData);
            }

            JOptionPane.showMessageDialog(view, "Report generated successfully!\nSaved to: " + filePath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error generating report: " + e.getMessage());
        }
    }

    private void calculateFee() {
        if (view.getParcelListModel().isEmpty()) {
            JOptionPane.showMessageDialog(view, "No parcels available for fee calculation.");
            return;
        }

        String selectedParcel = view.getParcelList().getSelectedValue(); // Get the selected parcel
        if (selectedParcel != null) {
            String parcelId = selectedParcel.split(",")[0].split(":")[1].trim(); // Extract Parcel ID
            Parcel parcel = parcelMap.getParcel(parcelId); // Retrieve parcel object
            if (parcel != null) {
                double fee = calculateParcelFee(parcel);
                JOptionPane.showMessageDialog(view, "Fee for parcel " + parcel.getId() + ": $" + fee);
            } else {
                JOptionPane.showMessageDialog(view, "Parcel not found in the system.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "No parcel selected for fee calculation.");
        }
    }

    private double calculateParcelFee(Parcel parcel) {
        double baseFee = parcel.getWeight() * 2.0; // Base fee based on weight
        double storageFee = parcel.getDaysInDepot() * 1.5; // Additional fee for days in depot
        return baseFee + storageFee;
    }

    private void updateCustomerArea() {
        view.getCustomerArea().setText(""); // Clear the area
        for (Customer customer : customerQueue.getAllCustomers()) {
            view.getCustomerArea().append(customer + "\n");
        }
    }

    private void updateParcelArea() {
        view.getParcelListModel().clear(); // Clear the list model
        for (Parcel parcel : parcelMap.getAllParcels()) {
            view.getParcelListModel().addElement(parcel.toString());
        }
    }
}