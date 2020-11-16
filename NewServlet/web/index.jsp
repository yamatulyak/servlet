<%@ page import="DoServlet.logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 08.11.2020
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите ID пользователя (0 - для вывода всего списка пользователей)
  <br/>
  Доступно: <%
  Model model = Model.getInstance();
  out.print(model.getFrontList().size());
  %>
  <br/>
  <form method="get" action="get">
    <label>ID:
      <input type="text" name="id"><br/>
    </label>
    <button type="submit">Поиск</button><br/>
  </form>

  <a href="addUser.html">Создать нового пользователя</a>
  </body>
</html>
