/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import javafx.scene.image.Image;

/**
 *
 * @author Ben
 */
public class GoogleNewsStoryWithImage extends GoogleNewsStory{
    public Image myImage;
    public String urlToImage;
    
    public GoogleNewsStoryWithImage(String author, String title, String description, String url, String publishedAt, String content, Image myImage){
          super(author, title, description, url, publishedAt, content);
          this.myImage = myImage;

    }
    public GoogleNewsStoryWithImage(String author, String title, String description, String url, String publishedAt, String content, String urlToImage){
          super(author, title, description, url, publishedAt, content);
          this.urlToImage = urlToImage;
    }
    
    
    
    
    
}
