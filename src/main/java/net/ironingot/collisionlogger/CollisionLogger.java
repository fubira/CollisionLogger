package net.ironingot.collisionlogger;

import java.io.File;
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
        if (plugin != null) {
            return;
        }

        plugin = this;
        setupConfig();

        getCommand("clog").setExecutor(new CollisionLoggerCommand(this));
        counterStoreMap = new HashMap<Object, CounterStore>();
        new VehicleCollisionListener(this);

        logger.info(getStateString());
    }

    @Override
    public void onDisable() {
    }

    private void setupConfig() {
        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            saveDefaultConfig();
        }

        config = new CollisionLoggerConfig(configFile);
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
        counterStoreMap.clear();
    }

    public String getStateString() {
        return getDescription().getName() + " is currently " +
               (config.isEnable() ? "enabled" : "disabled") + " " +
               (config.isBroadcastEnable() ? "(broadcast mode)" : "(server log mode)");
    }

    public void output(String logString) {
        if (config.isBroadcastEnable())
        {
            getServer().broadcastMessage(ChatColor.DARK_RED + logString);
        } else {
            logger.info(logString);
        }
    }
}
