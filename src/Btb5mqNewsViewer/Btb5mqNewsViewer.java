/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ben
 */
public class Btb5mqNewsViewer extends Application {
    protected static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        Prefrences pref = new Prefrences();
        
        mainStage = new Stage();
        mainStage = stage;
        
        
        if(pref.ifImages()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLDocumentControllerWithImage controller = new FXMLDocumentControllerWithImage();
            loader.setController(controller);

            Parent root = (Parent)loader.load();


            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.show();

            controller.ready(mainStage);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLDocumentController controller = new FXMLDocumentController();
            loader.setController(controller);

            Parent root = (Parent)loader.load();


            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.show();

            controller.ready(mainStage);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
