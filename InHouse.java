package Model;

/**
 * This class inherits from the Part class.
 */
public class InHouse extends Part{
    
    private int machineId;
    
    /**
     * This is the constructor for the class. 
     * This method accepts values for the class's fields.
     * 
     * @param id Id of the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Stock of the part
     * @param min Minimum of the part
     * @param max Maximum of the part
     * @param machineId MachineId of the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /**
     * This is the getMachineId method. 
     * This method gets machineId from an InHouse part.
     * 
     * @return Returns machineId
     */
    public int getMachineId(){
        
        return machineId;
    }
    
    /**
     * This is the setMachineId method. 
     * This method sets the machineId of an InHouse part.
     * 
     * @param machineId MachineId of the part
     */
    public void setMachineId(int machineId){
        
        this.machineId = machineId;
        
    }
}
