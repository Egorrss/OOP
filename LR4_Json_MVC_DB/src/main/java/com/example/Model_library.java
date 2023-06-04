package com.example;


import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model_library {
    private List<Book> library = new ArrayList<>();
    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;
    //private Driver driver;
    //private Statement statement;
    public Model_library() throws IOException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        if(!connection.isClosed()){System.out.println("Соединение с БД установлено");}
        else System.out.println("Соединение с БД закрыто");

        this.library = getLibraryFromDB();
    }

    /**
     * ищет по ключю
     * @param id
     * @return book
     */
    public Book getBookById(int id){
        for (Book book : library)
            if (book.getId() == id)
                return book;
        return null;
    }

    public void deleteBookById(int bookId) throws SQLException {
        String query_deleteById = "DELETE FROM my_db.library WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query_deleteById);
        stmt.setInt(1, bookId);
        stmt.executeUpdate();
    }

    public void updateBook(Book updatedBook) throws SQLException {
        String query_updateBook = "UPDATE my_db.library SET title=?, author=?, publisher=?, year=?, pages=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query_updateBook);
        stmt.setString(1, updatedBook.getTitle());
        stmt.setString(2, updatedBook.getAuthor());
        stmt.setString(3, updatedBook.getPublisher());
        stmt.setInt(4, updatedBook.getYear());
        stmt.setInt(5, updatedBook.getPages());
        stmt.setInt(6, updatedBook.getId());
        stmt.executeUpdate();
    }


    /**
     * Добавляет в БД
     * @param book
     * @throws IOException
     * @throws SQLException
     */
    public void addBook(Book book) throws IOException, SQLException {
        Statement statement = connection.createStatement();

        String title     = book.getTitle();
        String author    = book.getAuthor();
        String publisher = book.getPublisher();
        int    year      = book.getYear();
        int    pages     = book.getPages();

        String query_getAll = "insert into library (title, author, publisher, year, pages) " +
                "values (" +
                "'"+title+"'," +
                "'"+author+"'," +
                "'"+publisher+"'," + year + ", " + pages + ")";

        statement.execute(query_getAll);
    }

    /**
     * @return все записи из БД в List<Book>
     * @throws SQLException
     */
    public List<Book> getLibraryFromDB() throws SQLException {

        Statement statement = connection.createStatement();
        String query_getAll = "SELECT * FROM library";

        ResultSet resultSet = statement.executeQuery(query_getAll);
        String libraryTEXT ="";
        library.clear();
        while (resultSet.next()){

            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            String publisher = resultSet.getString(4);
            int year = resultSet.getInt(5);
            int pages = resultSet.getInt(6);
            libraryTEXT+=id+" "+title+author+" "+publisher+" "+year+" "+pages+"\n";

            Book book = new Book(id, title, author, publisher, year, pages);
            library.add(book);
        }
        System.out.println(libraryTEXT);
        return library;
    }

    public List<Book> getLibrary(){
        return library;
    }

}