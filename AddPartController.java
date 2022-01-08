package controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
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
 * FXML AddPartController class
 */
public class AddPartController implements Initializable {

    @FXML private Label label;
    
    @FXML private RadioButton inHouse;
    @FXML private RadioButton outsourced;
    
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private TextField inOrOut;
    
  
    
    /**
     * This is the changeLabel method.
     * This method changes the text of a label once a radio-button is pressed.
     * 
     * @param event This is a component specific action
     */
    @FXML public void changeLabel(ActionEvent event) {
        
        label.setText("Company Name");
        
    }
    
    /**
     * This is the changeLabel2 method. 
     * This method changes the text of a label once a radio-button is pressed.
     * 
     * @param event This is a component specific action
     */
    @FXML public void changeLabel2(ActionEvent event) {
        
        label.setText("Machine ID");
        
    }
    
    /**
     * This is the cancel method.
     * This method sends the user back to the main form without saving information.
     * 
     * @param event This is a component specific action
     * @throws Exception 
     * 
     * LOGICAL ERROR
     * The button would cancel the information without the user's confirmation.
     * I implemented a dialog box to solve the issue.
     */
    @FXML public void cancelMethod(ActionEvent event) throws Exception{
        
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
     * This is the isNumeric method.
     * This method determines if a string is numeric.
     * 
     * @param strNum A potentially numeric string
     * @return Returns false or true
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
     * This method saves entered information and sends the user back to the main form.
     * 
     * @param event This is a component specific action
     * @throws IOException 
     * 
     * LOGICAL ERROR
     * The application allowed numerical values to be passed into the name text-field.
     * I solved this by using a boolean value to check whether or not information in the field 
     * is numeric.
     */
    @FXML@SuppressWarnings("null")
 public void saveMethod(ActionEvent event) throws IOException {
        
        try{
        
            int id = generateID();
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
                
                if(name.isEmpty() || inOrOut.getText().isEmpty() || isNum == true)
                {
                    Alert alert4 = new Alert(Alert.AlertType.ERROR);
                    alert4.setTitle("Error");
                    alert4.setContentText("Please fill in appropriate Name or MachineID/Company Name.");
                    alert4.showAndWait();
                }
                
                
                else 
                {
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.isPresent() && result.get() == ButtonType.OK)
                    {

                        if(inHouse.isSelected())
                        {  
                            int machine = Integer.parseInt(inOrOut.getText());

                            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machine));  

                            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }

                            else if(outsourced.isSelected())
                            {
                                String company = inOrOut.getText();


                                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, company));

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
                alert.setContentText("Please fill in appropriate numerical information.");
                alert.showAndWait();
        }
    }
    
    /**
     * This is the generateID method. 
     * This method generates a contiguous id.
     * 
     * @return Returns an id
     */
    public int generateID(){
         int a = 1;
        
        for( Part o: Inventory.getAllParts()){
            if(o.getId()>= a){
                a = o.getId() + 1;
            }
        }
        
        return a;
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
