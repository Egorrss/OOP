package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "C:/Users/Egor/IdeaProjects/LR2_Json/library.txt";
    private List<Book> library;

    public void init() throws ServletException {
        // Read book library from file on servlet initialization
        try {
            library = readLibraryFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Convert library list to JSON and send as the response
        String json = new Gson().toJson(library);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read JSON data from request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        // Convert JSON to Book object
        Book book = new Gson().fromJson(jsonData, Book.class);

        // Add the new book to the library
        library.add(book);

        // Write library back to file
        writeLibraryToFile(library);

        // Convert updated library list to JSON and send as the response
        String json = new Gson().toJson(library);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private List<Book> readLibraryFromFile() throws IOException {
        List<Book> library = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length == 5) {
                String title = fields[0].trim();
                String author = fields[1].trim();
                String publisher = fields[2].trim();
                int year = Integer.parseInt(fields[3].trim());
                int pages = Integer.parseInt(fields[4].trim());
                Book book = new Book(title, author, publisher, year, pages);
                library.add(book);
            }
        }
        reader.close();
        return library;
    }

    private void writeLibraryToFile(List<Book> library) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        for (Book book : library) {
            writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getPublisher() + "," + book.getYear()
                    + "," + book.getPages() + System.lineSeparator());
        }
        writer.close();
    }
}