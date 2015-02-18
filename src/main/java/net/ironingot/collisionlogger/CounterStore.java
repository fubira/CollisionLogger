package net.ironingot.collisionlogger;

import java.lang.Integer;
import java.util.HashMap;

public class CounterStore {
    private HashMap<Integer, Integer> countMap;
    
    public CounterStore() {
        countMap = new HashMap<Integer, Integer>();
    }

    public int get(int id) {
        Integer ii = countMap.get(new Integer(id));
        if (ii == null) {
            ii = new Integer(0);
        }
        return ii.intValue();
    }

    public void set(int id, int ii) {
        countMap.put(new Integer(id), new Integer(ii));
    }

    public void count(int id) {
        set(id, get(new Integer(id)) + 1);
    }
}
