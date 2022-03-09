/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Product_MngController implements Initializable {

    @FXML
    private AnchorPane Product_Page;
    @FXML
    private Button P_Back;
    @FXML
    private HBox AddProductHbox;
    @FXML
    private JFXButton AddProductButton;
    @FXML
    private JFXButton ShowProduct;
    @FXML
    private JFXButton Shelf_Mng;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void P_Back(ActionEvent event) throws IOException  {
        
        
       
        
          Parent pane=FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
                Product_Page.getChildren().setAll(pane);
    
    
    }

    @FXML
    private void AddProductButton(ActionEvent event) throws IOException {
        
         Parent pane=FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
                 AddProductHbox.getChildren().setAll(pane);
        
        
        
    }

    @FXML
    private void ShowProduct(MouseEvent event) throws IOException {
        
         Parent pane=FXMLLoader.load(getClass().getResource("Show_Product.fxml"));
                 AddProductHbox.getChildren().setAll(pane);
        
        
         
        
    }

    @FXML
    private void Shelf_Mng(MouseEvent event) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource("Shelf_Mng.fxml"));
                 AddProductHbox.getChildren().setAll(pane);
        
        
    }

    @FXML
    private void Expired_Product(ActionEvent event) throws IOException {
    Parent pane=FXMLLoader.load(getClass().getResource("Expired_DATE_Handle.fxml"));
                 AddProductHbox.getChildren().setAll(pane);
   
    }
    
   
    
    
}
