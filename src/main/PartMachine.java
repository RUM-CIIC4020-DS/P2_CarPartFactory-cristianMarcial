package main;

import data_structures.ListQueue;
import interfaces.Queue;

/**
 * This class will represent a car part produced by the? machine of this.
 * 
 * 
 * @author Cristian Marcial cristian.marcial@upr.edu 
 */
public class PartMachine {
	
	/**
	 * Unique number that identifies the machine.
	 */
	int id;
	
	/**
	 * The car part that this particular machine produces.
	 */
	CarPart part;
	
	/**
	 * The frequency in which a part is made.
	 */
	int period;
	
	/**
	 * Error in the weight of the part being produced.
	 */
	double weightError;
	
	/**
	 * The chance a part might come out defective.
	 */
	int chanceOfDefective;
	
	/**
	 * A queue that has numbers from this period value minus 1 to 0. This will essentially act as a count down 
	 * for when the next part will be produced.
	 */
	Queue<Integer> timer = new ListQueue<Integer>();
	
	/**
	 * This queue will simulate the conveyor belt at the end of the machine. Has 10 spaces available, 
	 * represented by null values (the queue was modified to accept nulls).
	 */
	Queue<CarPart> conveyorBelt = new ListQueue<CarPart>();
	
	/**
	 * The total amount of parts produced by this machine.
	 */
	int totalPartsProduced;
	
	/**
	 * Constructor
	 */
    public PartMachine(int id, CarPart p1, int period, double weightError, int chanceOfDefective) {
        this.id = id;
        this.part = p1;
        this.period = period;
        this.weightError = weightError;
        this.chanceOfDefective = chanceOfDefective;
        
        for(int i=0; i<period; i++) this.timer.enqueue(i);
        //for(int i=0; i<9; i++) this.conveyorBelt.enqueue(null);
        resetConveyorBelt();
    }
    
    /**
   	 * Returns the id of the machine.
   	 * 
   	 * @return an integer representing the id of the machine.
   	 */
    public int getId() {
       return this.id;
    }
    
    /**
	 * Sets the id of the machine with the given argument.
	 * 
	 * @param id an integer for replacing the previous id of the machine.
	 */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
   	 * Returns the timer based on the period value on this machine.
   	 * 
   	 * @return an Integer ListQueue of this period value size holding a ascended sequence of integers.
   	 */
    public Queue<Integer> getTimer() {
    	return this.timer;
    }
    
    /**
	 * Sets the timer of the machine with the given argument.
	 * 
	 * @param timer an Integer ListQueue for replacing the previous timer.
	 */
    public void setTimer(Queue<Integer> timer) {
        this.timer = timer;
    }
    
    /**
   	 * Returns the part which this machine produces.
   	 * 
   	 * @return a CarPart representing the part which this machine produces.
   	 */
    public CarPart getPart() {
    	return this.part;
    }
    
    /**
	 * Changes the part which the machine produce with the given argument.
	 * 
	 * @param part1 a CarPart element for replacing the previous part which was being produced.
	 */
    public void setPart(CarPart part1) {
        this.part = part1;
    }
    
    /**
   	 * Returns the elements on the conveyor of this machine.
   	 * 
   	 * @return an CarParts ListQueue holding car parts being produced.
   	 */
    public Queue<CarPart> getConveyorBelt() {
    	return this.conveyorBelt;
    }
    
    /**
	 * Changes the elements on the conveyor belt with the given argument.
	 * 
	 * @param conveyorBelt a CarPart ListQueue for replacing the previous elements on the conveyor.
	 */
    public void setConveyorBelt(Queue<CarPart> conveyorBelt) {
    	this.conveyorBelt = conveyorBelt;
    }
    
    /**
   	 * Returns the number of the all parts produced by the machine.
   	 * 
   	 * @return an integer representing the number of the parts produced.
   	 */
    public int getTotalPartsProduced() {
    	return this.totalPartsProduced;
    }
    
    /**
	 * Sets the number of all parts produced by the machine with the given argument.
	 * 
	 * @param count an integer for replacing the previous value of the number of all parts produced by the machine.
	 */
    public void setTotalPartsProduced(int count) {
    	this.totalPartsProduced = count;
    }
    
    /**
   	 * Returns the weight error of the a part being produced.
   	 * 
   	 * @return a double value representing the weight error of the part being produced.
   	 */
    public double getPartWeightError() {
    	return this.weightError;
    }
    
    /**
	 * Sets the weight error value of the parts being produced with the given argument.
	 * 
	 * @param partWeightError a double for replacing the previous weight error value.
	 */
    public void setPartWeightError(double partWeightError) {
        this.weightError = partWeightError;
    }
    
    /**
   	 * Returns the value of the chance that a part being produced comes defective.
   	 * 
   	 * @return an integer representing the chance that a part being produced comes defective.
   	 */
    public int getChanceOfDefective() {
    	return this.chanceOfDefective;
    }
    
    /**
	 * Sets the value of the chance that a part being produced comes defective with the given argument.
	 * 
	 * @param chanceOfDefective an integer for replacing the previous chance of being defective value.
	 */
    public void setChanceOfDefective(int chanceOfDefective) {
        this.chanceOfDefective = chanceOfDefective;
    }
    
    public void resetConveyorBelt() {
    	this.conveyorBelt.clear();
    	for(int i=0; i<9; i++) this.conveyorBelt.enqueue(null);
    }
    
    public int tickTimer() {
    	int previous = this.timer.dequeue();
    	this.timer.enqueue(previous);
    	return previous;
    }
    
    public CarPart produceCarPart() {
    	if(this.timer.front()==0) {
    		
    		conveyorBelt.dequeue();
    		this.conveyorBelt.enqueue(part); //part ??
    		this.totalPartsProduced++;
    	}
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