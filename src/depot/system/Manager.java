/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package depot.system;

import depot.system.View.Controller.DepotController;
import depot.system.View.DepotView;
import depot.system.model.ParcelMap;
import depot.system.model.QueueOfCustomers;

/**
 *
 * @author dania
 */
public class Manager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     QueueOfCustomers customerQueue = new QueueOfCustomers();
    ParcelMap parcelMap = new ParcelMap();
    DepotView view = new DepotView();
    DepotController controller = new DepotController(customerQueue, parcelMap, view);

    // Load data from files
    controller.loadCustomersFromFile("D:\\Semester 5\\Scd\\Depot System\\data\\Custs.csv");
    controller.loadParcelsFromFile("D:\\Semester 5\\Scd\\Depot System\\data\\Parcels.csv");

    // Start the application
}}