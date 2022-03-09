/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import DatabaseConnection.DBconnection;
import com.jfoenix.controls.JFXButton;
import static grab.go.Shelf_MngController.Shelf_productMngList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FUJITSU
 */
public class Shelf_FillController implements Initializable {

    @FXML
    private TableView<ShelfManage> Shelf_mngTable;
    @FXML
    private TableColumn<ShelfManage, String> Shelf_productID;
    @FXML
    private TableColumn<ShelfManage, String> Shelf_productName;
    @FXML
    private TableColumn<ShelfManage, String> Shelf_blockNum;
    @FXML
    private TableColumn<ShelfManage, String> Shelf_shelfNum;
    @FXML
    private TableColumn<ShelfManage, Integer> Shelf_row;
    @FXML
    private TableColumn<ShelfManage, Integer> Shelf_col;
    @FXML
    private TableColumn<ShelfManage, String> Shelf_addSts;
    @FXML
    private TableColumn<ShelfManage, Integer> Shelf_addQty;

    static ObservableList<ShelfManage> Shelf_productMngList = FXCollections.observableArrayList();
    @FXML
    private JFXButton update_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Shelf_mngTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        try {
            Shelf_productMngList = getAllShelfProduct();
        } catch (SQLException ex) {
            Logger.getLogger(ShowEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Shelf_productID.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Product_Id"));
        Shelf_productName.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Product_Name"));
        Shelf_blockNum.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Block_no"));
        Shelf_shelfNum.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Shelf_no"));
        Shelf_row.setCellValueFactory(new PropertyValueFactory<ShelfManage, Integer>("Shelf_row"));
        Shelf_col.setCellValueFactory(new PropertyValueFactory<ShelfManage, Integer>("Shelf_col"));
        Shelf_addQty.setCellValueFactory(new PropertyValueFactory<ShelfManage, Integer>("addProQty"));
        Shelf_addSts.setCellValueFactory(new PropertyValueFactory<ShelfManage, String>("Add_sts"));

        Shelf_mngTable.setItems(Shelf_productMngList);
    }


    public ObservableList<ShelfManage> getAllShelfProduct() throws SQLException, ClassNotFoundException {
        ObservableList<ShelfManage> ShelfProductList = FXCollections.observableArrayList();
        DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        String query = "SELECT  Products.ProductID,ProductName,ShelfNo,Block_shelfNo,Shelf_row,Shelf_col,Add_status FROM Products INNER JOIN Shelf on Products.ProductID=Shelf.ProductID AND Add_status NOT LIKE '%No%'";
        dbc.queryToDB(query);
        ResultSet rs = dbc.queryToDB(query);

        while (rs.next()) {
            String ProductID = rs.getString("ProductID");
            String ProductName = rs.getString("ProductName");
            String Block_no = rs.getString("ShelfNo");
            String Block_Shelfno = rs.getString("Block_ShelfNo");
            int row_no = rs.getInt("Shelf_row");
            int col_no = rs.getInt("Shelf_col");
            String Add_sts = rs.getString("Add_status");
            String clean = Add_sts.replaceAll("\\D+", "");
            int addProQty;
            if (clean.equals("")) {
                addProQty = 0;
            } else {
                addProQty = Integer.parseInt(clean);
            }
            ShelfManage sm = new ShelfManage(ProductID, ProductName, Block_no, Block_Shelfno, row_no, col_no, addProQty, Add_sts);
            ShelfProductList.add(sm);
        }
        return ShelfProductList;
    }

    @FXML
    private void updateStatusOnAction(MouseEvent event) throws ClassNotFoundException, SQLException {

        ObservableList<ShelfManage> selectedProduct = FXCollections.observableArrayList();
        selectedProduct = Shelf_mngTable.getSelectionModel().getSelectedItems();

        updateStatus(selectedProduct);
        Shelf_productMngList.removeAll(selectedProduct);
    }

    private void updateStatus(ObservableList<ShelfManage> selectedProduct) throws ClassNotFoundException, SQLException {

        //ObservableList<ShelfManage> ShelfProductList=FXCollections.observableArrayList();
        DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        for (ShelfManage pd : selectedProduct) {
            String query = "SELECT OnShelf_qty,Add_status from Shelf where ProductID='" + pd.Product_Id + "'";
            dbc.queryToDB(query);
            ResultSet rs = dbc.queryToDB(query);
            while (rs.next()) {
                String onShelfQty = rs.getString("OnShelf_qty");
                String Add_sts = rs.getString("Add_status");
                String clean = Add_sts.replaceAll("\\D+", "");
                int addProQty;
                if (clean.equals("")) {
                    addProQty = 0;
                } else {
                    addProQty = Integer.parseInt(clean);
                }
                int s_quan=Integer.parseInt(onShelfQty);
                addProQty+=s_quan;
                String q = "UPDATE Shelf SET OnShelf_qty="+addProQty+", Add_status='No Pending' where ProductID='"+pd.Product_Id+"'";
                dbc.queryToDB(q);  
            }
         
        }

       
    }

}
