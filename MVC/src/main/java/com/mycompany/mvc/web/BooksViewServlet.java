/*
 -----------------------------------------------------------------------------------
 File        : BookViewServlet.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : Servlet who is responsible for display books
 ----------------------------------------------------------------------------------- 
 */
package com.mycompany.mvc.web;

import com.mycompany.mvc.services.dao.BooksManagerLocal;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

public class BooksViewServlet extends HttpServlet {

   @EJB
   private BooksManagerLocal booksManager;

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      //first page is under number 1 not 0
      int pageNum = 1;
      // maximum book per page
      int maxEntries = 10;

      //if the page number is not null we van have a value of a page
      if (request.getParameter("page") != null) {
          try {
              pageNum = Integer.parseInt(request.getParameter("page"));
          }catch(NumberFormatException e) {
              
          }
      }

      // calculate number of pages
      Long nbBooks = booksManager.nbBooks();
      int nbPages = (int) Math.ceil((double) nbBooks / maxEntries);

      // if user decided to visit a page which does not exist, he is gonne either go
      // on the last page either the first one
      if (pageNum > nbPages) {
         pageNum = nbPages;
      } else if (pageNum < 1) {
         pageNum = 1;
      }

      int offset = maxEntries * (pageNum - 1);

      // assign values 
      request.setAttribute("nbPages", nbPages);
      request.setAttribute("currentPage", pageNum);
      request.setAttribute("books", booksManager.findBooks(offset, maxEntries));
      request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
   }

   /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo() {
      return "Short description";
   }// </editor-fold>

}
