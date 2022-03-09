/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import DatabaseConnection.DBconnection;
import static grab.go.ShowEmployeeController.Emplist;
import static grab.go.Show_ProductController.productList;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Mukaffi
 */
public class Expired_DATE_HandleController implements Initializable {

    @FXML
    private TableView<ShelfManage> ProductTable;
    @FXML
    private TableColumn<ShelfManage, String> ProductIdCol;
    @FXML
    private TableColumn<ShelfManage, String> ProductName;
    @FXML
    private TableColumn<ShelfManage, String> Blockno;
    @FXML
    private TableColumn<ShelfManage, Integer> ShelfNumber;
    @FXML
    private TableColumn<ShelfManage, Integer> ShelfROw;
    @FXML
    private TableColumn<ShelfManage, Integer> ShelfColumn;
    @FXML
    private TableColumn<ShelfManage, LocalDate> MFG;
    @FXML
    private TableColumn<ShelfManage, LocalDate> EXP;
    ObservableList<ShelfManage> Expire_productMngList=FXCollections.observableArrayList();

    /**
     *     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            Expire_productMngList=getAllProduct();
            
            
// TODO
        } catch (SQLException ex) {
            Logger.getLogger(Show_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Show_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProductIdCol.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Product_Id"));
        ProductName.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Product_Name"));
        ShelfColumn.setCellValueFactory(new PropertyValueFactory<ShelfManage, Integer>("Shelf_col"));
        Blockno.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Block_no"));
        ShelfNumber.setCellValueFactory(new PropertyValueFactory<ShelfManage, Integer>("Shelf_no"));
        ShelfROw.setCellValueFactory(new PropertyValueFactory<ShelfManage, Integer>("Shelf_row"));
        MFG.setCellValueFactory(new PropertyValueFactory<ShelfManage, LocalDate>("MFG_Date"));
        EXP.setCellValueFactory(new PropertyValueFactory<ShelfManage, LocalDate>("Exp_Date")); 
        
     ProductTable.setItems(Expire_productMngList);
      
    }    
  public  ObservableList<ShelfManage> getAllProduct() throws SQLException, ClassNotFoundException{
       ObservableList<ShelfManage> pdList=FXCollections.observableArrayList();
     DBconnection dbc =new DBconnection();
       dbc.connectToDB();
        String query="SELECT Products.ProductID, Products.ProductName,Shelf.Block_shelfNo,Shelf.Shelf_row,Shelf.Shelf_col,Products.Expire,Products.MFG "
                + "FROM Products LEFT JOIN Shelf ON Products.ProductID=Shelf.ProductID where DATEDIFF(Day, GETDATE(),Products.Expire )<=7"; 
       
         dbc.queryToDB(query);
        ResultSet rs= dbc.queryToDB(query);
        
        while(rs.next()){
        String ProductID=rs.getString("ProductID");
        String ProductName=rs.getString("ProductName");
        String Block_no=rs.getString("Block_shelfNo");
        String Block_Shelfno=rs.getString("Block_ShelfNo");
        int row_no=rs.getInt("Shelf_row");
        int col_no=rs.getInt("Shelf_col");
        Date MFG=rs.getDate("MFG");
        Date Expire=rs.getDate("Expire");
        
      LocalDate ld_MFG=LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(MFG) );
      LocalDate ld_Expire=LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(Expire) );
        ShelfManage pd =new ShelfManage(ProductID,ProductName,Block_no,Block_Shelfno,row_no,col_no,ld_MFG,ld_Expire);
     
        pdList.add(pd);
         
        }
        
       return pdList; 
    

    
    } 
    @FXML
    private void delete(ActionEvent event) throws SQLException, ClassNotFoundException {
   ObservableList<ShelfManage> selectedEmployee=FXCollections.observableArrayList();
        selectedEmployee=ProductTable.getSelectionModel().getSelectedItems();
      
          deleteExpProduct(selectedEmployee);
        Expire_productMngList.removeAll(selectedEmployee);
    
    }
      public  void deleteExpProduct(ObservableList<ShelfManage> selectedProduct) throws SQLException, ClassNotFoundException {

      
         DBconnection dbc =new DBconnection();
          dbc.connectToDB();
       
        for (ShelfManage pd : selectedProduct) {
            String query = "DELETE FROM Shelf WHERE ProductID='"+pd.Product_Id+"'";
            dbc.DeleteDataToDB(query);
            String query1 = "DELETE  FROM Products  WHERE ProductID='"+pd.Product_Id+"'";
            dbc.queryToDB(query1);
            System.out.println(query1);
        
        }
            
 
            
    }

}
