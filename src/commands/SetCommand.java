package commands;

import com.avaje.ebeaninternal.server.deploy.BeanDescriptor;
import com.sun.deploy.config.Config;
import main.Bedwars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import util.Team;
import util.Teamcolor;

/**
 * Created by Hans Dischinger on 26.09.2016.
 */
public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {

        boolean containsTeamcolor = false;

        if (cs instanceof Player) {
            Player player = (Player) cs;

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("bed")) {
                    for (Teamcolor teamcolor : Teamcolor.values()) {
                        if (teamcolor.name().equalsIgnoreCase(args[1])) {
                            containsTeamcolor = true;
                        }
                    }

                    if (containsTeamcolor) {
                        if (player.getLocation().subtract(0, 0.5, 0).getBlock().getType() == Material.BED_BLOCK) {
                            int bedXLocation = player.getLocation().getBlockX();
                            int bedYLocation = player.getLocation().getBlockY();
                            int bedZLocation = player.getLocation().getBlockZ();
                            Bed bed = (Bed) player.getLocation().getBlock().getState().getData();
                            if (bed.isHeadOfBed()) {
                                Bedwars.getInstance().getConfig().set("Bedwars.BedLocation." + args[1] + ".head.X", bedXLocation);
                                Bedwars.getInstance().getConfig().set("Bedwars.BedLocation." + args[1] + ".head.Y", bedYLocation);
                                Bedwars.getInstance().getConfig().set("Bedwars.BedLocation." + args[1] + ".head.Z", bedZLocation);
                            } else {
                                Bedwars.getInstance().getConfig().set("Bedwars.BedLocation." + args[1] + ".feet.X", bedXLocation);
                                Bedwars.getInstance().getConfig().set("Bedwars.BedLocation." + args[1] + ".feet.Y", bedYLocation);
                                Bedwars.getInstance().getConfig().set("Bedwars.BedLocation." + args[1] + ".feet.Z", bedZLocation);
                            }
                            Bedwars.getInstance().saveConfig();
                            player.sendMessage(ChatColor.GREEN + "The " + args[1] + " bed was set");
                        } else {
                            player.sendMessage(ChatColor.RED + "You are not standing on a bed");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "This color is not avialable");
                    }



                } else if (args[0].equalsIgnoreCase("spawner")) {

                    if (player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.GOLD_BLOCK || player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.IRON_BLOCK || player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.HARD_CLAY) {

                        int spawnerX = player.getLocation().getBlockX();
                        int spawnerY = player.getLocation().getBlockY();
                        int spawnerZ = player.getLocation().getBlockZ();


                        if(Bedwars.getInstance().getConfig().contains("Bedwars.SpawnerLocation." + args[1])){
                            Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".X", spawnerX);
                        }

                            Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".X", spawnerX);
                            Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".Y", spawnerY);
                            Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".Z", spawnerZ);
                            Bedwars.getInstance().saveConfig();
                            player.sendMessage(ChatColor.GREEN + "The " + args[1] + " spawner was set");




                        Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".X", spawnerX);
                        Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".Y", spawnerY);
                        Bedwars.getInstance().getConfig().set("Bedwars.SpawnerLocation." + args[1] + ".Z", spawnerZ);
                        Bedwars.getInstance().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "The " + args[1] + " spawner was set");
                    }
                } else if(args[0].equalsIgnoreCase("villager")){
                    int villagerXLocation = player.getLocation().getBlockX();
                    int villagerYLocation = player.getLocation().getBlockY();
                    int villagerZLocation = player.getLocation().getBlockZ();
                    Bedwars.getInstance().getConfig().set("Bedwars.VillagerLocation." + args[1] + ".X", villagerXLocation);
                    Bedwars.getInstance().getConfig().set("Bedwars.VillagerLocation." + args[1] + ".Y", villagerYLocation);
                    Bedwars.getInstance().getConfig().set("Bedwars.VillagerLocation." + args[1] + ".Z", villagerZLocation);
                    Bedwars.getInstance().saveConfig();
                    player.sendMessage("The villager was set");
                    Location villagerLocation = player.getLocation();
                    player.getWorld().spawnEntity(villagerLocation, EntityType.VILLAGER);

                } else if(args[0].equalsIgnoreCase("spawn")){
                    int spawnXLocation = player.getLocation().getBlockX();
                    int spawnYLocation = player.getLocation().getBlockY();
                    int spawnZLocation = player.getLocation().getBlockZ();
                    Bedwars.getInstance().getConfig().set("Bedwars.Spawn." + args[1] + ".X", spawnXLocation);
                    Bedwars.getInstance().getConfig().set("Bedwars.Spawn." + args[1] + ".Y", spawnYLocation);
                    Bedwars.getInstance().getConfig().set("Bedwars.Spawn." + args[1] + ".Z", spawnZLocation);
                    Bedwars.getInstance().saveConfig();
                    player.sendMessage("The " + args[1] + " spawn was set");

                }
            }
        }
        return true;
    }
}
