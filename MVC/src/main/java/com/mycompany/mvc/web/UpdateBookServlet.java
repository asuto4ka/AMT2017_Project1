/*
 -----------------------------------------------------------------------------------
 File        : UpdateBookServlet.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : Servlet who is responsible for updating an entry for a specific book
 Note(s) : 
 -----------------------------------------------------------------------------------
 */
package com.mycompany.mvc.web;

import com.mycompany.mvc.model.Book;
import com.mycompany.mvc.services.dao.BooksManagerLocal;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBookServlet extends HttpServlet {

    @EJB
    private BooksManagerLocal booksManager;

    // if there is ID , user redirected to /books page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("id") == null) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "books"));
        }

        Book bookTmp = booksManager.findBook(Long.valueOf(request.getParameter("id")));
        request.setAttribute("book", bookTmp);

        request.getRequestDispatcher("/WEB-INF/pages/updateBook.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean error = false;

        Book bookTmp = booksManager.findBook(Long.valueOf(request.getParameter("id")));
        request.setAttribute("book", bookTmp);

        response.setContentType("text/html;charset=UTF-8");
        //extracting values and replace old values by new ones.
        long id = new Long(-1);
        try {
            id = Long.parseLong(request.getParameter("id"));
            //verification of the existence of a book with the given ID
            if (booksManager.findBook(Long.valueOf(request.getParameter("id"))) == null) {
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "books"));
            }
        } catch (NumberFormatException e) {
            error = true;
        }
        String summary = request.getParameter("inputSummary");
        String title = request.getParameter("inputTitle");
        //verify fiel title
        if (title == null || title.equals("")) {
            request.setAttribute("titleError", "Field Title is empty");
            error = true;
        }

        String author = request.getParameter("inputAuthor");
        //verify fiel author
        if (author == null || author.equals("")) {
            request.setAttribute("authorError", "Field Author is empty");
            error = true;
        }

        int releaseDate = 0, nbPages = 0;
        try {

            releaseDate = Integer.parseInt(request.getParameter("inputReleaseDate"));

            //verification on year validity
            if (releaseDate < 0 || releaseDate > Calendar.getInstance().get(Calendar.YEAR)) {
                request.setAttribute("dateError", "Field Release date has a wrong value");
                error = true;
            }
        } catch (NumberFormatException e) {
            error = true;
            request.setAttribute("dateError", "Field Release date has a wrong value");
        }

        //verification of number pages
        try {
            nbPages = Integer.parseInt(request.getParameter("inputNbPages"));
            if (nbPages < 1 || nbPages > 10000) {
                request.setAttribute("pagesError", "Field Number of pages has a wrong value");
                error = true;
            }
        } catch (NumberFormatException e) {
            error = true;
            request.setAttribute("pagesError", "Field Release date has a wrong value");
        }

        if (error) {
            request.getRequestDispatcher("/WEB-INF/pages/updateBook.jsp").forward(request, response);
        } else {
            booksManager.updateBook(id, title, summary, author, releaseDate, nbPages);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/books"));
        }

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
