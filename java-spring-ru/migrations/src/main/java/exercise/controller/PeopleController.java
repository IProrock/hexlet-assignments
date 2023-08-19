package exercise.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path= "", produces = "application/json")
    public String listPersons() {

        String query = "SELECT * FROM person";
        List<Map<String, Object>> mapresult = jdbc.queryForList(query);

        StringJoiner result = new StringJoiner(",", "{", "}");
        for (Map<String, Object> person : mapresult) {
            result.add("\"first_name\": \"" + person.get("first_name") + "\"");
            result.add("\"last_name\": \"" + person.get("last_name") + "\"");
        }
        String strResult = result.toString();


        return strResult;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public String printById(@PathVariable("id") String id) {

        String query = "SELECT * FROM person WHERE id=" + id;
        Map<String, Object> mapresult = jdbc.queryForMap(query);

        String result = "{\"first_name\": \"" + mapresult.get("first_name") + "\", ";
        result = result + "\"last_name\": \"" + mapresult.get("last_name") + "\"}";

        return result;

    }

    // END
}
