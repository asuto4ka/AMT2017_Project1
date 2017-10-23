/*-----------------------------------------------------------------------------------
 File        : BooksManager.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : class containing all methods which are needed
 ----------------------------------------------------------------------------------- 
 */
package com.mycompany.mvc.services.dao;

import com.mycompany.mvc.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class BooksManager implements BooksManagerLocal {

   @Resource(lookup = "java:/jdbc/amtproject")
   private DataSource dataSrc;

   @Override
   public List<Book> findBooks(int offset, int nbEntryMax) {
      List<Book> books = new ArrayList<>();

      // we have to test a connection and send an error message if needed
      try (Connection connection = dataSrc.getConnection()) {
         PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books LIMIT ?,?;");
         pstmt.setInt(1, offset);
         pstmt.setInt(2, nbEntryMax);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            long id = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String description = rs.getString("description");
            int releaseDate = rs.getInt("release_year");
            int nbPages = rs.getInt("nbPages");
            books.add(new Book(id, title, description, author, releaseDate, nbPages));
         }
      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }
      return books;
   }

   // create a new book, with all values typed by user
   @Override
   public void newBook(String title, String summary, String author, int releaseDate, int nbPages) {

      try {
         Connection connection = dataSrc.getConnection();

         PreparedStatement pstmt = connection.prepareStatement("INSERT INTO books"
                 + " (title, description, author, release_year, nbPages) VALUES"
                 + " (?,?,?,?,?);");
         System.out.println(releaseDate);
         pstmt.setString(1, title);
         pstmt.setString(2, summary);
         pstmt.setString(3, author);
         pstmt.setInt(4, releaseDate);
         pstmt.setInt(5, nbPages);

         pstmt.executeUpdate();

      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }

   }

   // delete a book
   @Override
   public void deleteBook(Long idToDel) {

      //check a connection ans delete a book
      try {
         Connection connection = dataSrc.getConnection();
         String delete = "DELETE FROM books WHERE book_id = ?";
         PreparedStatement pstmt = connection.prepareStatement(delete);
         pstmt.setLong(1, idToDel);

         pstmt.executeUpdate();
      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }
   }

   @Override
   public Book findBook(long id) {
      Book b = null;
      
         try (Connection connection = dataSrc.getConnection()) {
            String find = "SELECT * FROM books WHERE book_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(find);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               long idBook = rs.getInt("book_id");
               String title = rs.getString("title");
               String author = rs.getString("author");
               String description = rs.getString("description");
               int releaseDate = rs.getInt("release_year");
               int nbPages = rs.getInt("nbPages");
               b = new Book(idBook, title, description, author, releaseDate, nbPages);
            }
      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }
      return b;
   }

   // update a book
   @Override
   public void updateBook(Long id, String title, String summary, String author,
           int releaseDate, int nbPages) {

      //verify a connection, execute sql statement and replace old values by new ones
      try {
         Connection connection = dataSrc.getConnection();
         String update = "UPDATE books SET title = ?, description = ?,  author = ?, release_year = ?, nbPages = ? "
                 + "WHERE book_id = ?";

         PreparedStatement pstmt = connection.prepareStatement(update);

         pstmt.setString(1, title);
         pstmt.setString(2, summary);
         pstmt.setString(3, author);
         pstmt.setInt(4, releaseDate);
         pstmt.setInt(5, nbPages);
         pstmt.setLong(6, id);
         pstmt.executeUpdate();

      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }
   }

   // generate many books
   @Override
   public void generateData(Long nbBook) {

      // verify connection, insert new books with fixed values 
      try (Connection connection = dataSrc.getConnection()) {
         for (long i = 0; i < nbBook; i++) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO books"
                    + " (title, description,author, release_year, nbPages) VALUES"
                    + " (?,?,?,?,?);");

            pstmt.setString(1, "unknow" + i);
            pstmt.setString(2, "unknow" + i);
            pstmt.setString(3, "unknow" + i);
            pstmt.setInt(4, 1990);
            pstmt.setInt(5, 1500);

            pstmt.executeUpdate();
         }
      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }

   }

     // count number of books
   @Override
   public Long nbBooks() {
      Long numberOfRows = new Long(0);
      
      //verify connection, execute statement and return the number of entry
         try (Connection connection = dataSrc.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM books;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               numberOfRows = rs.getLong(1);
         }
      } catch (SQLException e) {
         Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
      }
      return numberOfRows;
   }

}
