package net.ironingot.collisionlogger;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    private File configFile;
    private YamlConfiguration configYaml;

    public Config(File folder, String path) {
        configFile = new File(folder, path);
        load();
    }

    public void load() {
        if (!configFile.exists())
        {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        configYaml = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        try {
            configYaml.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getAsBoolean(String key, Boolean defaultValue) {
        Boolean value = (Boolean) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsBoolean(String key, Boolean value)
    {
        configYaml.set(key, value);
    }

    public Integer getAsInteger(String key, Integer defaultValue) {
        Integer value = (Integer) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsInteger(String key, Integer value)
    {
        configYaml.set(key, value);
    }

    public Double getAsDouble(String key, Double defaultValue) {
        Double value = (Double) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsDouble(String key, Double value)
    {
        configYaml.set(key, value);
    }

    public String getAsString(String key, String defaultValue) {
        String value = (String) configYaml.get(key);

        if (value == null) {
            configYaml.set(key, defaultValue);
            save();
        }

        return value;
    }

    public void setAsString(String key, String value)
    {
        configYaml.set(key, value);
    }

}