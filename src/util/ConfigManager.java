package util;

import main.Bedwars;
import org.bukkit.material.Bed;

/**
 * Created by Hans Dischinger on 25.09.2016.
 */
public class ConfigManager {

    public void config() {

        Bedwars.getInstance().getConfig().set("Bedwars.Map", BedwarsMaps.STANDART.name());
        Bedwars.getInstance().saveConfig();


    }

}
