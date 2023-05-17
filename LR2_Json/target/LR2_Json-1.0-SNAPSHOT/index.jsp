<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            loadLibrary();

            $("#addBookForm").submit(function (event) {
                event.preventDefault();
                var bookData = {
                    "title": $("#title").val(),
                    "author": $("#author").val(),
                    "publisher": $("#publisher").val(),
                    "year": $("#year").val(),
                    "pages": $("#pages").val()
                };
                addBook(bookData);
            });
        });

        function loadLibrary() {
            $.ajax({
                url: "library",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    displayLibrary(data);
                }
            });
        }

        function addBook(bookData) {
            $.ajax({
                url: "library",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(bookData),
                success: function (data) {
                    displayLibrary(data);
                    $("#addBookForm")[0].reset();
                }
            });
        }

        function displayLibrary(library) {
            var table = $("#libraryTable");
            table.empty();
            $.each(library, function (index, book) {
                var row = $("<tr><td>" + book.title + "</td><td>" + book.author + "</td><td>" + book.publisher + "</td><td>" + book.year + "</td><td>" + book.pages + "</td></tr>");
                table.append(row);
            });
        }
    </script>
</head>
<body>
<h1>Library</h1>
<form id="addBookForm">
    <label for="title">Title:</label>
    <input type="text" id="title" required><br>
    <label for="author">Author:</label>
    <input type="text" id="author" required><br>
    <label for="publisher">Publisher:</label>
    <input type="text" id="publisher" required><br>
    <label for="year">Year:</label>
    <input type="text" id="year" required><br>
    <label for="pages">Pages:</label>
    <input type="text" id="pages" required><br>
    <input type="submit" value="Add">
</form>
<br>
<table id="libraryTable" border="1">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Publisher</th>
        <th>Year</th>
        <th>Pages</th>
    </tr>
</table>
</body>
</html>
