package Dev.ScalerGames.SmpPlus.Gui;

import Dev.ScalerGames.SmpPlus.Files.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (Data.getDataConfig().getStringList("Gui-Listener").contains(event.getView().getTitle())) {
            event.setCancelled(true);
        }
    }

}
