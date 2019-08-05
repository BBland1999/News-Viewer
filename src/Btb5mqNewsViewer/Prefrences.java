/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ben
 */
public class Prefrences {
    static ArrayList<String> prefrences = new ArrayList<String>();
    public static boolean includeImages;
    public Prefrences(){
        loadPrefrences();
    }
    public void loadPrefrences(){
        prefrences.clear();
        JSONParser parser = new JSONParser();
        //Reader reader = new FileReader("/prefrences.json");
        Reader reader;
        try{
            reader = new FileReader("prefrences.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            reader.close();

            includeImages = (boolean) jsonObject.get("include_images");

            JSONArray sources = (JSONArray)jsonObject.get("sources");
            
            for(Object source : sources){
                prefrences.add(source.toString());
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        } 

    }
    public void setPrefrence(String prefrenceString){
        if(prefrences.contains(prefrenceString)){
            System.out.println(prefrenceString);
            prefrences.remove(prefrenceString);
        }
        else{
            System.out.println(prefrenceString);
            prefrences.add(prefrenceString);        
        }
            
        JSONObject pref = new JSONObject();
        JSONArray sources = new JSONArray();
        for(String prefrence : prefrences){
            sources.add(prefrence);
        }
        
        pref.put("include_images", this.includeImages);
        pref.put("sources", sources);
        
        try (FileWriter file = new FileWriter("prefrences.json", false)) {
            System.out.println(pref.toJSONString());
            file.write(pref.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    
    public void flipIncludeImages(){
        if(includeImages){
            includeImages = false;
        }
        else{
            includeImages = true;
        }
        
        JSONObject pref = new JSONObject();
        JSONArray sources = new JSONArray();
        for(String prefrence : prefrences){
            sources.add(prefrence);
        }
        
        pref.put("include_images", this.includeImages);
        pref.put("sources", sources);
        
        try (FileWriter file = new FileWriter("prefrences.json", false)) {
            file.write(pref.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return;
    }
    
    public boolean ifImages(){
        return includeImages;
    }
    public ArrayList<String> getSources(){
        return prefrences;
    }
}
