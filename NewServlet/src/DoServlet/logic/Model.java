package DoServlet.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Model implements Serializable{

    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1,new User("Tigran","Mantashyan",55555));
        model.put(2,new User("Anton","Osipov",66666));
        model.put(3,new User("Zoya","Andreeva",77777));
        model.put(4,new User("Elena","Vorobey",88888));
    }


    public void add(User user, int id){
        model.put(id,user);
    }
    public void delall(int id) {
        if (id == 0) {
            for (Iterator<Integer> iterator = model.keySet().iterator(); iterator.hasNext(); ) {
                Integer key = iterator.next();
                if (key != 0) {
                    iterator.remove();
                }
            }
        }
    }
    public void del(int id){
        for (Iterator<Integer> iterator = model.keySet().iterator(); iterator.hasNext(); ) {
            Integer key = iterator.next();
            if (key != 0) {
                if (key == id){iterator.remove();}
            }
        }
    }
    public void update(User user,int id){
        model.replace(id,user);
    }

    public Map<Integer, User> getFrontList() {
        return model;
    }
}
