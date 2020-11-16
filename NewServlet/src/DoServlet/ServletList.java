package DoServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import DoServlet.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        if(id == 0){
//            pw.print("<html>" +
//                    "<h3>Доступные пользователи:</h3><br/>" +
//                    "ID пользователя: " +
//                    "<ul>");
//
//            for(Map.Entry<Integer, User> entry : model.getFrontList().entrySet()){
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//
//            }
//            pw.print("<ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//
//
//        }   else if (id > 0){
//                if (id > model.getFrontList().size()){
//                    pw.print("<html>" +
//                            "<h3>Такого пользователя нет :(</h3>" +
//                            "<a href=\"index.jsp\">Домой</a>" +
//                            "</html>");
//
//                }   else {
//                    pw.print("<html>" +
//                            "<h3>Запрошенный пользователь: </h3>" +
//                            "<br/>"+
//                            "Имя: " + model.getFrontList().get(id).getName() + "<br/>" +
//                            "Фамилия: " + model.getFrontList().get(id).getSurname() + "<br/>" +
//                            "Зарплата: " + model.getFrontList().get(id).getSalary() + "<br/>" +
//                            "<a href=\"index.jsp\">Домой</a>" +
//                            "</html>");
//                }
//            } else {
//                pw.print("<html>" +
//                        "<h3>ID должен быть больше 0(</h3>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//
//            }
//    }
    //@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")
    public class ThereIsNoSuchUserException extends RuntimeException { }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb1 = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine())!= null){
                jb1.append(line);
            }
        } catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj1 = gson.fromJson(String.valueOf(jb1), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        int id = jobj1.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        if(id == 0){
            pw.print(gson.toJson(model.getFrontList()));
        } else if (id > 0){
                    if (id > model.getFrontList().size()){
                        throw new ThereIsNoSuchUserException();
                        //pw.print(gson.toJson("Такого пользователя нет"));
                    }   else {
                            pw.print(gson.toJson(model.getFrontList().get(id)));
                        }

                } else {
                        throw new ThereIsNoSuchUserException();
                        //pw.print(gson.toJson("ID должен быть больше 0"));
                    }



    }


}
