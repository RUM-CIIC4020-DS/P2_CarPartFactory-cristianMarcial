package main;

import interfaces.Queue;

/**
 * This class will represent a car part produced by the? machine of this.
 * 
 * 
 * @author Cristian Marcial cristian.marcial@upr.edu 
 */
public class PartMachine {
	
	/**
	 * 
	 */
	int id;
	/**
	 * 
	 */
	CarPart part;
	/**
	 * 
	 */
	int period;
	/**
	 * 
	 */
	double weightError;
	/**
	 * 
	 */
	int chanceOfDefective;
	/**
	 * 
	 */
	Queue<Integer> timer;
	/**
	 * 
	 */
	Queue<CarPart> conveyorBelt;
	
    public PartMachine(int id, CarPart p1, int period, double weightError, int chanceOfDefective) {
        this.id = id;
        this.part = p1;
        this.period = period;
        this.weightError = weightError;
        this.chanceOfDefective = chanceOfDefective;
    }
    
    public int getId() {
       return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Queue<Integer> getTimer() {
    	return this.timer;
    }
    
    public void setTimer(Queue<Integer> timer) {
        this.timer = timer;
    }
    
    public CarPart getPart() {
    	return this.part;
    }
    
    public void setPart(CarPart part1) {
        this.part = part1;
    }
    public Queue<CarPart> getConveyorBelt() {
    	return this.conveyorBelt;
    }
    
    public void setConveyorBelt(Queue<CarPart> conveyorBelt) {
    	this.conveyorBelt = conveyorBelt;
    }
    
    public int getTotalPartsProduced() {
    	return 0;
    }
    
    public void setTotalPartsProduced(int count) {
    	
    }
    
    public double getPartWeightError() {
    	return 0.0d;
    }
    
    public void setPartWeightError(double partWeightError) {
        
    }
    
    public int getChanceOfDefective() {
    	return 0;
    }
    
    public void setChanceOfDefective(int chanceOfDefective) {
        
    }
    
    public void resetConveyorBelt() {
        
    }
    
    public int tickTimer() {
    	return 0;
    }
    
    public CarPart produceCarPart() {
    	return null;
    }

    /**
     * Returns string representation of a Part Machine in the following format:
     * Machine {id} Produced: {part name} {total parts produced}
     */
    @Override
    public String toString() {
        return "Machine " + this.getId() + " Produced: " + this.getPart().getName() + " " + this.getTotalPartsProduced();
    }
    /**
     * Prints the content of the conveyor belt. 
     * The machine is shown as |Machine {id}|.
     * If the is a part it is presented as |P| and an empty space as _.
     */
    public void printConveyorBelt() {
        // String we will print
        String str = "";
        // Iterate through the conveyor belt
        for(int i = 0; i < this.getConveyorBelt().size(); i++){
            // When the current position is empty
            if (this.getConveyorBelt().front() == null) {
                str = "_" + str;
            }
            // When there is a CarPart
            else {
                str = "|P|" + str;
            }
            // Rotate the values
            this.getConveyorBelt().enqueue(this.getConveyorBelt().dequeue());
        }
        System.out.println("|Machine " + this.getId() + "|" + str);
    }
}