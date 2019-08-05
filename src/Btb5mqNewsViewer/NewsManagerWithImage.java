/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.Vector;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Ben
 */
public class NewsManagerWithImage extends AbstractNewsManager{
       
    public List<GoogleNewsStoryWithImage> newsStories;

    public NewsManagerWithImage() { 
        newsStories = new ArrayList<GoogleNewsStoryWithImage>();       
    }
       
       
    @Override   
    void parseJsonNewsFeed(String jsonString) throws Exception {
        
        newsStories.clear();
        
        if (jsonString == null || jsonString == "") return;
        
        JSONObject jsonObj;
        try {
            jsonObj = (JSONObject)JSONValue.parse(jsonString);
        } catch (Exception ex) {
            throw ex;
        }
        
        if (jsonObj == null) return;
        
        String status = "";
        try {
            status = (String)jsonObj.get("status");
        } catch (Exception ex) {
            throw ex;
        }
        
        if (status == null || !status.equals("ok")) {
            throw new Exception("Status returned from API was not OK.");
        }
        
        JSONArray articles;
        try {
            articles = (JSONArray)jsonObj.get("articles");
        } catch (Exception ex) {
            throw ex;
        }
        
        
        
        for (Object article : articles) {
            try {
                JSONObject story = (JSONObject)article;
                String author = (String)story.getOrDefault("author", "Not Specified");
                String title = (String)story.getOrDefault("title", "");
                String description = (String)story.getOrDefault("description", "");
                String url = (String)story.getOrDefault("url", "");
                String urlToImage = (String)story.getOrDefault("urlToImage", "");
                String publishedAt = (String)story.getOrDefault("publishedAt", "");
                String content = (String)story.getOrDefault("content", "");
                
                
                GoogleNewsStoryWithImage newsStory = new GoogleNewsStoryWithImage(author, title, description, url, publishedAt, content, urlToImage);
//                BufferedImage image = null;
//                try {
//                    URL imgUrl = new URL(urlToImage);
//                    image = ImageIO.read(imgUrl);
//                    
//                    Image myImage = SwingFXUtils.toFXImage(image, null);
//                    GoogleNewsStoryWithImage newsStory = new GoogleNewsStoryWithImage(author, title, description, url, publishedAt, content, myImage);
//                    newsStories.add(newsStory);
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
                
                newsStories.add(newsStory);
                                
            } catch (Exception ex) {
                throw ex;
            }
            
        }
        int size = newsStories.size();
        
        List<GoogleNewsStoryWithImage> head = newsStories.subList(0, size/2);
        List<GoogleNewsStoryWithImage> tail = newsStories.subList(size/2, size);
        
        ThreadClass tc1 = new ThreadClass(head);
        ThreadClass tc2 = new ThreadClass(tail);
        
        Thread t1 = new Thread(tc1);
        Thread t2 = new Thread(tc2);

        t1.start();
        t2.start();
        
        //Wait for threads to finish
        while(true){
           try{
               t1.join();
               t2.join();
               break;
           }catch(Exception e){
                System.err.println("Error While multithreading");
           }   
        }
        
        ThreadClassHelper newClass = new ThreadClassHelper();
        newsStories = newClass.getList();
    }
    
    public List<GoogleNewsStoryWithImage> getNewsStories() {
        Collections.shuffle(newsStories);
        return newsStories;
    }
    
    public int getNumNewsStories() {        
        return newsStories.size();
    }
    
    
}

