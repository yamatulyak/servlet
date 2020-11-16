package DoServlet.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResModel implements Serializable {
        private static final DoServlet.logic.ResModel instance = new DoServlet.logic.ResModel();

        private final Map<String, Double> resmodel;

        public static DoServlet.logic.ResModel getInstance() {
            return instance;
        }

        private ResModel() {
            resmodel = new HashMap<>();

        }
        public void add(String str, double res){
        resmodel.put(str,res);
    }
        public Map<String, Double> getFrontList() {
            return resmodel;
        }

    }
