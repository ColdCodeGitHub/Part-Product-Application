package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class stores values and associated parts to create products.
 */
public class Product {
    
    // Observable List
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
  
    
    // Fields
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    /**
     * This is the constructor for the class. 
     * This method accepts values for the product's fields.
     * 
     * @param id Id of a product
     * @param name Name of a product
     * @param price Price of a product
     * @param stock Stock of a product
     * @param min Minimum of a product
     * @param max Maximum of a product
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        
    }
    
    /**
     * This is the addAssociatedPart method. 
     * This method associates a selected part to a product.
     * 
     * @param part This is a part
     */
    public void addAssociatedPart(Part part){
        
        associatedParts.add(part);
        
    }
    
    /**
     * This is the deleteAssociatedPart method. 
     * This method removes an associated part from a product.
     * 
     * @param part This is a part
     * @return Returns false
     */
    public boolean deleteAssociatedPart(Part part){
        
        
        associatedParts.remove(part);
        return false;
        
    }
    
    /**
     * This is the getAllAssociatedParts method. 
     * This method gets the ObservableList that contains associated parts.
     * 
     * @return Returns associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        
        return associatedParts;
        
    }
    
    /**
     * This is the getId method. 
     * This method gets a product's id.
     * 
     * @return Returns id
     */
    public int getId(){
        
        return id;
        
    }
    
    /**
     * This is the getName method. 
     * This method gets a product's name.
     * 
     * @return Returns name
     */
    public String getName(){
        
        return name;
        
    }
    
    /**
     * This is the getPrice method.
     * This method gets the price of a product.
     * 
     * @return Returns price
     */
    public double getPrice(){
        
        return price;
        
    }
    
    /**
     * This is the getStock method.
     * This method gets the stock of a product.
     * 
     * @return Returns stock
     */
    public int getStock(){
        
        return stock;
        
    }
    
    /**
     * This is the getMin method. 
     * This method gets the minimum of a product.
     * 
     * @return Returns min
     */
    public int getMin(){
        
        return min;
        
    }
    
    /**
     * This is the getMax method.
     * This method gets the maximum of a product.
     * 
     * @return Returns max
     */
    public int getMax(){
        
        return max;
        
    }
    
   /**
    * This is the setId method. 
    * This method sets the id of a product.
    * 
    * @param id Id of a product
    */
    public void setId(int id){
        
        this.id = id;
        
    }
    
    /**
     * This is the setName method. 
     * This method sets the name of a product.
     * 
     * @param name Name of a product
     */
    public void setName(String name){
        
        this.name = name;
        
    }
    
    /**
     * This is the setPrice method. 
     * This method sets the price of a product.
     * 
     * @param price Price of a product
     */
    public void setPrice(double price){
        
        this.price = price;
        
    }
    
    /**
     * This is the setStock method. 
     * This method sets the stock of a product.
     * 
     * @param stock Stock of a product
     */
    public void setStock(int stock){
        
        this.stock = stock;
        
    }
    
    /**
     * This is the setMin method. 
     * This method sets the minimum of a product.
     * 
     * @param min Minimum of a product
     */
    public void setMin(int min){
        
        this.min = min;
        
    }
    
    /**
     * This is the setMax method. 
     * This method sets the maximum of a product.
     * 
     * @param max Maximum of a product
     */
    public void setMax(int max){
        
        this.max = max;
        
    }        
}
