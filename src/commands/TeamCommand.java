package commands;

import main.Bedwars;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import util.BPlayer;
import util.Team;
import util.Teamcolor;

/**
 * Created by Hans Dischinger on 15.10.2016.
 */
public class TeamCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {


        if (cs instanceof Player) {
            if (args.length >= 1) {
                String farbe = args[0];
                Player player = ((Player) cs).getPlayer();
                BPlayer bPlayer = Bedwars.getInstance().getPlayerManager().getBPlayer(player);
                try {
                    Teamcolor teamcolor = Teamcolor.valueOf(farbe.toUpperCase());
                    Team team = Bedwars.getInstance().getTeamManager().getTeam(teamcolor);
                    bPlayer.setTeam(team);
                    cs.sendMessage(ChatColor.GREEN + "Du wurdest erfolgreich dem " + teamcolor.name() + " Team zugewiesen!");
                } catch (Exception e){
                    e.printStackTrace();
                    cs.sendMessage(ChatColor.RED + "FEHLER!");
                }
            }
        }
        return true;
    }
}
