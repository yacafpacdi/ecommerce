package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.model.AuthorModel;
import fr.afpa.ecommerce.model.BookModel;
import fr.afpa.ecommerce.model.CategoryModel;
import fr.afpa.ecommerce.model.EditorModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetailBookController", urlPatterns = {"/Book"})
public class DetailBookController extends HttpServlet {

    private final String bookPage = "/WEB-INF/default/Book.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        BookModel bookModel = new BookModel();
        AuthorModel authorModel = new AuthorModel();
        EditorModel editorModel = new EditorModel();
        CategoryModel categoryModel = new CategoryModel();

        try {
            request.setAttribute("categories", categoryModel.findParentCategories());
            request.setAttribute("book", bookModel.detailBook(id));
            request.setAttribute("authors", authorModel.findAuthorsByBook(id));
            request.setAttribute("editor", editorModel.findEditorByBook(id));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DetailBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(bookPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
