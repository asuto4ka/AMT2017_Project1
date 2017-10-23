/*
-----------------------------------------------------------------------------------
 File        : DeleteBookServlet.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : Servlet who is responsible for deleting an entry (a specific book)
 -----------------------------------------------------------------------------------
 */
package com.mycompany.mvc.web;

import com.mycompany.mvc.services.dao.BooksManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteBookServlet extends HttpServlet {

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

      // if user tries to insert into the url a wrong request without provideng an id
      // it will redirect to the /books page
      if (request.getParameter("id") == null) {
         response.sendRedirect(response.encodeRedirectURL("http://localhost:9090/MVC-MVC/books"));
      } else {
         // if it's possible to delete a book
         Long id = Long.valueOf(request.getParameter("id"));
         booksManager.deleteBook(id);
         //after deleting , redirection to the /books page
         response.sendRedirect(response.encodeRedirectURL("http://localhost:9090/MVC-MVC/books"));
      }
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      String id = request.getParameter("id");
      booksManager.deleteBook(Long.parseLong(id));
    
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
