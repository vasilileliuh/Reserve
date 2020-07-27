package pack;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class MyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MyServlet.class);

    String name = "Unifun";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
//            name = URLEncoder.encode(request.getParameter("name"), "UTF-8");
            name = request.getParameter("name");

            if (name.trim().length() == 0)
                throw new NullPointerException();
        } catch (NullPointerException e) {
            LOGGER.error("Input problem", e);
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h3>hello, " + name + "</h3>");
        name = "Unifun";

        String nameFromHeader = request.getHeader("name");
        writer.write("<p>Name: " + nameFromHeader + "</p>");
        writer.write("<p>name from header</p>\n");

        String nameFromBody = request.getReader().lines().collect(Collectors.joining());
        writer.write("<p>Name: " + nameFromBody + "</p>");
        writer.write("<p>name from body(in raw)</p>\n");


    }
}
