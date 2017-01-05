package main;

import commands.CountdownCommand;
import commands.SetCommand;
import commands.TeamCommand;
import listener.AftergameListener;
import listener.IngameListener;
import listener.PregameListener;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import util.*;

/**
 * Created by Hans Dischinger on 22.09.2016.
 */
public class Bedwars extends JavaPlugin {

    private PlayerManager playerManager;
    private static Bedwars instance;
    private BedwarsMaps bedwarsMaps;
    private TeamManager teamManager;
    private PregameListener pregameListener;
    private IngameListener ingameListener;
    private AftergameListener aftergameListener;
    private GamestateManager gamestateManager;

    @Override
    public void onEnable() {
        instance = this;
        this.playerManager = new PlayerManager();
        this.getCommand("start").setExecutor(new CountdownCommand());
        this.getCommand("set").setExecutor(new SetCommand());
        new ConfigManager().config();
        this.pregameListener = new PregameListener();
        this.ingameListener = new IngameListener();
        this.aftergameListener = new AftergameListener();
        this.teamManager = new TeamManager();
        this.gamestateManager = new GamestateManager();
        teamManager.createTeams(new Teamcolor[]{Teamcolor.GREEN, Teamcolor.RED, Teamcolor.YELLOW, Teamcolor.BLUE});
        this.getCommand("team").setExecutor(new TeamCommand());
        gamestateManager.setGamestate(Gamestate.PREGAME);
        for(Teamcolor teamcolor : Teamcolor.values()) teamManager.setSpawnLocation(teamcolor);
        teamManager.setTeamMax(4);
    }

    @Override
    public void onDisable() {
        for (World world : getServer().getWorlds()){
            for (Entity entity : world.getLivingEntities()){
                if(entity.getType() == EntityType.VILLAGER){
                    entity.remove();
                }
            }
        }
        super.onDisable();
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static Bedwars getInstance() {
        return instance;
    }

    public BedwarsMaps getBedwarsMaps() {
        return bedwarsMaps;
    }

    public void setBedwarsMaps(BedwarsMaps bedwarsMaps) {
        this.bedwarsMaps = bedwarsMaps;
    }

    public PregameListener getPregameListener() {
        return pregameListener;
    }

    public IngameListener getIngameListener() {
        return ingameListener;
    }

    public AftergameListener getAftergameListener() {
        return aftergameListener;
    }

    public GamestateManager getGamestateManager() {
        return gamestateManager;
    }
}
