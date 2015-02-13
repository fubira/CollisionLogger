package net.ironingot.collisionlogger;

import java.lang.Integer;
import java.lang.String;
import java.util.HashMap;

public class Counter {
    private HashMap<String, Integer> countMap;
    
    public Counter() {
        countMap = new HashMap<String, Integer>();
    }

    public int get(String key) {
        Integer ii = countMap.get(key);
        if (ii == null) {
            ii = new Integer(0);
        }
        return ii.intValue();
    }

    public void count(String key) {
        countMap.put(key, new Integer(get(key) + 1));
    }

    public void clear() {
        countMap.clear();
    }
}
