package com.hypernite.mc.hnmc.core.utils.converters;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Yaml 儲存 Location 用
 */
public class LocationSerializer {
    public static Map<String, Object> locToConfigSection(Location location) {
        Map<String, Object> serilizer = new LinkedHashMap<>();
        serilizer.put("world", location.getWorld().getName());
        serilizer.put("x", location.getX());
        serilizer.put("y", location.getY());
        serilizer.put("z", location.getZ());
        serilizer.put("pitch", (double) location.getPitch());
        serilizer.put("yaw", (double) location.getYaw());
        return serilizer;
    }

    public static Optional<Location> mapToLocation(ConfigurationSection section) {
        String world = Optional.ofNullable(section.getString("world")).orElseThrow(() -> new NoSuchElementException("world is null"));
        World bukkitWorld = Bukkit.getWorld(world);
        if (bukkitWorld == null) return Optional.empty();
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        double pitch = section.getDouble("pitch");
        double yaw = section.getDouble("yaw");
        return Optional.of(new Location(bukkitWorld, x, y, z, (float) pitch, (float) yaw));
    }
}
