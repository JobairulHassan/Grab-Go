/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import DatabaseConnection.DBconnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.LocalDate.from;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.sql.*;


/**
 * FXML Controller class
 *
 * @author Mukaffi
 */
public class LoginPageController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXComboBox<String> loginas;
        ObservableList<String> logtype = FXCollections.observableArrayList(
                  "Admin","Receptionist","Shelf Filler"


        );
    @FXML
    private AnchorPane LoginPage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loginas.setItems(logtype);
    }    

    @FXML
    private void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    String Username =username.getText();
    String Password =password.getText();
    String Logas =loginas.getValue();
    
    if(Logas.equals("Admin")){
        try {
            Connection con = DBconnection.connectionDatabase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from  admin where adminName='"+Username+"' and adminpass='"+Password+"'");
            if(rs.next())
            {
                 Parent pane=FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
               LoginPage.getChildren().setAll(pane);
            }
            else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Name  or Password Mismatch");
                    alert.setContentText("Please Enter Again.");
                    alert.showAndWait();
                   username.setText("");
                    password.setText("");
                }
        } catch (Exception e) {
            System.out.println(e);
        }

       
          
          
    }
    else if(Logas.equals("Receptionist")){
        try {
            Connection con = DBconnection.connectionDatabase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from  receptionist where receptionistName='"+Username+"' and receptionistpass='"+Password+"'");
            if(rs.next())
            {
                 Parent pane=FXMLLoader.load(getClass().getResource("ReceptionistHomePage.fxml"));
               LoginPage.getChildren().setAll(pane);
            }
            else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Name  or Password Mismatch");
                    alert.setContentText("Please Enter Again.");
                    alert.showAndWait();
                   username.setText("");
                    password.setText("");
                }
        } catch (Exception e) {
            System.out.println(e);
        }
               
    

    
    }else if(Logas.equals("Shelf Filler")){
         try {
            Connection con = DBconnection.connectionDatabase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from  admin where adminName='"+Username+"' and adminpass='"+Password+"'");
            if(rs.next())
            {
                 Parent pane=FXMLLoader.load(getClass().getResource("Shelf_Filler.fxml"));
               LoginPage.getChildren().setAll(pane);
            }
            else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Name  or Password Mismatch");
                    alert.setContentText("Please Enter Again.");
                    alert.showAndWait();
                   username.setText("");
                    password.setText("");
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        

    }
    
    
    
    }
    
}
        
        
        
        
        
        
        
    
    
   

