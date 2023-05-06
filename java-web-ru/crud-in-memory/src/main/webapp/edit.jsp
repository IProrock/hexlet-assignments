<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href="/users">Все пользователи</a>
            <!-- BEGIN -->
            <c:if test = "${error != null}">
                <div>
                    <mark>${error}</mark>
                </div>
            </c:if>
            <form action="./edit" method="post">
                            <div class="mb-3">
                                <label>Имя</label>
                                <input class="form-control" type="text" name="firstName" value="${firstName}">
                            </div>
                            <div class="mb-3">
                                 <label>Фамилия</label>
                                 <input class="form-control" type="text" name="lastName" value="${lastName}">
                            </div>
                            <div class="mb-3">
                                 <label>email</label>
                                 <input class="form-control" type="text" name="email" value="${email}">
                            </div>
                            <div class="mb-3">
                                  <label>id</label>
                                  <input class="form-control" type="id" name="id" value="${id}">
                            </div>
                            <button class="btn btn-primary" type="submit">Редактировать</button>
                        </form>
            <!-- END -->
        </div>
    </body>
</html>
