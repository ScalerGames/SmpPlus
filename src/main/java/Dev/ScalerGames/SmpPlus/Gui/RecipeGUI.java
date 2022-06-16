package Dev.ScalerGames.SmpPlus.Gui;

import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RecipeGUI implements Listener {
    public static void generate(Player p, String recipe_name) {
        Inventory inv = Bukkit.createInventory(null, 27, Format.color("&d&lSmp+ &5(New Recipe)"));
        List<Integer> empty = new ArrayList<Integer>(); empty.add(3); empty.add(4); empty.add(5); empty.add(26);
        empty.add(12); empty.add(13); empty.add(14); empty.add(16); empty.add(21); empty.add(22); empty.add(23);
        ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta(); meta.setDisplayName(Format.color("&f")); item.setItemMeta(meta);
        for (int i = 0; i < inv.getSize(); i++) {
            if (!empty.contains(i)) {
                inv.setItem(i, item);
            }
        }
        ItemStack green = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta g_meta = green.getItemMeta(); g_meta.setDisplayName(Format.color("&2&lConfirm")); green.setItemMeta(meta);
        inv.setItem(26, green);
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(Format.color("&d&lSmp+ &5(New Recipe)"))) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.PURPLE_STAINED_GLASS_PANE)) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                event.setCancelled(true);
                if (event.getInventory().getItem(16) != null) {

                } else {
                    event.getWhoClicked().closeInventory();
                    Messages.prefix(event.getWhoClicked(), "&cFailed to create the crafting recipe (No result)");
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals(Format.color("&d&lSmp+ &5(New Recipe)"))) {
            for (int i = 0; i < event.getInventory().getSize(); i++) {
                if (event.getInventory().getItem(i) != null && !(event.getInventory().getItem(i).getType().equals(Material.PURPLE_STAINED_GLASS_PANE))) {
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(i));
                }
            }
        }
    }

}
