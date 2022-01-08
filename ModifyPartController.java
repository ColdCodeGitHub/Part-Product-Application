package controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML ModifyPartController class
 *
 * @author Louis Schumaker
 */
public class ModifyPartController implements Initializable {
    
    @FXML private Label label;
    
    @FXML private RadioButton inHouse;
    @FXML private RadioButton outsourced;
    
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField stockField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private TextField otherField;
    
    private InHouse existingInPart;
    private Outsourced existingOutPart;
    
    /**
     * This is the init method for InHouse parts.
     * This method transfers part information from the main form to the modify part form.
     * 
     * @param part This is a part
     */
    public void initInMethod(Part part){
      
        existingInPart = (InHouse) part;
       
        
            inHouse.setSelected(true);
            label.setText("Machine ID");
            
            idField.setText(Integer.toString(existingInPart.getId()));
            nameField.setText(existingInPart.getName());
            stockField.setText(Integer.toString(existingInPart.getStock()));
            priceField.setText(Double.toString(existingInPart.getPrice()));
            maxField.setText(Integer.toString(existingInPart.getMax()));
            minField.setText(Integer.toString(existingInPart.getMin()));
            otherField.setText(Integer.toString(existingInPart.getMachineId()));
                
    }
    
    /**
     * This is the init method for Outsourced parts.
     * This method transfers part information from the main form to the modify part form.
     * 
     * @param part This is a part
     */
     public void initOutMethod(Part part){
      
        existingOutPart = (Outsourced) part;
      
            outsourced.setSelected(true);
            label.setText("Company Name");
            
            idField.setText(Integer.toString(existingOutPart.getId()));
            nameField.setText(existingOutPart.getName());
            stockField.setText(Integer.toString(existingOutPart.getStock()));
            priceField.setText(Double.toString(existingOutPart.getPrice()));
            maxField.setText(Integer.toString(existingOutPart.getMax()));
            minField.setText(Integer.toString(existingOutPart.getMin()));
            otherField.setText(existingOutPart.getCompanyName());
    
    }
    
    /**
     * This is the changeLabel method.
     * This method changes the label's text when a radio-button is pressed.
     * 
     * @param event This is a component specific action
     * @return void
     */
    @FXML void changeLabel(ActionEvent event) {
        
        label.setText("Company Name");
        
    }
    
    /**
     * This is the changeLabel2 method. 
     * This method changes the label's text when a radio-button is pressed.
     * 
     * @param event This is a component specific action
     * @return void
     */
    @FXML void changeLabel2(ActionEvent event) {
        
        label.setText("Machine ID");
        
    }
    
    
    /**
     * This is the cancel method.
     * This method will send the user back to the main form without saving any information.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * LOGICAL ERROR
     * The button would cancel the information without the user's confirmation.
     * I implemented a dialog box to solve the issue.
     */
    @FXML public void cancelButton(ActionEvent event) throws Exception {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear your updates, do you want to continue?");
        
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
     * This is the isNumeric method. 
     * This method determines if a string is numeric.
     * 
     * @param strNum This is a potentially numeric string
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
     * This method saves all entered information and sends the user back to the main form.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * LOGICAL ERROR
     * The application allowed numerical values to be passed into the name text-field.
     * I solved this by using a boolean value to check whether or not information in the field 
     * is numeric.
     */
    @FXML public void saveButton(ActionEvent event) throws Exception {
       
        try{
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
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
                
                if(name.isEmpty() || otherField.getText().isEmpty() || isNum == true)
                {
                    Alert alert4 = new Alert(Alert.AlertType.ERROR);
                    alert4.setTitle("Error");
                    alert4.setContentText("Please fill in part information before saving.");
                    alert4.showAndWait();
                }
                
                else{
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.isPresent() && result.get() == ButtonType.OK)
                    {

                        if(inHouse.isSelected())
                        {  
                            int machine = Integer.parseInt(otherField.getText());
                            InHouse in = new InHouse(id, name, price, stock, min, max, machine);
                            Inventory.updatePart(id, in); 

                            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }

                            else if(outsourced.isSelected())
                            {
                                String company = otherField.getText();
                                Outsourced out = new Outsourced(id, name, price, stock, min, max, company);
                                Inventory.updatePart(id, out);

                                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                                Parent scene = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
                                stage.setScene(new Scene(scene));
                                stage.show();
                            }

                            else if(!inHouse.isSelected() && !outsourced.isSelected())
                            {
                                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                                alert2.setContentText("Please select InHouse or Outsourced.");
                                alert2.showAndWait();
                            }


                    }
                }
            }
        }
        catch(NumberFormatException e)
        { 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in part information before saving.");
            alert.showAndWait(); 
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
        
        
        
    }    
    
}
