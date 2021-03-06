package org.maxgamer.quickshop.util.location;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class StringOf {

    @NotNull
    private final Location location;

    @NotNull
    private final World world;

    public StringOf(@NotNull Location location) {
        if (location.getWorld() == null) {
            throw new IllegalStateException("World of the location cannot be null!");
        }

        this.location = location;
        this.world = location.getWorld();
    }

    @NotNull
    public String asKey() {
        return asString().replaceAll(":", "/").replaceAll("\\.", "_");
    }

    @NotNull
    public String asString() {
        String s = world.getName() + ":";

        s +=
                String.format(
                        Locale.ENGLISH, "%.2f,%.2f,%.2f", location.getX(), location.getY(), location.getZ());

        if (location.getYaw() != 0f || location.getPitch() != 0f) {
            s += String.format(Locale.ENGLISH, ":%.2f:%.2f", location.getYaw(), location.getPitch());
        }
        return s;
    }

}
