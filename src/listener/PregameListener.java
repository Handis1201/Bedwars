package listener;

import main.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Bed;
import pregame.Countdown;
import pregame.TeamItem;
import util.*;

/**
 * Created by Hans Dischinger on 24.09.2016.
 */
public class PregameListener implements Listener {

    Gamestate gamestate = Gamestate.PREGAME;
    GamestateManager gamestateManager;

    public PregameListener() {
        Bukkit.getPluginManager().registerEvents(this, Bedwars.getInstance());
        gameStart();
    }

    public void gameStart() {
        Countdown countdown = new Countdown();
    }

    /*@EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }*/

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bedwars.getInstance().getPlayerManager().addBPlayer(e.getPlayer());
        TeamItem teamItem = new TeamItem(Bedwars.getInstance().getPlayerManager().getBPlayer(e.getPlayer()));
        teamItem.setItem(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        BPlayer bPlayer = Bedwars.getInstance().getPlayerManager().getBPlayer(e.getPlayer());
        Bedwars.getInstance().getPlayerManager().getbPlayerList().remove(bPlayer);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onTeamItemInteract(PlayerInteractEvent e) {
        Player player = (Player) e.getPlayer();
        if (e.getItem() != null) {
            ItemStack redTeam = new ItemStack(org.bukkit.Material.WOOL, 1, DyeColor.RED.getData());
            ItemStack greenTeam = new ItemStack(org.bukkit.Material.WOOL, 1, DyeColor.GREEN.getData());
            ItemStack blueTeam = new ItemStack(org.bukkit.Material.WOOL, 1, DyeColor.BLUE.getData());
            ItemStack yellowTeam = new ItemStack(org.bukkit.Material.WOOL, 1, DyeColor.YELLOW.getData());
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem().getType() == org.bukkit.Material.BONE) {
                    Inventory createInv = Bukkit.createInventory(player, 45, "Choose your Team"); //jetzt
                    createInv.setItem(11, redTeam);
                    createInv.setItem(15, greenTeam);
                    createInv.setItem(29, blueTeam);
                    createInv.setItem(33, yellowTeam);
                    player.openInventory(createInv);
                }
            }
        }
    }

    @EventHandler
    public void onTeamChoose (InventoryClickEvent e){
        if(e.getCurrentItem() == null){
            return;
        }
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem().getType() == Material.WOOL){
            e.setCancelled(true);

            BPlayer bPlayer = Bedwars.getInstance().getPlayerManager().getBPlayer(player);
            if(e.getCurrentItem().getData().getData() == DyeColor.RED.getData()) {
                bPlayer.setTeam(Bedwars.getInstance().getTeamManager().getTeam(Teamcolor.RED));
            } else if (e.getCurrentItem().getData().getData() == DyeColor.GREEN.getData()) {
                bPlayer.setTeam(Bedwars.getInstance().getTeamManager().getTeam(Teamcolor.GREEN));
            } else if (e.getCurrentItem().getData().getData() == DyeColor.YELLOW.getData()) {
                bPlayer.setTeam(Bedwars.getInstance().getTeamManager().getTeam(Teamcolor.YELLOW));
            } else if (e.getCurrentItem().getData().getData() == DyeColor.BLUE.getData()) {
                bPlayer.setTeam(Bedwars.getInstance().getTeamManager().getTeam(Teamcolor.BLUE));
            }
        }
    }
}




