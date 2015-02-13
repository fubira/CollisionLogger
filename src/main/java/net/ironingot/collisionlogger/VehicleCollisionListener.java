package net.ironingot.collisionlogger;

import java.util.UUID;
import java.util.Formatter;
import java.util.HashMap;
import java.lang.StringBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCollisionEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.Entity;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.Location;

public class VehicleCollisionListener implements Listener {
    public CollisionLogger plugin;
    private HashMap<Object, Integer> count = new HashMap<Object, Integer>();

    public VehicleCollisionListener(CollisionLogger plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void VehicleBlockCollisionEvent(VehicleBlockCollisionEvent event) {
        VehicleCollisionEventLogger(event);
    }

    @EventHandler
    public void VehicleEntityCollisionEvent(VehicleEntityCollisionEvent event) {
        VehicleCollisionEventLogger(event);
    }

    public void VehicleCollisionEventLogger(VehicleCollisionEvent event) {
        Vehicle vehicle = event.getVehicle();
        String uuid = vehicle.getUniqueId().toString();
        Counter counter = plugin.getCounter(event.getClass());

        if (counter.get(uuid) > 100) {
            World world = vehicle.getWorld();
            Location location = vehicle.getLocation();

            String logString = new StringBuilder()
                    .append("[").append(event.getEventName())
                    .append("<").append(Integer.toHexString(uuid.hashCode())).append(">")
                    .append("] ")
                    .append("at ").append(world.getName())
                    .append("(")
                    .append((int)location.getX()).append(",")
                    .append((int)location.getY()).append(",")
                    .append((int)location.getZ())
                    .append(")")
                    .toString();

            plugin.logger.info(logString);
            plugin.getServer().broadcastMessage(ChatColor.DARK_RED + logString);
            counter.set(uuid, 0);
        }
        counter.count(uuid);
    }
}
