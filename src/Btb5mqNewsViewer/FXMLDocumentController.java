/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
/**
 *
 * @author Ben
 */
public class FXMLDocumentController extends AbstractFXMLDocumentController{
    
    private NewsManager newsManager;
    ArrayList<GoogleNewsStory> stories;
    
    @Override
    public void ready(Stage stage) {
        this.stage = stage;
        webEngine = webView.getEngine();
        newsManager = new NewsManager();
        newsListItems = FXCollections.observableArrayList();
        itemsWithImages = FXCollections.observableArrayList();
        
        newsListView.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

                    if((int) new_val < 0 || (int) new_val > (stories.size() - 1)){
                        return; 
                    }
                    
                    GoogleNewsStory story = stories.get((int) new_val); 
                    webEngine.load(story.url);
                    
                }
        ); 
        
        loadNews(newsManager);
    }
    
    @FXML
    void handleUpdate() {
        loadNews(newsManager);
    }
    
    void loadNews(NewsManager newsManager) {
        
        try {
            newsManager.load(); 
        } catch(Exception ex){
            displayExceptionAlert(ex); 
            return; 
        }
        
        stories = newsManager.getNewsStories(); 
        newsListItems.clear(); 
        itemsWithImages.clear();
        for(GoogleNewsStory story : stories){
            Label label1 = new Label(story.title);           
            itemsWithImages.add(label1);
        }
        
        newsListView.setItems(itemsWithImages);
        
        if(stories.size() > 0){
            newsListView.getSelectionModel().select(0);
            newsListView.getFocusModel().focus(0); 
            newsListView.scrollTo(0); 
        }
    }
    
   
}
