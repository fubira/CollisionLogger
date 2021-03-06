package net.ironingot.collisionlogger;

import java.io.File;
import java.io.IOException;

public class CollisionLoggerConfig extends Config {

    public  CollisionLoggerConfig(File file) {
        super(file);
    }

    public Boolean isEnable() {
        return getAsBoolean("enable", Boolean.FALSE);
    }

    public void enable() {
        setAsBoolean("enable", Boolean.TRUE);
    }

    public void disable() {
        setAsBoolean("enable", Boolean.FALSE);
    }

    public Boolean isBroadcastEnable() {
        return getAsBoolean("broadcast", Boolean.FALSE);
    }

    public void enableBroadcast() {
        setAsBoolean("broadcast", Boolean.TRUE);
    }

    public void disableBroadcast() {
        setAsBoolean("broadcast", Boolean.FALSE);
    }
}