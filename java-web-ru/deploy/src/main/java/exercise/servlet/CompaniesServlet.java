package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        String search = request.getParameter("search");
        PrintWriter pw = response.getWriter();
        List<String> filteredCompanies = new LinkedList<>();

        if (search == null || search == "") {
            for (String comp : companies) {
                pw.write(comp.toString() + "\n");
            }
        }

        else {
            for (String companie : companies) {
                if (companie.contains(search)) {
                    filteredCompanies.add(companie);
                }
            }

            if (filteredCompanies.size() == 0) {
                pw.write("Companies not found");
            } else {
                for (String comp : filteredCompanies) {
                    pw.write(comp.toString() + "\n");
                }
            }
        }
        // END
    }
}
