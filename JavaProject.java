package Model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * This class starts an application meant for inventory management. 
 * 
 * FUTURE ENHANCEMENTS
 * At the moment, the ability to search for parts and products functions correctly, but only when the part/product
 * name or id is spelled exactly as it was originally entered. In the future I would like
 * to optimize the search functions to recognize names regardless of capitalization.
 * I would also like to add extra columns in the table-views so users could see whether or not parts
 * are in-house or outsourced without having to enter the modify part form.
 */
public class JavaProject extends Application{
   
    // The javadoc files are located at NetBeansProjects/JavaProject/src/javadoc
    
    /**
     * This is the main method for the program. 
     * This main method is the first method called in this program
     * 
     * @param args This is an argument
     */
    public static void main(String[] args){
   
        launch(args);
      
    }

    /**
     * This is the start method. 
     * This method loads the main form of the GUI
     * 
     * @param stage This is a stage
     * @throws java.io.IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("/view/JavaProject.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main");
        stage.show();   
     }
}