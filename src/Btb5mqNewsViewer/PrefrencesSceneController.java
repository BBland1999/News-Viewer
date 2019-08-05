/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ben
 */
public class PrefrencesSceneController implements Initializable {

    Prefrences pref = new Prefrences();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(pref.prefrences.contains("msnbc")){  
            msnbcCheckBox.setSelected(true);
        }
        if(pref.prefrences.contains("cbs-news")){
            cbsNewsCheckBox.setSelected(true);
        }
        if(pref.prefrences.contains("cnbc")){
            cnbcCheckBox.setSelected(true);
        }
        if(pref.prefrences.contains("reuters")){
            reutersCheckBox.setSelected(true);
        }
        if(pref.prefrences.contains("fox-news")){
            foxnewsCheckBox.setSelected(true);
        }
        if(pref.includeImages){
            showCheckBox.setSelected(true);
        }
    }
    
    
    @FXML
    CheckBox msnbcCheckBox;
    
    @FXML
    CheckBox cbsNewsCheckBox;
    
    @FXML
    CheckBox cnbcCheckBox;
    
    @FXML
    CheckBox reutersCheckBox;
    
    @FXML
    CheckBox foxnewsCheckBox;
    
    @FXML
    RadioButton showCheckBox;
    
    @FXML
    private void msnbcCheckBoxAction(){
        pref.setPrefrence("msnbc");
        update();
    }
    
    @FXML
    private void cbsNewsCheckBoxAction(){
        pref.setPrefrence("cbs-news");
        update();
    }
    
    @FXML
    private void cnbcCheckBoxAction(){
        pref.setPrefrence("cnbc");
        update();
    }
    
    @FXML
    private void reutersCheckBoxAction(){
        pref.setPrefrence("reuters");
        update();
    }
    
    @FXML
    private void foxnewsCheckBoxAction(){
        pref.setPrefrence("fox-news");
        update();
    }
    
    @FXML 
    private void showCheckBoxAction(ActionEvent e) throws  Exception{
        pref.flipIncludeImages();
        
        if(!showCheckBox.isSelected()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLDocumentController controller = new FXMLDocumentController();
            loader.setController(controller);
            Parent root = (Parent)loader.load();
        
            Scene scene = new Scene(root);
            Stage appStage = Btb5mqNewsViewer.mainStage;
            appStage.setScene(scene);
            appStage.show();
            controller.ready(Btb5mqNewsViewer.mainStage);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLDocumentControllerWithImage controller = new FXMLDocumentControllerWithImage();
            loader.setController(controller);
            Parent root = (Parent)loader.load();
        
            Scene scene = new Scene(root);
            Stage appStage = Btb5mqNewsViewer.mainStage;
            appStage.setScene(scene);
            appStage.show();
            controller.ready(Btb5mqNewsViewer.mainStage);
        }
        
    }
    
    private void update(){
        if(pref.ifImages()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLDocumentControllerWithImage controller = new FXMLDocumentControllerWithImage();
            loader.setController(controller);
            Parent root;
            try {
                root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage appStage = Btb5mqNewsViewer.mainStage;
                appStage.setScene(scene);
                appStage.show();
                controller.ready(Btb5mqNewsViewer.mainStage);
            } catch (IOException ex) {
                Logger.getLogger(PrefrencesSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLDocumentController controller = new FXMLDocumentController();
            loader.setController(controller);
            Parent root;
            try {
                root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage appStage = Btb5mqNewsViewer.mainStage;
                appStage.setScene(scene);
                appStage.show();
                controller.ready(Btb5mqNewsViewer.mainStage);
            } catch (IOException ex) {
                Logger.getLogger(PrefrencesSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }
    
}