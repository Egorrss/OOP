package com.example.lr1_about_us;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "aboutServlet", value = "/about-servlet")
public class AboutServlet extends HttpServlet {
    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println();
        out.println(
            "<html><head>"+
            "<meta charset=\"utf-8\" />"+
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />"+
            "<meta name=\"description\" content=\"\" />"+
            "<meta name=\"author\" content=\"\" />"+
            "<title>Туры по горам</title>"+
            "<link rel=\"icon\" type=\"image/x-icon\" href=\"about_us_web/assets/favicon.ico\" />"+
            "<!-- Font Awesome icons (free version)-->"+
            "<script src=\"https://use.fontawesome.com/releases/v6.3.0/js/all.js\" crossorigin=\"anonymous\"></script>"+

            "<link href=\"https://fonts.googleapis.com/css?family=Varela+Round\" rel=\"stylesheet\" />"+
            "<link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\" />"+

            "<link href=\"about_us_web/css/styles.css\" rel=\"stylesheet\" />"+
        "</head>"
        );
        out.println(
                "<body>"+
                "<header class=\"masthead\">"+
                "<div class=\"container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center\">"+
                "<div class=\"d-flex justify-content-center\">"+
                "<div class=\"text-center\">"+
                "<h2 class=\"text-white-50 mx-auto mt-2 mb-5\">Наша компания уже более 10 лет занимается организацией туров по горам по всей России, а так же в горах Казахстана. В нашей команде сотни гидов, которые помогут вам насладится красотой природы"+
                "<br><br><a class=\"btn btn-primary\" href=\"index.jsp\">Поехали!</a>"+
                "</div>"+
                "</div>"+
                "</div>"+
                "</header>"+
                "</body></html>"
        );
    }
    public void destroy() {}
}