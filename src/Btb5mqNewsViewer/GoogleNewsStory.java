/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Btb5mqNewsViewer;

/**
 *
 * @author Ben
 */
public class GoogleNewsStory {
    
    public String author;
    public String title;
    public String description;
    public String url;
    public String publishedAt;
    public String content;
    
    public GoogleNewsStory(String author, String title, String description, String url, String publishedAt, String content){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
        this.content = content;
    }
}
