/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Ben
 */
public class ThreadClass implements Runnable{
    public List<GoogleNewsStoryWithImage> newsStories; 
    Vector<GoogleNewsStoryWithImage> vct = new Vector<GoogleNewsStoryWithImage>();
    
    public ThreadClass(List<GoogleNewsStoryWithImage> newsStories) {
        this.newsStories = newsStories;
    }
    
    public void run() {
        Thread t = Thread.currentThread();
        
        vct.addAll(newsStories);
        ThreadClassHelper newClass = new ThreadClassHelper();
        
        for(GoogleNewsStoryWithImage story : newsStories){
            BufferedImage image = null;
            try {
                URL imgUrl = new URL(story.urlToImage);
                image = ImageIO.read(imgUrl);

                Image myImage = SwingFXUtils.toFXImage(image, null);
                GoogleNewsStoryWithImage newsStory = new GoogleNewsStoryWithImage(story.author, story.title, story.description, story.url, story.publishedAt, story.content, myImage);
                newClass.addToList(newsStory);
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
        
        
        
        
    }
    
}
