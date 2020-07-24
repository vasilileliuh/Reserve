package pack;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class MyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MyServlet.class);

    String name = "Unifun";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            name = URLEncoder.encode(request.getParameter("name"), "utf-8");
            if (name.trim().length() == 0)
                throw new NullPointerException();
        } catch (NullPointerException e) {
            LOGGER.error("Input problem", e);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>hello, " + name + "</h3>");
        name = "Unifun";
    }
}
