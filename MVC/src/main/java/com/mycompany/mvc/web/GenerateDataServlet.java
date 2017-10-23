/*
-----------------------------------------------------------------------------------
 File        : GenerateDateServlet.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : Servlet who is responsible for generating books
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

public class GenerateDataServlet extends HttpServlet {

    @EJB
    private BooksManagerLocal booksManager;

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
        request.getRequestDispatcher("/WEB-INF/pages/generateBooks.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long nbBooks = new Long(-1);
        boolean error = false;
        //verification of number pages
        try {
            nbBooks = Long.parseLong(request.getParameter("inputNbBooks"));
            if (nbBooks > 0 || nbBooks < Integer.MAX_VALUE) {
                booksManager.generateData(nbBooks);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/books"));
            }
            request.setAttribute("nbBooksError", "Field Number of data has a wrong value");
            error = true;

        } catch (NumberFormatException e) {
            error = true;
            request.setAttribute("nbBooksError", "Field Number of data has a wrong value");
        }

        request.getRequestDispatcher("/WEB-INF/pages/generateBooks.jsp").forward(request, response);

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
