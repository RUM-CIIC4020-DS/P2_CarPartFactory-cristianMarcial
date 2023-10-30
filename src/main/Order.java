package main;

import interfaces.Map;

public class Order {
   
    public Order(int id, String customerName, Map<Integer, Integer> requestedParts, boolean fulfilled) {
        
    }
    public int getId() {
        return 0;
    }
    public void setId(int id) {
        
    }
    public boolean isFulfilled() {
      return true;
    }
    public void setFulfilled(boolean fulfilled) {
        
    }
    public Map<Integer, Integer> getRequestedParts() {
    	return null;
    }
    public void setRequestedParts(Map<Integer, Integer> requestedParts) {
       
    }
    public String getCustomerName() {
    	return null;
    }
    public void setCustomerName(String customerName) {
        
    }
    /**
     * Returns the order's information in the following format: {id} {customer name} {number of parts requested} {isFulfilled}
     */
    @Override
    public String toString() {
        return String.format("%d %s %d %s", this.getId(), this.getCustomerName(), this.getRequestedParts().size(), (this.isFulfilled())? "FULFILLED": "PENDING");
    }

    
    
}
