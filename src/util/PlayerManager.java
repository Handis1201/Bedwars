package util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hans Dischinger on 23.09.2016.
 */
public class PlayerManager {

    private List<BPlayer> bPlayerList = new ArrayList<>();

    public List<BPlayer> getbPlayerList() {
        return bPlayerList;
    }

    public BPlayer getBPlayer(Player player){
        for(BPlayer bPlayer : bPlayerList){
            if(player.equals(bPlayer.getPlayer())){
                return bPlayer;
            }
        }
        BPlayer bPlayer = new BPlayer(player);
        return bPlayer;
    }

    public void addBPlayer(Player player){
        bPlayerList.add(new BPlayer(player));
    }

}
