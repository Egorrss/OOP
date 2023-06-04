package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "ControllerServlet", value = "/books/*")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Model_library model_library;

    public void init(){
        // Read book library from DB on servlet initialization
        try {
            model_library = new Model_library();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Convert library list to JSON and send as the response
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(model_library.getLibrary());
        response.getWriter().write(json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read JSON data from request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        // Convert JSON to Book object
        Book newBook = new Gson().fromJson(jsonData, Book.class);

        // Add the new book to the library
        // Write library back to file
        try {
            model_library.addBook(newBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Convert updated library list to JSON and send as the response
        try {
            String json = new Gson().toJson(model_library.getLibraryFromDB());
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read JSON data from request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        // Convert JSON to Book object
        Book updatedBook = new Gson().fromJson(jsonData, Book.class);

        // Update the book in the library
        // Write library back to file
        try {
            model_library.updateBook(updatedBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Convert updated library list to JSON and send as the response
        try {
            String json = new Gson().toJson(model_library.getLibraryFromDB());
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            try {
                int bookId = Integer.parseInt(pathInfo.substring(1));  // Получение id книги из URL
                model_library.deleteBookById(bookId);  // Удаление книги по id

                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException | SQLException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error deleting book: " + e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid book id");
        }
    }

}