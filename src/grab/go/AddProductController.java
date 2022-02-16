/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import DatabaseConnection.DBconnection;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddProductController implements Initializable {

    @FXML
    private JFXTextField Pro_id;
    @FXML
    private JFXTextField Pro_name;
    @FXML
    private JFXComboBox<String> Shelf_No;
          ObservableList<String> ShelfAll = FXCollections.observableArrayList(
                  "Block A","Block B","Block C","Block D","Block E"
        );
    private JFXDatePicker MFG;
    @FXML
    private JFXDatePicker Expire;
    @FXML
    private JFXTextField Price;
    @FXML
    private JFXCheckBox Ava_Stock;
    @FXML
    private Button Save_pro;
    @FXML
    private Button Reset_Pro;
    @FXML
    private JFXDatePicker mfg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Shelf_No.setItems(ShelfAll);
        
         
        // TODO
    }    

    @FXML
    private void Save_pro(ActionEvent event) throws ClassNotFoundException, SQLException, WriterException, IOException {
        
       String productId= Pro_id.getText() ;
         String productName= Pro_name.getText() ; 
           String shelf=Shelf_No.getValue();
             LocalDate mfg1 =  mfg.getValue();
            LocalDate exp = Expire.getValue();
           String productPrice= Price.getText() ;
           String stock;
            if(Ava_Stock.isSelected()){
             stock= "Available";
            } else {
                stock= "Not Available";
                }
          
            System.out.println(stock);
            
     
       product pd=new product( productId,productName,shelf, mfg1,exp,productPrice,stock );
        System.out.println(pd);
        insertProduct(pd);
        QrCodegenarates(pd);
        
    }
    
    private void insertProduct(product pd) throws ClassNotFoundException, SQLException {
      
        DBconnection dbc = new DBconnection();
        dbc.connectToDB();
        String query = "insert into Products(ProductID,ProductName,ShelfNo,MFG,Expire,UnitPrice,Stock ) values('" + pd.Product_Id + "','" + pd.Product_Name+ "','" + pd.Shelf_no + "','" + pd.MFG_Date + "','" + pd.Exp_Date + "','" + pd.Unit_Price+ "','" + pd.Available_Stock + "')";
        System.out.println(query);
        boolean dataInserted = dbc.insertDataToDB(query);
        if(dataInserted)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Product Added");
            alert.showAndWait();
        }
    }
    
    
    public void  QrCodegenarates(product pd) throws WriterException, UnsupportedEncodingException, IOException{
        String qrCodeData = pd.Product_Id;
            String filePath = "Qr codes\\"+pd.Product_Id+"and "+pd.Product_Name+".png";
            String charset = "UTF-8"; // 
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");

}
    @FXML
    private void Reset_Pro(ActionEvent event) {
    }
    
}

/* ProductID int ,
	ProductName  varchar(50),
	ProductName  varchar(50),
	MFG Date,
	Expire DATE,
	UnitPrice float(24),
	Stock varchar(100)*/