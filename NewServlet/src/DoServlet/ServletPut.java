package DoServlet;

import com.google.gson.GsonBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import DoServlet.logic.User;
import DoServlet.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public class ThereIsNoSuchUserException extends RuntimeException { }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb3 = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine())!= null){
                jb3.append(line);
            }
        } catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj3 = gson.fromJson(String.valueOf(jb3), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        int id = jobj3.get("id").getAsInt();
        String name = jobj3.get("name").getAsString();
        String surname = jobj3.get("surname").getAsString();
        double salary = jobj3.get("salary").getAsDouble();
        User user = new User(name, surname, salary);

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if(id == 0){
            throw new ServletPut.ThereIsNoSuchUserException();

        } else if (id > 0){
            if (id > model.getFrontList().size()){
                throw new ServletPut.ThereIsNoSuchUserException();
                //pw.print(gson.toJson("Такого пользователя нет"));
            }   else {
                pw.print("Пользователь c id=" + id);
                model.update(user, id);
                pw.print(" Был обновлен на пользователя: " + gson.toJson(model.getFrontList().get(id)));
            }

        } else {
            throw new ServletPut.ThereIsNoSuchUserException();
            //pw.print(gson.toJson("ID должен быть больше 0"));
        }



    }


}


