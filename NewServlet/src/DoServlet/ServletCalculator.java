package DoServlet;

import DoServlet.logic.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import DoServlet.logic.Result;
import DoServlet.logic.ResModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calc")
public class ServletCalculator extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Result res = new Result();
    ResModel model = ResModel.getInstance();
    public class ThereIsNoSuchUserException extends RuntimeException { }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb4 = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine())!= null){
                jb4.append(line);
            }
        } catch (Exception e){
            System.out.println("Error");
        }

        JsonObject jobj4 = (JsonObject)this.gson.fromJson(String.valueOf(jb4), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        double a = jobj4.get("a").getAsDouble();
        double b = jobj4.get("b").getAsDouble();
        char math = jobj4.get("math").getAsCharacter();
        this.res.chet(a, b, math);
        model.add("Result",this.res.getRes());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(model.getFrontList()));

    }



}
