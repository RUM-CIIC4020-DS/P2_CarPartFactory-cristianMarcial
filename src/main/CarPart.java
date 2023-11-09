package main;

/**
 * This class will represent a car part produced by a machine of this. In this class, there are private variables which store 
 * information about the car part, such as its ID, its name, author, its weight, and its defective status. Each variable can 
 * be obtained and changed with the "Getters and Setters" methods and they are initialized by the constructor method.
 * 
 * @author Cristian Marcial cristian.marcial@upr.edu 
 */
public class CarPart {
    
	/**
	 * Unique number that identifies the part.
	 */
	int id;
	
	/**
	 * Name of the part.
	 */
	String name;
	
	/**
	 * Weight of the part.
	 */
	double weight;
	
	/**
	 * It is true if this part is defective; false otherwise.
	 */
	boolean isDetective;
	
    public CarPart(int id, String name, double weight, boolean isDetective) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.isDetective = isDetective;
    }
    
    /**
	 * Returns the id of the part.
	 * 
	 * @return an integer representing the id of the part.
	 */
    public int getId() {
        return this.id;
    }
    
    /**
	 * Sets the id of the part with the given argument.
	 * 
	 * @param id an integer for replacing the previous id of the part.
	 */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
	 * Returns the name of the part.
	 * 
	 * @return a String representing the name of the part.
	 */
    public String getName() {
        return this.name;
    }
    
    /**
	 * Sets the name of the part with the given argument.
	 * 
	 * @param name a String for replacing the previous name of the part.
	 */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
	 * Returns the weight of the part.
	 * 
	 * @return a double value representing the weight of the part.
	 */
    public double getWeight() {
      return this.weight;
    }
    
    /**
	 * Sets the weight number of the part with the given argument.
	 * 
	 * @param weight a double value for replacing the previous weight number of the part.
	 */
    public void setWeight(double weight) {
    	this.weight = weight;
    }

    /**
	 * Returns true if this part is defective; false otherwise.
	 * 
	 * @return a boolean value representing s whether this part is defective or not.
	 */
    public boolean isDetective() {
    	return this.isDetective;
    }
    
    /**
	 * Sets the detective status of the part with the given argument.
	 * 
	 * @param isDetective a boolean for replacing the previous detective status of the part.
	 */
    public void setDetective(boolean isDetective) {
        this.isDetective = isDetective;
    }
    
    /**
     * Returns the parts name as its string representation
     * @return (String) The part name
     */
    public String toString() {
        return this.getName();
    }
}