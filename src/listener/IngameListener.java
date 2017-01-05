package listener;

import main.Bedwars;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Bed;
import util.BPlayer;
import util.Gamestate;
import util.Team;
import util.Teamcolor;

/**
 * Created by Hans Dischinger on 23.09.2016.
 */
public class IngameListener implements Listener {

    Gamestate gamestate = Gamestate.INGAME;

    public IngameListener() {
        Bukkit.getPluginManager().registerEvents(this, Bedwars.getInstance());
    }


    private Bedwars bedwars = Bedwars.getInstance();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        BPlayer bPlayer = bedwars.getPlayerManager().getBPlayer(player);
        e.setDeathMessage(ChatColor.RED + "Du bist gestorben");
        bedwars.getPlayerManager().getBPlayer(player).addDeath();

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        BPlayer bPlayer = bedwars.getPlayerManager().getBPlayer(player);
        if(bedwars.getTeamManager().getTeam(bPlayer.getTeamcolor()).isBed() == true){
            e.setRespawnLocation(bedwars.getTeamManager().getTeam(bPlayer.getTeamcolor()).getSpawn());
        } else {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.getWorld().setWeatherDuration(0);
        e.setCancelled(true);
    }

    @EventHandler
    public void onKill(EntityDamageByEntityEvent e) {
        Player killer = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();
        killer.sendMessage(ChatColor.GREEN + "Du hast " + bedwars.getInstance().getPlayerManager().getBPlayer(victim).getTeamcolor() + victim.getName() + ChatColor.GREEN + " getötet!");
        bedwars.getInstance().getPlayerManager().getBPlayer(killer).addKill();
        bedwars.getInstance().getPlayerManager().getBPlayer(victim).addDeath();
    }

    public void brokenBed() {
        //    bedwars.getInstance().getPlayerManager().getBPlayer(player).getTeam().setBed(false);
    }

    @EventHandler
    public void onDeathAndBrokenBed(PlayerDeathEvent e) {
        if (bedwars.getInstance().getPlayerManager().getBPlayer(e.getEntity().getPlayer()).getTeam().isBed() == false) {
            e.getEntity().getPlayer().hidePlayer(e.getEntity().getPlayer());
            e.getEntity().getPlayer().setGameMode(GameMode.CREATIVE);
        }
    }

    @EventHandler
    public void onHitAndBrokenBed(EntityDamageByEntityEvent e) {
        Player deathDamager = (Player) e.getDamager();
        Player deathVictim = (Player) e.getEntity();
        if (bedwars.getInstance().getPlayerManager().getBPlayer(deathDamager).isAlive() == false)
            e.setCancelled(true);
    }

    @EventHandler
    public void onBreakBed(BlockBreakEvent e) {
        Player player = e.getPlayer();
        BPlayer bPlayer = bedwars.getPlayerManager().getBPlayer(player);
        if (e.getBlock().getType() == Material.BED_BLOCK) {
            for (Teamcolor teamcolor : Teamcolor.values()) {
                if (bedwars.getConfig().contains("Bedwars.BedLocation." + teamcolor.name().toLowerCase())) {
                    if ((e.getBlock().getLocation().getBlockX() == bedwars.getConfig().getInt("Bedwars.BedLocation." + teamcolor.name().toLowerCase() + ".head.X")
                            && e.getBlock().getLocation().getBlockY() == bedwars.getConfig().getInt("Bedwars.BedLocation." + teamcolor.name().toLowerCase() + ".head.Y")
                            && e.getBlock().getLocation().getBlockZ() == bedwars.getConfig().getInt("Bedwars.BedLocation." + teamcolor.name().toLowerCase() + ".head.Z"))
                            || (e.getBlock().getLocation().getBlockX() == bedwars.getConfig().getInt("Bedwars.BedLocation." + teamcolor.name().toLowerCase() + ".feet.X")
                            && e.getBlock().getLocation().getBlockY() == bedwars.getConfig().getInt("Bedwars.BedLocation." + teamcolor.name().toLowerCase() + ".feet.Y")
                            && e.getBlock().getLocation().getBlockZ() == bedwars.getConfig().getInt("Bedwars.BedLocation." + teamcolor.name().toLowerCase() + ".feet.Z"))) {

                        if (bPlayer.getTeamcolor() == teamcolor) {
                            e.setCancelled(true);
                            player.sendMessage(ChatColor.DARK_RED + "You can't break your own bed");

                        } else{
                            bedwars.getTeamManager().getTeam(teamcolor).setBed(false);
                            e.setCancelled(true);
                            e.getBlock().setType(Material.AIR);
                            for(Player p : Bukkit.getOnlinePlayers()){
                                p.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 1);
                            }
                    }
                }
            }
        }
    }

}


}
