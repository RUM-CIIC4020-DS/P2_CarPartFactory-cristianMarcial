package main;

import interfaces.Map;

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
    }
    
    /**
	 * Returns the id of the order.
	 * 
	 * @return an integer representing the id of the order.
	 */
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isFulfilled() {
    	return this.fulfilled;
    }
    
    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
    
    public Map<Integer, Integer> getRequestedParts() {
    	return this.requestedParts;
    }
    
    public void setRequestedParts(Map<Integer, Integer> requestedParts) {
    	this.requestedParts = requestedParts;
    }
    
    public String getCustomerName() {
    	return this.customerName;
    }
    
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
