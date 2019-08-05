/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Ben
 */
public class NewsManager extends AbstractNewsManager{
       
       private ArrayList<GoogleNewsStory> newsStories;
       
       public NewsManager() {
            newsStories = new ArrayList<>();       
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
                String publishedAt = (String)story.getOrDefault("publishedAt", "");
                String content = (String)story.getOrDefault("content", "");
                             
                GoogleNewsStory newsStory = new GoogleNewsStory(author, title, description, url/*, urlToImage*/, publishedAt, content);
                newsStories.add(newsStory);
                                
            } catch (Exception ex) {
                throw ex;
            }
            
        }
        Collections.shuffle(newsStories);
    }
    
    public ArrayList<GoogleNewsStory> getNewsStories() {
        return newsStories;
    }
    
    public int getNumNewsStories() {        
        return newsStories.size();
    }
    
    
}
