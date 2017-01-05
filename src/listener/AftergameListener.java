package listener;

import main.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import util.Gamestate;

/**
 * Created by Hans Dischinger on 24.09.2016.
 */
public class AftergameListener implements Listener {


    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    public AftergameListener(){
        Bukkit.getPluginManager().registerEvents(this, Bedwars.getInstance());
    }

    Gamestate gamestate = Gamestate.AFTERGAME;
}
