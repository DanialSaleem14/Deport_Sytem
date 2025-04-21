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
public class QueueOfCustomers {
    private LinkedList<Customer> queue;

    public QueueOfCustomers() {
        queue = new LinkedList<>();
    }

    public void enqueueCustomer(Customer customer) {
        queue.addLast(customer);
    }

    public Customer dequeueCustomer() {
        return queue.pollFirst();
    }

    public Customer peekCustomer() {
        return queue.peekFirst();
    }

    @Override
    public String toString() {
        return "Queue: " + queue;
    }

    // Return all customers as an Iterable collection
    public Iterable<Customer> getAllCustomers() {
        return queue;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Return the size of the queue
    public int size() {
        return queue.size();
    }
}