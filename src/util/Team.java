package util;

import org.bukkit.Location;

/**
 * Created by Hans Dischinger on 22.09.2016.
 */
public class Team {

    private int teammember;
    private Teamcolor teamcolor;
    boolean bed;
    private Location spawn;

    public Teamcolor getTeamcolor() {
        return teamcolor;
    }

    public void setTeamcolor(Teamcolor teamcolor) {
        this.teamcolor = teamcolor;
    }

    public int getTeammember() {
        return teammember;
    }

    public void setTeammember(int teammember) {
        this.teammember = teammember;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }
}
