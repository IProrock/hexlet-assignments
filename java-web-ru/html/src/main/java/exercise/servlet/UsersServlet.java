package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path pathToUsersLstFile = Paths.get("./src/main/resources/users.json").toAbsolutePath().normalize();
        ObjectMapper mapper = new ObjectMapper();
        List<User> usrLst = mapper.readValue(Files.readString(pathToUsersLstFile), new TypeReference<List<User>>() {});

        return usrLst;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<User> ustLst = getUsers();
        PrintWriter pw = response.getWriter();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                                <html lang=\\"ru\\">
                                    <head>
                                        <meta charset=\\"UTF-8\\">
                                        <title>Example application | Users</title>
                                        <link rel=\\"stylesheet\\" href=\\"mysite.css\\">
                                    </head>
                                    <body>
                                    <table>\n
                """);
        for (User usr : ustLst) {
            body.append("""
                    <tr>
                        <td>""" + usr.getId() + """
                        </td>
                        <td>
                          <a href="/users/""" + usr.getId() + """
                        ">""" + usr.getFirstName() + " " + usr.getLastName() + """
                        </a>
                        </td>
                        ...
                      </tr>
                    """);
        }

        body.append("</table>");
        body.append("""
                </body>
                            </html>
                """);

        pw.write(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<User> ustLst = getUsers();
        StringBuilder body = new StringBuilder();
        PrintWriter pw = response.getWriter();
        body.append("""
                <!DOCTYPE html>
                                <html lang=\\"ru\\">
                                    <head>
                                        <meta charset=\\"UTF-8\\">
                                        <title>Example application | Users</title>
                                        <link rel=\\"stylesheet\\" href=\\"mysite.css\\">
                                    </head>
                                    <body>
                                    <table>\n
                """);

        for (User usr : ustLst) {
            if (usr.getId().equals(id)) {
                body.append("<tr>");
                body.append("<td> + UserId: </td>");
                body.append("<td>" + usr.getId() + "</td>");
                body.append("</tr>");
                body.append("<tr>");
                body.append("<td> + FirstName: </td>");
                body.append("<td>" + usr.getFirstName() + "</td>");
                body.append("</tr>");
                body.append("<tr>");
                body.append("<td> + LastName: </td>");
                body.append("<td>" + usr.getLastName() + "</td>");
                body.append("</tr>");
                body.append("<tr>");
                body.append("<td> + Email: </td>");
                body.append("<td>" + usr.getEmail() + "</td>");
                body.append("</tr>");
                body.append("</table>");
                body.append("""
                        </body>
                            </html>
                        """);



                pw.write(body.toString());
                return;
            }
        }
        response.sendError(404, "Not found");
        // END
    }
}
