/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grab.go;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Mukaffi
 */
public class GrabGo extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene =new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }
    
}
