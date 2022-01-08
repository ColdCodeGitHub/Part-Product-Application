package controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
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
 * FXML ModifyProductController class
 *
 * @author louis
 */
public class ModifyProductController implements Initializable {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    
    @FXML private TableView<Part> associatedPartsTableView;
    @FXML private TableColumn<Part, Integer> idAsPartCol;
    @FXML private TableColumn<Part, String> nameAsPartCol;
    @FXML private TableColumn<Part, Integer> invAsPartCol;
    @FXML private TableColumn<Part, Double> priceAsPartCol;
    
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<Part, Integer> idPartCol;
    @FXML private TableColumn<Part, String> namePartCol;
    @FXML private TableColumn<Part, Integer> invPartCol;
    @FXML private TableColumn<Part, Double> pricePartCol;
    
    @FXML private TextField search;
   
    private ObservableList<Part> associatedParts;
    private ObservableList<Part> parts;
    private Product prod;
    
    

    /**
     * This is the isNumeric method. 
     * This method determines if a string is numeric.
     * 
     * @param strNum This a potentially numeric string
     * @return Returns true or false
     */
    public boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        int d = Integer.parseInt(strNum);
    } catch (NumberFormatException e) {
        return false;
    }
    return true;
}
    
    
    /**
     * This is the save method. 
     * This button saves all entered information and sends the user back to the main form.
     * 
     * @param event This is a component specific action
     * @throws IOException 
     * 
     * LOGICAL ERROR
     * The application allowed numerical values to be passed into the name text-field.
     * I solved this by using a boolean value to check whether or not information in the field 
     * is numeric.
     */
    @FXML public void saveButton(ActionEvent event) throws IOException {
        
        try{
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int stock = Integer.parseInt(invField.getText());
            double price = Double.parseDouble(priceField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText()); 
            boolean isNum;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will save your information, do you want to continue?");

            if(min > max)
            {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("Min cannot be greater than Max!");
                alert2.showAndWait();
            }

                else if(stock > max || stock < min)
                {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setContentText("Inv must be between Max and Min!");
                    alert3.showAndWait();
                }

            if(min < max && stock <= max && stock >= min)
            {
                isNum = isNumeric(name);
                
                if(name.isEmpty() || isNum == true)
                {
                    Alert alert4 = new Alert(Alert.AlertType.ERROR);
                    alert4.setContentText("Please provide an appropriate name.");
                    alert4.showAndWait();
                }
                else{
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.isPresent() && result.get() == ButtonType.OK)
                    {

                        if(prod == null)
                        {
                            Product product = new Product(id, name, price, stock, min, max);

                            for(Part part : associatedPartsTableView.getItems())
                            {
                                product.addAssociatedPart(part);   
                            }

                            Inventory.updateProduct(id, product);

                            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }

                        else if(prod != null)
                        {

                            Product product = new Product(id, name, price, stock, min, max);

                            for(Part part : associatedPartsTableView.getItems())
                            {
                                product.addAssociatedPart(part);   
                            }

                            Inventory.updateProduct(id, product);

                            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }
                    }
                }
            }
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill appropriate numerical or name information before saving.");
            alert.showAndWait();
        }
    }
    
    /**
     * This is the cancel method.
     * This method sends the user back to the main form without saving any information.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * LOGICAL ERROR
     * The button would cancel the information without the user's confirmation.
     * I implemented a dialog box to solve the issue.
     */
    @FXML public void cancelButton(ActionEvent event) throws Exception {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear your information, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    
    /**
     * This the search method.
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
    @FXML public void searchMethod(ActionEvent event){
        
        String text = search.getText();
        
        if(search.getText().isEmpty())
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
            parts = Inventory.lookupPart(tempName);
            
            if(parts != Inventory.getAllParts())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part does not exist.");
                alert.showAndWait();
            }
            
            else if(parts == Inventory.getAllParts())
            {
                for(Part part : parts)
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
     * This is the deleteAssociatedPart method. 
     * This method removes an associated part from a product.
     * 
     * @param event This is a component specific action
     * 
     * LOGICAL ERROR
     * I originally tried to delete the associated parts by removing parts
     * from the allParts ObservableList in the Inventory class. I rectified my
     * mistake by utilizing the deleteAssociatedPart method in the Product class.
     */
    @FXML public void deleteAssociatedPart(ActionEvent event){
    
        
        Part part = associatedPartsTableView.getSelectionModel().getSelectedItem();
        
        if(associatedPartsTableView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an associated part.");
            alert.showAndWait();
        }
        
        else if (associatedPartsTableView.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
            
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                prod.deleteAssociatedPart(part);
                associatedParts = prod.getAllAssociatedParts();
            }
        }
         
        
    }
    
    /**
     * This is the add method. 
     * This method associates a part with a product.
     * 
     * @param event This is a component specific action
     * 
     * RUNTIME ERROR
     * NumberFormatException
     * In order to add associated parts, I had to create a product with
     * information from the text-fields. If the fields were empty, then 
     * an exception would be thrown. I implemented a try/catch statement 
     * with a dialog box to solve the issue.
     */
    @FXML public void addButton(ActionEvent event){
        
        try{
            Part part = partsTableView.getSelectionModel().getSelectedItem();

            if(partsTableView.getSelectionModel().getSelectedItem() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a part.");
                alert.showAndWait();
            }

            else if(partsTableView.getSelectionModel().getSelectedItem() != null)
            {
                if(prod == null)
                {
                    prod = createProduct();

                    prod.addAssociatedPart(part);

                    associatedParts = prod.getAllAssociatedParts();

                    associatedPartsTableView.setItems(associatedParts);
                } 

                else if(part != null)
                {

                    prod.addAssociatedPart(part);

                    associatedParts = prod.getAllAssociatedParts();

                    associatedPartsTableView.setItems(associatedParts);
                }      
            }
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in product information before adding parts.");
            alert.showAndWait();
        }
    }
    
    /**
     * This is the createProduct method. 
     * This method creates a temporary product.
     * 
     * @return Returns a product
     */
    public Product createProduct(){
       
        
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int stock = Integer.parseInt(invField.getText());
        double price = Double.parseDouble(priceField.getText());
        int max = Integer.parseInt(maxField.getText());
        int min = Integer.parseInt(minField.getText()); 

        Product product = new Product(id, name, price, stock, min, max);
        
        return product;
    }
     
    /**
     * This the init method.
     * This method transfers product information from the main form to the modify product form.
     * 
     * @param product This is a product
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public void initMethod(Product product){
        
            idField.setText(Integer.toString(product.getId()));
            nameField.setText(product.getName());
            invField.setText(Integer.toString(product.getStock()));
            priceField.setText(Double.toString(product.getPrice()));
            maxField.setText(Integer.toString(product.getMax()));
            minField.setText(Integer.toString(product.getMin()));

            prod = product;

            associatedParts = prod.getAllAssociatedParts();

            associatedPartsTableView.setItems(associatedParts);
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
        
      
        
        idAsPartCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameAsPartCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invAsPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceAsPartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
      
    }    
    
}
