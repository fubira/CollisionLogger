package net.ironingot.collisionlogger;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class CollisionLogger extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("Minecraft");
    public static CollisionLogger plugin = null;
    public static HashMap<Class, Counter> counterMap;

    @Override
    public void onEnable() {
        new VehicleCollisionListener(this);

        if (plugin != null) {
            return;
        }

        counterMap = new HashMap<Class, Counter>();
        plugin = this;
    }

    @Override
    public void onDisable() {
        if (plugin == null) {
            return;
        }

        counterMap = null;
        plugin = null;
    }

    public Counter getCounter(Class c) {
        Counter counter = counterMap.get(c);
        if (counter == null) {
            counter = new Counter();
            counterMap.put(c, counter);
        }
        return counter;
    }

}
