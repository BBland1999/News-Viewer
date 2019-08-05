/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Ben
 */
public class ThreadClassHelper {
    static Vector<GoogleNewsStoryWithImage> vct = new Vector<GoogleNewsStoryWithImage>();
    
    public void addToList(GoogleNewsStoryWithImage story){
        vct.add(story);
    }
   
   public List<GoogleNewsStoryWithImage> getList(){
       List<GoogleNewsStoryWithImage> list = new ArrayList<GoogleNewsStoryWithImage>(); 
       list.addAll(vct);
       return list;
   }
}
