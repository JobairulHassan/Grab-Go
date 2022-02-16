/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdminHomePageController implements Initializable {

    @FXML
    private AnchorPane homePage;
    @FXML
    private ImageView emp_mang;
    @FXML
    private ImageView stock_mng;
    @FXML
    private ImageView cus_mng;
    @FXML
    private ImageView pro_mng;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  

   

    @FXML
    private void stock_mng(MouseEvent event)throws IOException {
        
           Parent pane=FXMLLoader.load(getClass().getResource("Stock_Mng.fxml"));
               homePage.getChildren().setAll(pane);
      
    }

    @FXML
    private void emp_mang(MouseEvent event)throws IOException {
        
        Parent pane=FXMLLoader.load(getClass().getResource("GrabAndGo.fxml"));
               homePage.getChildren().setAll(pane);
    }

   

    @FXML
    private void pro_mng(MouseEvent event)throws IOException  {
        
        
        
        Parent pane=FXMLLoader.load(getClass().getResource("Product_Mng.fxml"));
               homePage.getChildren().setAll(pane);
    }

    @FXML
    private void cus_mng(MouseEvent event) throws IOException {
         Parent pane=FXMLLoader.load(getClass().getResource("Customer_Mng.fxml"));
               homePage.getChildren().setAll(pane);
    }
    }
    

