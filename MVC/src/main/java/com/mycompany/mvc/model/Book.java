/*
 -----------------------------------------------------------------------------------
 File        : Book.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : book class describing book structure
 ----------------------------------------------------------------------------------- 
 */
package com.mycompany.mvc.model;


public class Book {

    private final long id;
    private final String title;
    private final String summary;
    private final String author;
    private final int releaseDate;
    private final int nbPages;
    
    //constructor
    public Book( long id, String title, String summary, String author, int releaseDate, int nbPages){
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.releaseDate = releaseDate;
        this.nbPages = nbPages;
    }

    //getters
    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public int getNbPages() {
        return nbPages;
    }
    
}
