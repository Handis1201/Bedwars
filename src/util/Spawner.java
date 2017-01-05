package util;

import org.bukkit.Location;

/**
 * Created by Hans Dischinger on 15.10.2016.
 */
public class Spawner {

    private Location location;
    private SpawnerType spawnerType;
    private int countdown = 0;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SpawnerType getSpawnerType() {
        return spawnerType;
    }

    public void setSpawnerType(SpawnerType spawnerType) {
        this.spawnerType = spawnerType;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
}
