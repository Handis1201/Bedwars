package util;

import main.Bedwars;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hans Dischinger on 15.10.2016.
 */
public class TeamManager {

    List<Team> teamList = new ArrayList<>();
    int teamMax;

    public TeamManager(){

    }

    public void createTeams(Teamcolor[] colors){
        for(Teamcolor teamcolor : colors){
            Team team = new Team();
            team.setTeamcolor(teamcolor);
            team.setBed(true);
            teamList.add(team);
        }
    }

    public Team getTeam(Teamcolor teamcolor){
        for(Team team : teamList){
            if(team.getTeamcolor() == teamcolor){
                return team;
            }
        }
        return null;
    }

    public int getTeamMax() {
        return teamMax;
    }

    public void setTeamMax(int teamMax) {
        if(Bedwars.getInstance().getBedwarsMaps() == BedwarsMaps.STANDART){
            teamMax = 4;
        }
        this.teamMax = teamMax;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void setSpawnLocation(Teamcolor teamcolor){
        Team team = Bedwars.getInstance().getTeamManager().getTeam(teamcolor);
        int x = Bedwars.getInstance().getConfig().getInt("Bedwars.Spawn." + teamcolor.name().toLowerCase() + ".X");
        int y = Bedwars.getInstance().getConfig().getInt("Bedwars.Spawn." + teamcolor.name().toLowerCase() + ".Y");
        int z = Bedwars.getInstance().getConfig().getInt("Bedwars.Spawn." + teamcolor.name().toLowerCase() + ".Z");
        Bedwars.getInstance().getServer().broadcastMessage(x + ", " + y + ", " + z + ", " + teamcolor.name().toLowerCase());
        Location spawn = new Location(Bedwars.getInstance().getServer().getWorlds().get(0), x, y, z);
        team.setSpawn(spawn);
    }

}
