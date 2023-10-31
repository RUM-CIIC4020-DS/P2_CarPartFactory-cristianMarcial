package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import interfaces.*;;

/**
 * This class will represent a car part produced by the? machine of this.
 * 
 * 
 * @author Cristian Marcial cristian.marcial@upr.edu 
 */
public class CarPartFactory {
	
	/**
	 * 
	 */
	List<PartMachine> machines;
	
	/**
	 * 
	 */
	List<Order> orders;
	
	/**
	 * 
	 */
	Map<Integer, CarPart> partCatalog;
	
	/**
	 * 
	 */
	Map<Integer, List<CarPart>> inventory;
	
	/**
	 * 
	 */
	Map<Integer, Integer> defectives;
	
	/**
	 * 
	 */
	Stack<CarPart> productionBin;
	
    public CarPartFactory(String orderPath, String partsPath) throws IOException {
    	
    	/**
    	 * Orders
    	 */
    	BufferedReader line = new BufferedReader(new FileReader(orderPath));
		String currentLine;
		line.readLine(); 
		
		while((currentLine = line.readLine()) != null) { 
	        String[] lineSplit = currentLine.split(",", 3);  
	        
	        int numOfRequestedPartsMap = 0;
	        for(char i : lineSplit[2].toCharArray()) if(i=='-') numOfRequestedPartsMap++;
	        String[] requestedParts = lineSplit[2].split("-", numOfRequestedPartsMap); 
	        
	        //Map<Integer, Integer> requestedPartsMap0 = new MapHashTable<Integer, Integer>(0, new HashFunction());
	        //Map<Integer, Integer> requestedPartsMap0 = new HashTableSC<Integer, Integer>(0, new HashFunction<Integer>(0));
	        Map<Integer, Integer> requestedPartsMap = null;
	        
	        for(int i = 0; i < lineSplit[2].length(); i++) {
	        	//if() {}
	        }
	        
	        this.orders.add(new Order(Integer.parseInt(lineSplit[0]), lineSplit[1], requestedPartsMap, false));
		}
    }
    
    public List<PartMachine> getMachines() {
    	return this.machines;
    }
    
    public void setMachines(List<PartMachine> machines) {
        this.machines = machines;
    }
    
    public Stack<CarPart> getProductionBin() {
    	return this.productionBin;
    }
    
    public void setProductionBin(Stack<CarPart> production) {
       this.productionBin = production;
    }
    
    public Map<Integer, CarPart> getPartCatalog() {
    	return this.partCatalog;
    }
    
    public void setPartCatalog(Map<Integer, CarPart> partCatalog) {
        this.partCatalog = partCatalog;
    }
    
    public Map<Integer, List<CarPart>> getInventory() {
    	return this.inventory;
    }
    
    public void setInventory(Map<Integer, List<CarPart>> inventory) {
        this.inventory = inventory;
    }
    
    public List<Order> getOrders() {
    	return this.orders;
    }
    
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public Map<Integer, Integer> getDefectives() {
    	return this.defectives;
    }
    
    public void setDefectives(Map<Integer, Integer> defectives) {
        this.defectives = defectives;
    }

    public void setupOrders(String path) throws IOException {
    	
    }
    
    public void setupMachines(String path) throws IOException {
    	
    }
    
    public void setupCatalog() {
        
    }
    
    public void setupInventory() {
        
    }
    
    public void storeInInventory() {
    	
    }
    
    public void runFactory(int days, int minutes) {
        
    }
    
    public void processOrders() {
        
    }
    
    /**
     * Generates a report indicating how many parts were produced per machine,
     * how many of those were defective and are still in inventory. Additionally, 
     * it also shows how many orders were successfully fulfilled. 
     */
    public void generateReport() {
        String report = "\t\t\tREPORT\n\n";
        report += "Parts Produced per Machine\n";
        for (PartMachine machine : this.getMachines()) {
            report += machine + "\t(" + 
            this.getDefectives().get(machine.getPart().getId()) +" defective)\t(" + 
            this.getInventory().get(machine.getPart().getId()).size() + " in inventory)\n";
        }
       
        report += "\nORDERS\n\n";
        for (Order transaction : this.getOrders()) {
            report += transaction + "\n";
        }
        System.out.println(report);
    }
}