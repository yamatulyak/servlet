package DoServlet;

import com.google.gson.GsonBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import DoServlet.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/del")
    public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public class ThereIsNoSuchUserException extends RuntimeException { }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb2 = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine())!= null){
                jb2.append(line);
            }
        } catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj2 = gson.fromJson(String.valueOf(jb2), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        int id = jobj2.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        if(id == 0){
            pw.print("Удалены все пользователи: " + gson.toJson(model.getFrontList()));
            model.delall(id);

        } else if (id > 0){
            if (id > model.getFrontList().size()){
                throw new ServletDelete.ThereIsNoSuchUserException();
                //pw.print(gson.toJson("Такого пользователя нет"));
            }   else {
                pw.print("Удален пользователь: " + gson.toJson(model.getFrontList().get(id)));
                model.del(id);
            }

        } else {
            throw new ServletDelete.ThereIsNoSuchUserException();
            //pw.print(gson.toJson("ID должен быть больше 0"));
        }



    }


}


