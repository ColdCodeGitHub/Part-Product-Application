package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains all created parts and products.
 */
@SuppressWarnings("UtilityClassWithoutPrivateConstructor")
public class Inventory {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    /**
     * This is the addPart method. 
     * This method adds a part to an ObservableList.
     * 
     * @param newPart This is a new part
     */
    public static void addPart(Part newPart){
        
        allParts.add(newPart);
        
    }
    
    /**
     * This is the addProduct method. 
     * This method adds a product to an ObservableList.
     * 
     * @param newProduct This is a new product
     */
    public static void addProduct(Product newProduct){
        
        allProducts.add(newProduct);
        
    }
    
    /**
     * This is one out of two lookupPart methods. 
     * This method searches for a part's id in an ObservableList.
     * 
     * @param partId Id of a part
     * @return Returns a part or null
     */
    public static Part lookupPart(int partId){
        
        for(Part part: Inventory.getAllParts())
        {
            if(part.getId() == partId)
            {
                return part;
            }  
        }
          
        return null;
    }
    
    /**
     * This is one out of two lookupProduct methods.
     * This method searches for a product's id in an ObservableList.
     * 
     * @param productId Id of a product
     * @return Returns a product or null
     */
    public static Product lookupProduct(int productId){
        
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getId() == productId)
            {
                return product;
            }
        }
        
        return null; 
    }
    
    /**
     * This is one out of two lookupPart methods.
     * This method searches for a part's name in an ObservableList.
     * 
     * @param partName Name of a part
     * @return Returns allParts or null
     */
    public static ObservableList<Part> lookupPart(String partName){
        
        boolean found = false;
        
        for(Part part : Inventory.getAllParts())
        {
            if(part.getName().equals(partName))
            {
                found = true;
                return allParts;
            }
        }
       
        return null;
        
    }
    
    /**
    * This is one out of two lookupProduct methods.
    * This method searches for a product's name in an ObservableList.
    * 
    * @param productName Name of a product
    * @return Returns allProducts or null
    */
    public static ObservableList<Product> lookupProduct(String productName){
        
       boolean found = false;
       
       for(Product product : Inventory.getAllProducts())
       {
           if(product.getName().equals(productName))
           {
                found = true;
                return allProducts;
           }
       }
        
        return null;
        
    }
    
    
    /**
     * This is updateProduct method. 
     * This method replaces a product with another product.
     * 
     * @param index Id of a product
     * @param newProduct This is a product
     */
    public static void updateProduct(int index, Product newProduct){
        
        int idIndex = -1;
        
        for(Product product : Inventory.getAllProducts())
        {
            idIndex++;
            
            if(product.getId() == index)
            {    
                Inventory.getAllProducts().set(idIndex, newProduct);  
            }  
        }
    }
    
    /**
     * This is the updatePart method.
     * This method replaces a part with another part.
     * 
     * @param index Id of a part
     * @param selectedPart  This is a part
     */
    public static void updatePart(int index, Part selectedPart){
        
        int idIndex = -1;
        
        if(selectedPart instanceof InHouse)
        {
            for(Part part : Inventory.getAllParts())
            {   
                idIndex++;
                
                if(part.getId() == index)
                {
                    Inventory.getAllParts().set(idIndex, selectedPart);
                } 
            }
        }
            
        else if(selectedPart instanceof Outsourced)
        {
            for(Part part : Inventory.getAllParts())
            {   
                idIndex++;
                
                if(part.getId() == index)
                {
                    Inventory.getAllParts().set(idIndex, selectedPart);
                }
            }
        }     
    }   
    
    
    
    /**
     * This is the deletePart method.
     * This method removes a part from an ObservableList.
     * 
     * @param selectedPart This is a part
     * @return Returns false
     */
    public static boolean deletePart(Part selectedPart){
        
        allParts.remove(selectedPart);
        return false;
        
    }
    
    /**
     * This is the deleteProduct method. 
     * This method removes a product from an ObservableList.
     * 
     * @param selectedProduct This is a product
     * @return Returns false
     */
    public static boolean deleteProduct(Product selectedProduct){
        
        allProducts.remove(selectedProduct);
        return false;
        
    }
    
    /**
     * This is the getAllParts method. 
     * This method gets the ObservableList for all parts.
     * 
     * @return Returns allParts
     */
    public static ObservableList<Part> getAllParts(){
        
        return allParts;
        
    }
    
    /**
     * This is the getAllProducts method. 
     * This method gets the ObservableList for all products.
     * 
     * @return Returns allProducts
     */
    public static ObservableList<Product> getAllProducts(){
        
        return allProducts;
        
    }
    
}
