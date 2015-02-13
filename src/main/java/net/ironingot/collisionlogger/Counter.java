package net.ironingot.collisionlogger;

import java.lang.Integer;
import java.lang.String;
import java.util.HashMap;

public class Counter {
    private HashMap<String, Integer> count;
    
    public Counter() {
        count = new HashMap<String, Integer>();
    }

    public int get(String key) {
        Integer ii = count.get(key);
        if (ii == null) {
            ii = new Integer(0);
        }
        return ii.intValue();
    }

    public void count(String key) {
        count.put(key, new Integer(get(key) + 1));
    }
}
