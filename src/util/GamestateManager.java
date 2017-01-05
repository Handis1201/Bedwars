package util;

import listener.PregameListener;
import main.Bedwars;
import org.bukkit.event.HandlerList;

/**
 * Created by Hans Dischinger on 24.09.2016.
 */
public class GamestateManager {

    Gamestate gamestate;

    public Gamestate getGamestate() {
        return gamestate;
    }

    public void setGamestate(Gamestate gamestate) {
        switch (gamestate){
            case PREGAME:
                HandlerList.unregisterAll(Bedwars.getInstance().getIngameListener());
                HandlerList.unregisterAll(Bedwars.getInstance().getAftergameListener());
                Bedwars.getInstance().getServer().getPluginManager().registerEvents(Bedwars.getInstance().getPregameListener(), Bedwars.getInstance());
                break;
            case INGAME:
                HandlerList.unregisterAll(Bedwars.getInstance().getPregameListener());
                HandlerList.unregisterAll(Bedwars.getInstance().getAftergameListener());
                Bedwars.getInstance().getServer().getPluginManager().registerEvents(Bedwars.getInstance().getIngameListener(), Bedwars.getInstance());
                break;
            case AFTERGAME:
                HandlerList.unregisterAll(Bedwars.getInstance().getIngameListener());
                HandlerList.unregisterAll(Bedwars.getInstance().getPregameListener());
                Bedwars.getInstance().getServer().getPluginManager().registerEvents(Bedwars.getInstance().getAftergameListener(), Bedwars.getInstance());
                break;
        }
        this.gamestate = gamestate;
    }
}
