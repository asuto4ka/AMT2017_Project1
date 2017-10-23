/*-----------------------------------------------------------------------------------
 File        : BooksManagementLocal.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : interface which is gonna be implemented by BooksManager
 -----------------------------------------------------------------------------------*/ 
package com.mycompany.mvc.services.dao;

import com.mycompany.mvc.model.Book;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BooksManagerLocal {
    
    void newBook(String title, String summary, String author, int releaseDate, int nbPages);
    void deleteBook(Long idToDel);
    void updateBook(Long id, String title,String summary,String author,int releaseDate,int nbPages);
    Book findBook (long id);
    void generateData(Long nbData);
    Long nbBooks();
    List<Book> findBooks(int offset, int nbEntryMax);
    
}
