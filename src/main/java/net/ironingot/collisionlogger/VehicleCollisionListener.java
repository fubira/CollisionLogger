package net.ironingot.collisionlogger;

import java.util.UUID;
import java.util.Formatter;
import java.util.HashMap;
import java.lang.StringBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.Entity;
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
    public void VehicleEntityCollisionEvent(VehicleEntityCollisionEvent event) {
        Entity entity = event.getVehicle();
        UUID uuid = entity.getUniqueId();
        Counter counter = plugin.getCounter(event.getClass());

        if (counter.get(uuid.toString()) > 100) {
            World world = entity.getWorld();
            Location location = entity.getLocation();

            plugin.logger.info(
                new StringBuilder().append(
                    new Formatter().format("<%s> ID:[%s] World:%s Pos:(%0.1f,%0.1f,%0.1f)",
                                           "VehicleEntityCollisionEvent",
                                           uuid.toString(), world.getName(),
                                           location.getX(), location.getY(), location.getZ()).out()
                ).toString()
            );
        }
        counter.count(uuid.toString());
    }
}
