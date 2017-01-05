package util;

import main.Bedwars;
import org.bukkit.entity.Player;


/**
 * Created by Hans Dischinger on 22.09.2016.
 */
public class BPlayer {

    private Player player;
    private Team team;
    private int teamdestroys;
    private int kills;
    private int deaths;
    private int brokenbeds;
    boolean isAlive;
    //private Teamcolor teamcolor;

    public BPlayer (Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if (team.getTeammember() != Bedwars.getInstance().getTeamManager().getTeamMax()) {

            this.team = team;
            player.sendMessage("You are now in Team " + team.getTeamcolor().name());
        } else {
            player.sendMessage("This team is full");
        }
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getBrokenbeds() {
        return brokenbeds;
    }

    public void setBrokenbeds(int brokenbeds) {
        this.brokenbeds = brokenbeds;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getTeamdestroys() {
        return teamdestroys;
    }

    public void setTeamdestroys(int teamdestroys) {
        this.teamdestroys = teamdestroys;
    }

    public void addKill(){
        kills+=1;
    }

    public void addDeath(){
        deaths+=1;
    }

    public Teamcolor getTeamcolor() {
        return getTeam().getTeamcolor();
    }

    /*public void setTeamcolor(Teamcolor teamcolor) {
        this.teamcolor = teamcolor;
    }*/
}
