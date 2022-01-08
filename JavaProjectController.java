package controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML JavaProjectController class
 *
 * @author louis
 */
public class JavaProjectController implements Initializable {
   
    @FXML private Button closeButton;
    
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<Part, Integer> idPartCol;
    @FXML private TableColumn<Part, String> namePartCol;
    @FXML private TableColumn<Part, Integer> invPartCol;
    @FXML private TableColumn<Part, Double> pricePartCol;
    
    @FXML private TableView<Product> productsTableView;
    @FXML private TableColumn<Product, Integer> idProductCol;
    @FXML private TableColumn<Product, String> nameProductCol;
    @FXML private TableColumn<Product, Integer> invProductCol;
    @FXML private TableColumn<Product, Double> priceProductCol;
    
    @FXML private TextField searchParts;
    @FXML private TextField searchProducts;
    
    private ObservableList<Part> Parts;
    private ObservableList<Product> Products;
    
    

    
    /**
     * This is the exit method.
     * This method closes the application. 
     * 
     * @param event This is a component specific action
     * 
     * LOGICAL ERROR
     * The button would close the application without the users confirmation.
     * I implemented and if statement and a dialog box to fix the issue.
     */
    @FXML public void exitButton(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will close the application, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }
    
    /**
     * This is the modifyProduct method.
     * This method sends the user to the modify part form.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * RUNTIME ERROR
     * NullPointerException
     * Null information would be passed into the table-views.
     * I fixed this by implementing a try/catch statement 
     * to show a dialog box instead of showing stack trace errors.
     */
    @FXML public void modifyProduct(ActionEvent event) throws Exception{
      
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.load();

            ModifyProductController controller = loader.getController();
            controller.initMethod(productsTableView.getSelectionModel().getSelectedItem());

            stage.setScene(new Scene(scene));
            stage.show();
        }
        
        catch(NullPointerException e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a product.");
            alert.showAndWait();
            
        }
    }
    
    /**
     * This is the addProduct method. 
     * This method sends the user to the add product form.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * RUNTIME ERROR
     * NullPointerException
     * The scene would not change when I pressed the button.
     * I had to ensure that the controller was in the correct package.
     */
   @FXML public void addProduct(ActionEvent event) throws Exception{
       
       Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
       Parent scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
       stage.setScene(new Scene(scene));
       stage.show();
       
   }
    
    /**
     * This is the modifyPart method.
     * This method sends the user to the modify part form.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * LOGICAL ERROR
     * The application would allow me to access the modify part form without
     * passing in part information. I implemented if/else statements and a
     * dialog box to prevent the issue from happening again.
     */
    @FXML public void modifyPart(ActionEvent event) throws Exception{
        
        if(partsTableView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part.");
            alert.showAndWait();
        }
        
        else if(partsTableView.getSelectionModel().getSelectedItem() != null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.load();

            if(partsTableView.getSelectionModel().getSelectedItem() instanceof InHouse)
            {
                ModifyPartController controller = loader.getController();
                controller.initInMethod(partsTableView.getSelectionModel().getSelectedItem());
            }

            else if(partsTableView.getSelectionModel().getSelectedItem() instanceof Outsourced)
            {
               ModifyPartController controller = loader.getController();
               controller.initOutMethod(partsTableView.getSelectionModel().getSelectedItem()); 
            }
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    
    /**
     * This is the addPart method.
     * This method sends the user to the add part form.
     * 
     * @param event This is a component specific action
     * @throws Exception
     */
    @FXML public void addPart(ActionEvent event) throws Exception{
        
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * This is the search method for products. 
     * This method searches through the products table.
     * 
     * @param event This is a component specific action
     * 
     * RUNTIME ERROR
     * NumberFormatException
     * If a non-numeric string was passed into the variable, the method
     * would try to parse it into an int.
     * I fixed this issue by implementing a try/catch statement to 
     * search for a product with a string instead of an int.
     */
    @FXML public void searchMethodProducts(ActionEvent event){
        
        String text = searchProducts.getText();
        
        if(searchProducts.getText().isEmpty())
        { 
            productsTableView.getSelectionModel().clearSelection();
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a Product ID or a Product Name.");
            alert.showAndWait();
            
            return;
        }
        
        try{
            int tempID = Integer.parseInt(text);
            Product product = Inventory.lookupProduct(tempID);
            
            if(product == null)
            { 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Product does not exist.");
                alert.showAndWait();
            }
            
            else if(product.getId() == tempID)
            {
                productsTableView.getSelectionModel().select(product); 
            }
        }
        catch(NumberFormatException e)
        {
            String tempName = text;
            Products = Inventory.lookupProduct(tempName);
            
            if(Products != Inventory.getAllProducts())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Product does not exist.");
                alert.showAndWait();
            }
            
            else if(Products == Inventory.getAllProducts())
            {
                for(Product product : Products)
                {
                    if(product.getName().equals(tempName))
                    {
                        productsTableView.getSelectionModel().select(product);
                    }

                }
            }
        }
    }
    
    /**
     * This is the search method for parts.
     * This method searches through the parts table.
     * 
     * @param event This is a component specific action
     * 
     * RUNTIME ERROR
     * NumberFormatException
     * If a non-numeric string was passed into the variable, the method
     * would try to parse it into an int.
     * I fixed this issue by implementing a try/catch statement to
     * search for a part using a string instead of an int.
     */
    @FXML public void searchMethodParts(ActionEvent event){
        
        
        String text = searchParts.getText();
        
        if(searchParts.getText().isEmpty())
        {
            partsTableView.getSelectionModel().clearSelection(); 
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a Part ID or a Part Name.");
            alert.showAndWait();
            
            return;
        }
        
        
        try{
            int tempID = Integer.parseInt(text);
            Part part = Inventory.lookupPart(tempID);
            
            if(part == null)
            { 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part does not exist.");
                alert.showAndWait();
            }
            
            else if(part.getId() == tempID)
            {
                partsTableView.getSelectionModel().select(part); 
            }
        }
        catch(NumberFormatException e)
        {
            String tempName = text;
            Parts = Inventory.lookupPart(tempName);
            
            if(Parts != Inventory.getAllParts())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part does not exist.");
                alert.showAndWait();
            }
            
            else if(Parts == Inventory.getAllParts())
            {
                for(Part part : Parts)
                {
                    if(part.getName().equals(tempName))
                    {
                        partsTableView.getSelectionModel().select(part);
                    }

                }
            }
        }
     
    }
    
    /**
     * This is the deletePart method. 
     * This method deletes a part from the parts table.
     * 
     * @param event This is a component specific action
     * 
     * LOGICAL ERROR
     * The button did nothing if a part was not selected. I implemented 
     * if/else statements and dialog boxes to solve the issue.
     */
    @FXML public void deletePart(ActionEvent event){
        
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        
        if(partsTableView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part.");
            alert.showAndWait();
        }
        
        else if(partsTableView.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
            Optional<ButtonType> result = alert.showAndWait();
        
            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                Inventory.deletePart(part);
            }           
        }    
    }
    
    /**
     * This is the deleteProduct method. 
     * This method deletes a product from the products table.
     * 
     * @param event This is a component specific action
     * 
     * LOGICAL ERROR
     * The button would delete products with parts associated with it.
     * I implemented nested if statements to ensure only products without 
     * associated parts could be deleted.
     */
    @FXML public void deleteProduct(ActionEvent event){
        
        Product product = productsTableView.getSelectionModel().getSelectedItem();
        
        if(productsTableView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a product.");
            alert.showAndWait();
        }
        
        else if(productsTableView.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();
        
            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                
                if(product.getAllAssociatedParts().size() < 1)
                {
                    Inventory.deleteProduct(product);
                }
                
                else if(product.getAllAssociatedParts().size() >= 1)
                {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setContentText("Cannot delete a product with associated parts!");
                    alert2.showAndWait();
                }
            }           
        }
    }
 
    
    /**
     * This is the initialize method.
     * This method initializes the controller class.
     * 
     * @param url
     * @param rb 
     */ 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        partsTableView.setItems(Inventory.getAllParts());
        
        idPartCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namePartCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        productsTableView.setItems(Inventory.getAllProducts());
        
        idProductCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProductCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invProductCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceProductCol.setCellValueFactory(new PropertyValueFactory<>("price"));   
    }   
}
