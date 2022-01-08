package Model;

/**
 * This class inherits from the Part class.
 */
public class Outsourced extends Part{
   
    
    private String companyName;
    
    /**
     * This is the constructor for the class. 
     * This method accepts values for the class's fields.
     * 
     * @param id Id of a part
     * @param name Name of a part
     * @param price Price of a part
     * @param stock Stock of a part
     * @param min Minimum of a part
     * @param max Maximum of a part
     * @param companyName Company name of a part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /**
     * This is the getCompanyName method. 
     * This method gets a company name from an outsourced part.
     * 
     * @return Returns companyName
     */
    public String getCompanyName(){
        
        return companyName;
        
    }
    
    /**
     * This is the setCompanyName method. 
     * This method sets a company name for an outsourced part.
     * 
     * @param companyName Company name of a part
     */
    public void setCompanyName(String companyName){
        
        this.companyName = companyName;
        
    }
}
