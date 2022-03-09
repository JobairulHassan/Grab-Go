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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FUJITSU
 */
public class Update_ProductController implements Initializable {

    @FXML
    private JFXTextField Pro_id;
    @FXML
    private JFXTextField Pro_name;
    @FXML
    private JFXTextField Shelf_No;
    @FXML
    private JFXTextField Price;
    @FXML
    private Button Update_pro;
    @FXML
    private JFXComboBox<String> Block_No;
    ObservableList<String> blocks = FXCollections.observableArrayList(
                  "Block A","Block B","Block C","Block D","Block E"
        );
    
    
    
    @FXML
    private JFXTextField Shelf_col;
    @FXML
    private AnchorPane updateProAnc;
    @FXML
    private JFXComboBox<String> shelf_roww;
   ObservableList<String> Shelf = FXCollections.observableArrayList(
                  "1","2","3","4","5","6"
        );
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Block_No.setItems(blocks);
         shelf_roww.setItems(Shelf);
        
        String product_id=Shelf_MngController.sm.Product_Id;
        String product_Name=Shelf_MngController.sm.Product_Name;
        String blockNo=Shelf_MngController.sm.Block_no;
        String shelfNo=Shelf_MngController.sm.Shelf_no;
        int shelfRow=Shelf_MngController.sm.Shelf_row;
        int shelfCol=Shelf_MngController.sm.Shelf_col;
        
        Pro_id.setText(product_id);
        Pro_name.setText(product_Name);
        Block_No.setValue(blockNo);
        Shelf_No.setText(shelfNo);
      shelf_roww.setValue(Integer.toString(shelfRow));
        Shelf_col.setText(Integer.toString(shelfCol));
        
        //unit_price from db
        
        String q="SELECT UnitPrice FROM Products where ProductID='"+product_id+"'";
        DBconnection dbc =new DBconnection();
        try { 
            dbc.connectToDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Update_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Update_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbc.queryToDB(q);
        ResultSet rs= dbc.queryToDB(q);
        try {
            if(rs.next()){
                Price.setText(rs.getString("UnitPrice"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Update_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Update_productOnAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        String row=shelf_roww.getValue();
        int rn = Integer.parseInt(row);
        
      String query = "UPDATE Shelf SET Block_shelfNo='"+Shelf_No.getText()+"', Shelf_row="+rn+", Shelf_col="+Shelf_col.getText()+", Add_status='No Pending' WHERE ProductID='"+Pro_id.getText()+"'";
    
       System.out.println(query);
        //ResultSet dataInserted = dbc.queryToDB(query);
         boolean dataIn = dbc.insertDataToDB(query);
        System.out.println("Updated?: "+dataIn);
        if(dataIn){
            String q="UPDATE Products SET ShelfNo='"+Block_No.getValue()+"',UnitPrice= "+Price.getText()+" WHERE ProductID='"+Pro_id.getText()+"'";
            boolean dataInserted=dbc.insertDataToDB(q);
        
            if(dataInserted){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Product Information updated");
                //alert.setContentText(msg2);
                alert.showAndWait();
            
                Parent pane=FXMLLoader.load(getClass().getResource("Shelf_Mng.fxml"));
                updateProAnc.getChildren().setAll(pane);
            }
        }
       // System.out.println("Inserted?: "+dataInserted);
        
    }
    
}
