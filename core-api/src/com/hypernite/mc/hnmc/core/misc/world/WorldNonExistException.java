package com.hypernite.mc.hnmc.core.misc.world;

public class WorldNonExistException extends Exception {
    private String world;

    public WorldNonExistException(String world) {
        this.world = world;
    }

    public String getWorld() {
        return world;
    }
}
