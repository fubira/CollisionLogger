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
import org.bukkit.World;
import org.bukkit.Location;

public class VehicleCollisionListener implements Listener {
    public CollisionLogger plugin;

    public VehicleCollisionListener(CollisionLogger plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void VehicleEntityCollisionEvent(VehicleEntityCollisionEvent event) {
        if (!plugin.isEnable())
            return;

        VehicleCollisionEventLogger(event);
    }

    public void VehicleCollisionEventLogger(VehicleCollisionEvent event) {
        Vehicle vehicle = event.getVehicle();
        CounterStore counterStore = plugin.getCounterStore(event.getClass());
        int entityId = vehicle.getEntityId();

        if (counterStore.get(entityId) > 100) {
            World world = vehicle.getWorld();
            Location location = vehicle.getLocation();

            String logString = new StringBuilder()
                    .append("[").append(event.getEventName())
                    .append("<").append(entityId).append(">")
                    .append("] ")
                    .append("at ").append(world.getName())
                    .append("(")
                    .append((int)location.getX()).append(",")
                    .append((int)location.getY()).append(",")
                    .append((int)location.getZ())
                    .append(")")
                    .toString();

            plugin.log(logString);
            counterStore.set(entityId, 0);
        }
        counterStore.count(entityId);
    }
}
