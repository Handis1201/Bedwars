package pregame;

import commands.CountdownCommand;
import main.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import util.BPlayer;
import util.Gamestate;
import util.GamestateManager;
import util.Team;

/**
 * Created by Hans Dischinger on 23.09.2016.
 */
public class Countdown extends BukkitRunnable {

    public static int count = 61;

    public Countdown() {
        this.runTaskTimer(Bedwars.getInstance(), 0L, 20L);
    }

    @Override
    public void run() {
        count--;
        countSetLevel();
        if (Bukkit.getOnlinePlayers().size() < 4) {
            int missingPlayers = 4 - Bukkit.getOnlinePlayers().size();
            if (count == 45) {
                Bukkit.broadcastMessage(ChatColor.RED + "" + missingPlayers + " players are needed to start the game");
                count = 61;
                return;
            }
        }
        if (count == 60 || count == 45 || count == 30 || count == 15 || count == 10 || count == 5 || count == 4 || count == 3 || count == 2 || count == 1) {
            countSendMessage();
        } else if (count == 0) {
            for(BPlayer bPlayer : Bedwars.getInstance().getPlayerManager().getbPlayerList()){
                if(bPlayer.getTeam() == null){
                    int leastPlayers = -1;
                    Team kleinstesTeam = null;
                    for(Team team : Bedwars.getInstance().getTeamManager().getTeamList()){
                        if (leastPlayers == -1 || team.getTeammember() < leastPlayers) {
                            kleinstesTeam = team;
                            leastPlayers = team.getTeammember();
                        }
                    }
                    if (kleinstesTeam != null) {
                        bPlayer.setTeam(kleinstesTeam);
                    }
                }
                bPlayer.getPlayer().sendMessage("hfis");
                teleportPlayers(bPlayer.getPlayer());
            }
            broadcast(ChatColor.AQUA + "Das Spiel hat gestartet!");
            Bedwars.getInstance().getGamestateManager().setGamestate(Gamestate.INGAME);
        }
    }

    public void countSendMessage() {
        Bukkit.getOnlinePlayers().stream().forEach(player -> {
            player.sendMessage(ChatColor.GOLD + "The game starts in " + ChatColor.GREEN + count + " seconds");
        });

    }

    public void broadcast(String string) {
        Bukkit.getOnlinePlayers().stream().forEach(player -> {
            player.sendMessage(string);
        });

    }

    public void countSetLevel() {
        Bukkit.getOnlinePlayers().stream().forEach(player -> {
            player.setLevel(count);
            player.setExp(0);
        });
    }

    public void teleportPlayers(Player player){
        BPlayer bPlayer = Bedwars.getInstance().getPlayerManager().getBPlayer(player);
        player.teleport(Bedwars.getInstance().getTeamManager().getTeam(bPlayer.getTeamcolor()).getSpawn());
    }
}
