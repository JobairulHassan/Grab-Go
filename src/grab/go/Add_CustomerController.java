/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import DatabaseConnection.DBconnection;
import Exceptions.NullValueException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RegexValidator;
import static grab.go.AddEmployeeController.isNumeric;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;


public class Add_CustomerController implements Initializable {

    @FXML
    private JFXTextField cus_id;
    @FXML
    private JFXTextField cus_name;
    @FXML
    private JFXTextField cus_add;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    
    @FXML
    private JFXButton save_cus_info;
    @FXML
    private JFXButton reset_cus_info;
    @FXML
    private JFXComboBox<String> MembershipType;

          ObservableList<String> typeall = FXCollections.observableArrayList(
                  "Gold", "Silver","Platinum"
        );
    @FXML
    private JFXTextField Credit_cus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MembershipType.setItems(typeall);
        
         
         NumberValidator numValidator=new NumberValidator();
        phone.getValidators().add(numValidator);
        numValidator.setMessage("add only 11 digits of your phone number");
        phone.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if(!t1){
                   phone.validate();
                    
                }else{
                    phone.setText("");
                }   
            }
       });
        
       RegexValidator validate = new RegexValidator();
       validate.setRegexPattern("[A-Za-z\\s]+");
       cus_name.setValidators(validate);
       cus_name.getValidators().get(0).setMessage("Name Should be contain only Alphabets");
       
       cus_name.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if(!t1){
                    cus_name.validate();
                }  
            }
       });
       
        
  

    
     

        
        
        
  
        
        
        
     
        // TODO
    }  
    
    
    
     @FXML
    
    
     
     
     public static boolean isNumerics(String str) { 
        try {  
                Double.parseDouble(str);  
                return true;
        } catch(NumberFormatException e){  
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Number is not valid");
            alert.setContentText(null);
            alert.showAndWait();
            return false;
        }  
    }
     
      private boolean validatePnums(){
       if(phone.getText().length()== 11)
        return true;
       else
         return false;
    }
    private boolean validateFieldss() {
        try {
            if (cus_id.getText().isEmpty() || cus_name.getText().isEmpty() ||cus_add.getText().isEmpty() || email.getText().isEmpty()
                    ||  phone.getText().isEmpty() ||Credit_cus.getText().isEmpty()) {

                //Alert Message
                throw new NullValueException("Information Missing!!!", "Please provide all the informations properly.");
            }
        } catch (NullValueException e) {
            return false;
        }
        return true;
    }


 @FXML
   
    private void save_cus_info(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        if (validateFieldss()&& isNumerics(phone.getText())&& validatePnums()){
        
       String CustomerId= cus_id.getText() ;
       String CustomerName= cus_name.getText() ; 
       String CustomerAddress= cus_add.getText() ; 
       String CustomerEmail= email.getText() ; 
       String CustomerPhone= phone.getText() ; 
       String Membershiptype=MembershipType.getValue(); 
       String Creditcus=Credit_cus.getText(); 
            
     
       Customer cd=new Customer( CustomerId,CustomerName,CustomerAddress, CustomerEmail,CustomerPhone, Membershiptype,Creditcus);
        insertCustomer(cd);
        System.out.println(cd);
      
         cus_id.clear();
         cus_name.clear();
         email.clear();
         phone.clear();   
         cus_add.clear();
         MembershipType.setItems(typeall);
         Credit_cus.clear();
        }

    }

    @FXML
    private void reset_cus_info(ActionEvent event) {
   
    }

    private void insertCustomer(Customer cd) throws ClassNotFoundException, SQLException {
      
        
        DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        String query = "insert into Customer (CustomerId, CustomerName,CustomerAddress,CustomerEmail,CustomerPhone,MembershipType,CustomerCredit) values('" + cd.CustomerID+ "','" + cd.CustomerName+ "','" + cd.CustomerAddress + "','" + cd.CustomerEmail + "','" + cd.CustomerPhoneNumber + "','" + cd.MembershipType+ "','" + cd.Credit+ "')";
        System.out.println(query);
        boolean dataInserted = dbc.insertDataToDB(query);
        if(dataInserted)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Customer Added");
            alert.showAndWait();
        }
    }

    
    
    
    
    
    
}
