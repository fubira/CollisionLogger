package net.ironingot.collisionlogger;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class CollisionLogger extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("CollisionLogger");
    public static CollisionLogger plugin = null;
    public static HashMap<Object, CounterStore> counterStoreMap;

    private boolean enable;
    private boolean broadcast;

    @Override
    public void onEnable() {
        if (plugin == null)
        {
            plugin = this;

            new VehicleCollisionListener(this);
            celarCounterStore();

        }

        getCommand("clog").setExecutor(new CollisionLoggerCommand(this));

        enable();
        enableBroadcast();
    }

    @Override
    public void onDisable() {
    }

    public void enable() { enable = true; }
    public void disable() { enable = false; }
    public boolean isEnable() { return enable; }

    public void enableBroadcast() { broadcast = true; }
    public void disableBroadcast() { broadcast = false; }
    public boolean isEnableBroadcast() { return broadcast; }

    public String getPermissionName(String tag) {
        return "collisionlogger." + tag;
    } 

    public CounterStore getCounterStore(Object obj)
    {
        CounterStore counterStore = counterStoreMap.get(obj);

        if (counterStore == null) {
            counterStore = new CounterStore();
            counterStoreMap.put(obj, counterStore);
        }

        return counterStore;
    }

    public void celarCounterStore()
    {
        counterStoreMap = new HashMap<Object, CounterStore>();
    }

    public void log(String logString) {
        if (isEnableBroadcast())
        {
            getServer().broadcastMessage(ChatColor.DARK_RED + logString);
        } else {
            logger.info(logString);
        }
    }
}
