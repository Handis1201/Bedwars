package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pregame.Countdown;

/**
 * Created by Hans Dischinger on 24.09.2016.
 */
public class CountdownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {

        if(args.length == 0){
            cs.sendMessage(ChatColor.RED + cmd.getUsage());
            return true;
        } else if (args.length == 1){
            try {
                Countdown.count = Integer.parseInt(args[0]);
                cs.sendMessage(ChatColor.GOLD + "Countdown has been set to " + ChatColor.GREEN + args[0] + " seconds");
                return true;
            } catch (NumberFormatException e) {
                cs.sendMessage(ChatColor.RED + "Time must be a number!");
            }
        }

        return true;
    }
}
