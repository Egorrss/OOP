package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model_library {
    private List<Book> library;
    private static final String FILE_PATH = "C:/Users/Egor/IdeaProjects/LR3_Json_MVC/library.txt";

    public Model_library() throws IOException {

        library = readLibraryFromFile(FILE_PATH);
    }

    public void addBook(Book book) throws IOException {
        library.add(book);
        writeLibraryToFile(library);
    }
    public List<Book> getLibrary(){
        return library;
    }
    private List<Book> readLibraryFromFile(String FILE_PATH) throws IOException {
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