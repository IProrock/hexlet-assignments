package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {


        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();
        String pagestr = request.getParameter("page");
        Integer page = pagestr==null? 1 : Integer.parseInt(pagestr);

        if (page < 1) {
            page = 1;
        }

        String query = "SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, (page - 1) * 10);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title"),
                        "body",  rs.getString("body")
                ));
            } } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        request.setAttribute("articles", articles);
        request.setAttribute("page", page);


            // END
            TemplateEngineUtil.render("articles/index.html", request, response);


    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        PrintWriter pw = response.getWriter();
//        pw.write("FFF");

        Integer id = Integer.parseInt(getAction(request));
//        pw.write(id.toString());
        String query = "SELECT id, title, body FROM articles WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();

            String title = rs.getString("title");
            String body = rs.getString("body");

            request.setAttribute("title", rs.getString("title"));
            request.setAttribute("body", rs.getString("body"));

//            pw.write(body);

        } catch (Exception e) {
            response.sendError(404);
        }


//         END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
