package main;

import interfaces.Map;

/**
 * This class will represent an order made by a customer or company. In this class, there are private variables which store 
 * information about the order, such as its ID, the name of the customer who made it, the parts which were requested by the
 * customer and their quantity, and its fulfilled status. Each variable can be obtained and changed with the "Getters and 
 * Setters" methods and each one is initialized by the constructor method.
 * 
 * @author Cristian Marcial cristian.marcial@upr.edu
 */
public class Order {
	
	/**
	 * Unique number that identifies the order.
	 */
	int id;
	
	/**
	 * Name of the customer that made the order.
	 */
	String customerName;
	
	/**
	 * Map that identifies which parts were requested and the amount needed of each. The key is the part id,
	 * and the values are the amount needed.
	 */
	Map<Integer, Integer> requestedParts;
	
	/**
	 * It is true if this order is completed; false otherwise.
	 */
	boolean fulfilled;
	
    public Order(int id, String customerName, Map<Integer, Integer> requestedParts, boolean fulfilled) {
        this.id = id;
        this.customerName = customerName;
        this.requestedParts = requestedParts;
        this.fulfilled = fulfilled;
    }
    
    /**
	 * Returns the id of the order.
	 * 
	 * @return an integer representing the id of the order.
	 */
    public int getId() {
        return this.id;
    }
    
    /**
	 * Sets the id of the order with the given argument.
	 * 
	 * @param id an integer for replacing the previous id of the order.
	 */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
	 * Returns the completeness status of the order.
	 * 
	 * @return true if the order is completed; false otherwise.
	 */
    public boolean isFulfilled() {
    	return this.fulfilled;
    }
    
    /**
     * Changes the completeness status of the order.
	 * 
	 * @param fulfilled a boolean for replacing the previous completeness status of the order.
	 */
    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
    
    /**
	 * Returns a Map with the requested parts id's as a key and the quantity of each one as a value.
	 * 
	 * @return a Map value holding the requested parts id and their quantity.
	 */
    public Map<Integer, Integer> getRequestedParts() {
    	return this.requestedParts;
    }
    
    /**
	 * Assign the requested parts and the quantity of each one.
	 * 
	 * @param requestedParts an Map for replacing the previous Map of the requested parts.
	 */
    public void setRequestedParts(Map<Integer, Integer> requestedParts) {
    	this.requestedParts = requestedParts;
    }
    
    /**
	 * Returns the name of the customer that made the order.
	 * 
	 * @return a String representing the name of the customer that made the order.
	 */
    public String getCustomerName() {
    	return this.customerName;
    }
    
    /**
	 * Sets the name of the customer that made the order with the given argument.
	 * 
	 * @param customerName a String for replacing the previous customer name.
	 */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    /**
     * Returns the order's information in the following format: {id} {customer name} {number of parts requested} {isFulfilled}
     */
    @Override
    public String toString() {
        return String.format("%d %s %d %s", this.getId(), this.getCustomerName(), this.getRequestedParts().size(), (this.isFulfilled())? "FULFILLED": "PENDING");
    }
}