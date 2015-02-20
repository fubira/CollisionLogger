package net.ironingot.collisionlogger;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class CollisionLogger extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("CollisionLogger");
    public static CollisionLogger plugin = null;

    private CollisionLoggerConfig config;
    private HashMap<Object, CounterStore> counterStoreMap;

    @Override
    public void onEnable() {
        if (plugin == null)
        {
            plugin = this;

            new VehicleCollisionListener(this);
            clearCounterStore();

        }

        config = new CollisionLoggerConfig(getDataFolder(), "config.yml");

        getCommand("clog").setExecutor(new CollisionLoggerCommand(this));
    }

    @Override
    public void onDisable() {
    }

    public CollisionLoggerConfig getPluginConfig() {
        return config;
    }

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

    public void clearCounterStore()
    {
        counterStoreMap = new HashMap<Object, CounterStore>();
    }

    public void output(String logString) {
        if (config.isEnableBroadcast())
        {
            getServer().broadcastMessage(ChatColor.DARK_RED + logString);
        } else {
            logger.info(logString);
        }
    }
}
