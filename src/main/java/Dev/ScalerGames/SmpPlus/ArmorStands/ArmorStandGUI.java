package Dev.ScalerGames.SmpPlus.ArmorStands;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ArmorStandGUI implements Listener {

    static ConfigurationSection config = Main.getInstance().getConfig();

    public static void create(Player p) {
        Inventory inv = Bukkit.createInventory(null, config.getInt("armor-stand-editor.size"), Format.color(config.getString("armor-stand-editor.name")));

        for (int i = 0; i >= inv.getSize(); i++) {

        }

        p.openInventory(inv);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', config.getString("armor-stand-editor.name"))))) {

            //Head X

            if (event.getSlot() == config.getInt("armor-stand-editor.items.visible.slot")) {
                onStandClick.as.getHeadPose().setX(1); //Placeholder
            }


        }
    }

}
