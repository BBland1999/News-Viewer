/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Ben
 */
public abstract class AbstractNewsManager implements GetNumNewsStoriesInterface{
    private String urlString = "";
       final String baseUrlString = "https://newsapi.org/v2/top-headlines?sources=";
       final String apiKey = "b86ca687a7d24e4abd9a5ad22e1ad56c";
     
       URL url;
       Prefrences pref = new Prefrences();
       
       public void load() throws Exception {
        
          
            urlString = baseUrlString;

            for(String source : pref.prefrences){
                urlString = urlString + source + ",";
            }

            urlString = urlString + "&apiKey=" + apiKey;

            try {
                url = new URL(urlString); 
            } catch(MalformedURLException muex){
                throw muex; 
            }

            String jsonString = ""; 

            try {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream())); 

                String inputLine; 

                while((inputLine = in.readLine()) != null){
                    jsonString += inputLine; 
                }

                in.close();

            } catch(IOException ioex){
                throw ioex; 
            }

            try {
                parseJsonNewsFeed(jsonString); 
            } catch (Exception ex){
                throw ex; 
            }
        }
       
    abstract void parseJsonNewsFeed(String jsonString) throws Exception;
}
