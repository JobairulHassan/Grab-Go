/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class GrabAndGoController implements Initializable {

    @FXML
    private Button showEmployee;
    public static HBox EmployeeHbox;
    private AnchorPane GrabPage;
    @FXML
    private HBox EmployeeHbox1;
    @FXML
    private Button back;
    @FXML
    private Button add_emp;
    @FXML
    private AnchorPane EmployeePage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }  

   

    @FXML
    private void showAll(ActionEvent event) throws IOException {
             Parent pane=FXMLLoader.load(getClass().getResource("ShowEmployee.fxml"));
                EmployeeHbox1.getChildren().setAll(pane);
    
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
          Parent pane=FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
              EmployeePage  .getChildren().setAll(pane);
    
    }

    private void addEmployee(ActionEvent event) throws IOException {
    
      Parent pane=FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
                EmployeeHbox1.getChildren().setAll(pane);
    
    }

    @FXML
    private void add_emp(ActionEvent event) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
                EmployeeHbox1.getChildren().setAll(pane);
    }
    
}
