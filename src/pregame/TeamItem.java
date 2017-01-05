package pregame;


import javafx.scene.paint.Material;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.BPlayer;

/**
 * Created by Hans Dischinger on 23.09.2016.
 */
public class TeamItem {

    private BPlayer bPlayer;

    public TeamItem(BPlayer bPlayer){
        this.bPlayer = bPlayer;
    }

    public void setItem(Player player){
        ItemStack teamItem = new ItemStack(org.bukkit.Material.BONE);
        player.getInventory().setItem(0, teamItem);
    }
}
