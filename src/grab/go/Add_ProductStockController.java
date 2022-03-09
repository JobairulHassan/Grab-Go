/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import DatabaseConnection.DBconnection;
import Exceptions.NullValueException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static grab.go.AddEmployeeController.isNumeric;
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
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Add_ProductStockController implements Initializable {

    @FXML
    private JFXTextField Stock_Product;
    @FXML
    private JFXTextField Product_Quantity;
    @FXML
    private JFXTextField Upcoming_Quantity;
    @FXML
    private JFXButton Save_ProductStock;
    @FXML
    private JFXButton ResetProductStock;
    @FXML
    private JFXTextField ProductStock_Id;
    @FXML
    private TableView<product> StockProductTable;
    @FXML
    private TableColumn<product, String> SproductID;
    @FXML
    private TableColumn<product, String> StockProName;
    
    static ObservableList<product> productList=FXCollections.observableArrayList();

    /*
     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        StockProductTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
        try {
            productList=getAllProduct();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Add_ProductStockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Add_ProductStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         SproductID.setCellValueFactory(new PropertyValueFactory<product, String>("Product_Id")); 
       StockProName.setCellValueFactory(new PropertyValueFactory<product, String>("Product_Name"));
        
          StockProductTable.setItems(productList);
        
     
    } 

   
    private boolean validateFields() {
        try {
            if (Stock_Product.getText().isEmpty() || Product_Quantity.getText().isEmpty() || Upcoming_Quantity.getText().isEmpty() || ProductStock_Id.getText().isEmpty()
                    ) {

                //Alert Message
                throw new NullValueException("Information Missing!!!", "Please provide all the informations properly.");
            }
        } catch (NullValueException e) {
            return false;
        }
        return true;
    }

    
    
    
    @FXML
    private void Save_ProductStock(ActionEvent event) throws ClassNotFoundException, SQLException {
         if (validateFields())
         {
         String StockID=ProductStock_Id.getText();
         String StockProduct=  Stock_Product.getText();
         String  ProductQuantity=  Product_Quantity.getText() ;  
         String UpcomingQuantity= Upcoming_Quantity.getText() ;
           
     
       Stock st=new Stock(StockID,StockProduct, ProductQuantity,UpcomingQuantity );
        System.out.println(st);
        insertProduct(st);
         }  
         
    }

    @FXML
    private void ResetProductStock(ActionEvent event) {
    }

    private void insertProduct(Stock st) throws ClassNotFoundException, SQLException {
        
        
        
        
        DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        String query = "insert into Stock(Product_Id,Product_name,Product_Quantity,Product_Upcoming) values('" + st.ProductID + "','" + st.ProductName + "','" + st.ProductQuantity+ "','" + st.ProductUpcomingQuantity+ "')";
        System.out.println(query);
        boolean dataInserted = dbc.insertDataToDB(query);
        if(dataInserted)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Stock Information  Added");
            alert.showAndWait();
        }
        
         
    }

    
    public  ObservableList<product> getAllProduct() throws SQLException, ClassNotFoundException{
       ObservableList<product> pdList=FXCollections.observableArrayList();
     DBconnection dbc =new DBconnection();
       dbc.connectToDB();
        String query="select * from Products"; 
         dbc.queryToDB(query);
        ResultSet rs= dbc.queryToDB(query);
        
        while(rs.next()){
        String ProductID=rs.getString("ProductID");
        String ProductName=rs.getString("ProductName");
        String ShelfNo=rs.getString("ShelfNo");
        String UnitPrice=rs.getString("UnitPrice");
        String Stock=rs.getString("Stock");
        Date MFG=rs.getDate("MFG");
        Date Expire=rs.getDate("Expire");
        
      LocalDate ld_MFG=LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(MFG) );
      LocalDate ld_Expire=LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(Expire) );
        
        product pd =new product(ProductID,ProductName,ShelfNo,ld_MFG,ld_Expire,UnitPrice,Stock);
        pdList.add(pd);
         
        }
        
       return pdList; 
    
  
    
    } 

    @FXML
    private void Add_OnAction(ActionEvent event) {
        
        product pd= new product();
        
        pd= StockProductTable.getSelectionModel().getSelectedItem();
        
        Stock_Product.setText(pd.Product_Name);
         
       ProductStock_Id.setText(pd.Product_Id);
                
    }

  
    
    
    
    
    
    
    
    

    }
    

/*



    @FXML
    private void Save_ProductStock(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        
         String  StockProduct=  Stock_Product.getText() ;
         String  ProductQuantity=  Product_Quantity.getText() ;  
         String UpcomingQuantity= Upcoming_Quantity.getText() ;
           
     
       Stock st=new Stock( StockProduct, ProductQuantity,UpcomingQuantity );
        System.out.println(st);
        insertProduct(st);
       
    }

    @FXML
    private void ResetProductStock(ActionEvent event) {
    }

    private void insertProduct(Stock st) throws ClassNotFoundException, SQLException {
         DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        String query = "insert into Stock(Product_name,Product_Quantity,Product_Upcoming) values('" + st.ProductName + "','" + st.ProductQuantity+ "','" + st.ProductUpcomingQuantity+ "')";
        System.out.println(query);
        boolean dataInserted = dbc.insertDataToDB(query);
        if(dataInserted)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Stock Information  Added");
            alert.showAndWait();
        }
    }

  


*/