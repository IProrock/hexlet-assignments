<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType='text/html' pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Certain id=${user.get("id")}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                rel="stylesheet"
                integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                crossorigin="anonymous">
    </head>
    <body>
        <table>
            <tr>
            <td>${user.get("id")}</td>
            <td>${user.get("firstName")} ${user.get("lastName")}</td>
            <td>${user.get("email")}</td>
            <td><a href='/users/delete?id=${user.get("id")}'>delete user</a></td>
            </tr>
        </table>
    </body>
</html>
<!-- END -->
