package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import data_structures.BasicHashFunction;
import data_structures.DoublyLinkedList;
import data_structures.HashTableSC;
import data_structures.LinkedStack;
import interfaces.*;

/**
 * This class will manage all the machines and any operation related to them. ??)
 * 
 * 
 * @author Cristian Marcial cristian.marcial@upr.edu 
 */
public class CarPartFactory {
	
	/**
	 * A list with all the machines that produce parts.
	 */
	List<PartMachine> machines;
	
	/**
	 * A list with all the orders received.
	 */
	List<Order> orders;
	
	/**
	 * Map that holds all the car parts that can be produced in the factory. The key is the id of the CarPart, 
	 * this allows for quick look up for the parts.
	 */
	Map<Integer, CarPart> partCatalog;
	
	
	/**
	 * Map that hold all the parts produced and not used for an order yet. The key is the id for the CarPart and
	 * the value is a List with all the available parts.
	 */
	Map<Integer, List<CarPart>> inventory;
	
	/**
	 * Map that counts how many defective parts have been produced. The key is the car part id and the value the
	 * count of defective parts.
	 */
	Map<Integer, Integer> defectives;
	
	/**
	 * A stack that receives all the parts produced by the machines during production.
	 */
	Stack<CarPart> productionBin;
	
    public CarPartFactory(String orderPath, String partsPath) throws IOException {
    	setupMachines(partsPath);
    	setupOrders(orderPath);
    	setupInventory();
    	this.productionBin = new LinkedStack<CarPart>(); 
    	defectives = new HashTableSC<Integer, Integer>(10, new BasicHashFunction());
        
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
    
    /**
     * Receives the path to the order’s csv then initializes and populates the order’s map with the information
     * from the file. Initially all orders are unfulfilled.
     * 
     * @param path a String for receiving the file path in which the orders are located.
     */
    public void setupOrders(String path) throws IOException {
		this.orders = new DoublyLinkedList<Order>();
		
    	BufferedReader lineInOrdersFile = new BufferedReader(new FileReader(path));
		String currentLineInOrdersFile; 
		lineInOrdersFile.readLine();
		
		while((currentLineInOrdersFile = lineInOrdersFile.readLine()) != null) {
			
			/**
			 * Each line is spilt id,customer_name,requested_parts 
			 */
	        String[] lineSplit = currentLineInOrdersFile.split(",", 3);
	        
	        int numOfRequestedPartsMap = 1;
	        for(char i : lineSplit[2].toCharArray()) if(i=='-') numOfRequestedPartsMap++;
	        String[] requestedParts = lineSplit[2].split("-", numOfRequestedPartsMap);
	        Map<Integer, Integer> requestedPartsMap = new HashTableSC<Integer, Integer>(10, new BasicHashFunction());
	        
	        //(5 8)-(3 6)-(1 10)
	        for(String i : requestedParts) {
	        	String s[] = i.substring(1, i.length()-1).split(" ", 2);
	        	requestedPartsMap.put(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
	        }
	        this.orders.add(new Order(Integer.parseInt(lineSplit[0]), lineSplit[1], requestedPartsMap, false));
		} lineInOrdersFile.close();
    }
    
    /**
     * Method that receives a path to the parts csv and initializes all the machines with the provided information. There
     * is no specific rule for the id of the machines, except that they must be unique.
     * 
     * @param path
     */
    public void setupMachines(String path) throws IOException {
		this.machines = new DoublyLinkedList<PartMachine>();
		this.partCatalog = new HashTableSC<Integer, CarPart>(10, new BasicHashFunction());
		
    	BufferedReader lineInPartsFile = new BufferedReader(new FileReader(path));
		String currentLineInPartsFile;
		lineInPartsFile.readLine();
		
		while((currentLineInPartsFile = lineInPartsFile.readLine()) != null) {
			String[] lS = currentLineInPartsFile.split(",", 6); //ID 0 ,PartName 1,Weight 2,WeightError 3,Period 4,ChanceOfDefective 5
			CarPart part = new CarPart(Integer.parseInt(lS[0]), lS[1], Double.parseDouble(lS[2]), false);
			this.partCatalog.put(Integer.parseInt(lS[0]), part);
			this.machines.add(new PartMachine(Integer.parseInt(lS[0]), part, Integer.parseInt(lS[4]), Double.parseDouble(lS[3]), Integer.parseInt(lS[5])));
		} lineInPartsFile.close();
    }
    
    /**
     * Initializes the inventory map. Initially all the car part id's will have an empty List.
     */
    public void setupInventory() {
    	this.inventory = new HashTableSC<Integer, List<CarPart>>(10, new BasicHashFunction());
    	for(int i : partCatalog.getKeys()) this.inventory.put(i, new DoublyLinkedList<CarPart>());
    }
    
    /**
     * Checks the content of the production bin and places it in its corresponding List in the inventory. If a part
     * is defective, it's not included in the inventory and the defective count for that part increases.
     */
    public void storeInInventory() {
    	while(!this.productionBin.isEmpty()) {
    		if(!productionBin.top().isDetective()) 
    			inventory.get(productionBin.top().getId()).add(productionBin.top());
    		else if(defectives.containsKey(productionBin.top().getId())) 
    			defectives.put(productionBin.top().getId(), defectives.get(productionBin.top().getId()+1));
    		else 
    			defectives.put(productionBin.top().getId(), 1);
    		productionBin.pop();
    	}
    }
    
    /**
     * Simulates the execution of the factory for the given number of days and each day runs for the given amount of minutes.
     * 
     * @param days
     * @param minutes 
     */
    public void runFactory(int days, int minutes) {
        for(int i = 0; i < days; i++) {	
        	for(int j = 0; j < minutes; j++)
        		for(PartMachine m: this.machines) {
        			CarPart result = m.produceCarPart();
        			if(result!=null) this.productionBin.push(result);
        		}
        	for(PartMachine m: this.machines) {
        		for(int k = 0; k < 10; k++) {
        			if(m.getConveyorBelt().front()!=null) this.productionBin.push(m.getConveyorBelt().front());
        			m.getConveyorBelt().dequeue();
        			m.getConveyorBelt().enqueue(null);
        		}
        	} this.storeInInventory();
        } this.processOrders();
    }
    
    /**
     * Checks the current inventory and starts fulfilling orders if the parts are available. It’s considered fulfilled if 
     * all the parts are available and they are removed from inventory. If an order cannot be fulfilled, then the parts 
     * remain in inventory.
     */
    public void processOrders() {
        for(Order o : this.orders) {
            List<Integer> availableParts = new DoublyLinkedList<Integer>();
        	boolean fulfilled = true;
            //o.getRequestedParts().get(p) /availableParts.put(p, o.getRequestedParts().get(p));
        	for(int p : o.getRequestedParts().getKeys())
        		if(inventory.containsKey(p)) availableParts.add(p);
        		else fulfilled = false;
        	if(fulfilled) for(int i : availableParts) inventory.remove(i);
        	o.setFulfilled(fulfilled);
        }
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