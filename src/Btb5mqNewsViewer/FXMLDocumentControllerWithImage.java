/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Ben
 */
public class FXMLDocumentControllerWithImage extends AbstractFXMLDocumentController{
    
    private NewsManagerWithImage newsManager;
    List<GoogleNewsStoryWithImage> stories;
    
    @Override
    public void ready(Stage stage){
        this.stage = stage;
        webEngine = webView.getEngine();
        newsManager = new NewsManagerWithImage();
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

    void loadNews(NewsManagerWithImage newsManager) 
        {
        try {
            newsManager.load(); 
        } catch(Exception ex){
            displayExceptionAlert(ex); 
            return; 
        }
       
        stories = newsManager.getNewsStories(); 
        newsListItems.clear(); 
        itemsWithImages.clear();
        for(GoogleNewsStoryWithImage story : stories){
            
            Label label1 = new Label(story.title);
            //Image image = new Image(getClass().getResourceAsStream("labels.jpg"));
            ImageView img = new ImageView(story.myImage);
            img.setFitHeight(50);
            img.setPreserveRatio(true);
            label1.setGraphic(img);
            //ImageView imageView = new ImageView(story.urlToImage);
            //Text text = new Text(story.title);
            itemsWithImages.add(label1);
            //newsListItems.add(hbox);
        }
        
        newsListView.setItems(itemsWithImages);
        
        if(stories.size() > 0){
            newsListView.getSelectionModel().select(0);
            newsListView.getFocusModel().focus(0); 
            newsListView.scrollTo(0); 
        }
    }        
     
    
}
