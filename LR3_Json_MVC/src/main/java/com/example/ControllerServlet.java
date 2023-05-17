package com.example;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "ControllerServlet", value = "/books")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Model_library model_library;

    public void init(){
        // Read book library from file on servlet initialization

        try {
            model_library = new Model_library();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //model_library = (Model_library) getServletContext().getAttribute("ControllerServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Convert library list to JSON and send as the response
        String json = new Gson().toJson(model_library.getLibrary());
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        model_library.addBook(newBook);


        // Convert updated library list to JSON and send as the response
        String json = new Gson().toJson(model_library.getLibrary());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}