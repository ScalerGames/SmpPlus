package Dev.ScalerGames.SmpPlus.Gui;

import Dev.ScalerGames.SmpPlus.Files.Data;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (GuiListener.storage.containsValue(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', event.getView().getTitle())))) {
            event.setCancelled(true);
        }
    }

    public static HashMap<String, String> storage = new HashMap<String, String>();
    public static void store(String menu, String name) {
        storage.put(menu, name);
    }

}
