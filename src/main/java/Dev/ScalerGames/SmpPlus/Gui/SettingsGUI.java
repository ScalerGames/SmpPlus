package Dev.ScalerGames.SmpPlus.Gui;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SettingsGUI implements Listener {

    public static void generate(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, Format.placeholder(p, "&d&lSmp+ &5(Settings)"));
        Integer slot = 0;
        for (String event : Main.getInstance().getConfig().getConfigurationSection("Events").getKeys(false)) {
            if (Main.getInstance().getConfig().contains("Events." + event + ".enabled") || Main.getInstance().getConfig().contains("Events." + event + ".single-player-sleep")) {
                if (Main.getInstance().getConfig().getBoolean("Events." + event + ".enabled") || Main.getInstance().getConfig().getBoolean("Events." + event + ".single-player-sleep")) {
                    ItemStack item = new ItemStack(Material.LIME_DYE);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(Format.color("&5✪ &5&l" + event + " &5✪"));
                    List<String> lore = new ArrayList<String>();
                    lore.add(Format.color("&5Enabled: &2true"));
                    lore.add(Format.color("&5Message: &r" + Main.getInstance().getConfig().getString("Events." + event + ".message")));
                    lore.add(" ");
                    lore.add(Format.color("&dLeft Click: &5Toggle"));
                    lore.add(Format.color("&dRight Click: &5Edit Message"));
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    inv.setItem(slot, item);
                    slot++;
                } else {
                    ItemStack item = new ItemStack(Material.RED_DYE);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(Format.color("&5✪ &5&l" + event + " &5✪"));
                    List<String> lore = new ArrayList<String>();
                    lore.add(Format.color("&5Enabled: &4false"));
                    lore.add(Format.color("&5Message: &r" + Main.getInstance().getConfig().getString("Events." + event + ".message")));
                    lore.add(" ");
                    lore.add(Format.color("&dLeft Click: &5Toggle"));
                    lore.add(Format.color("&dRight Click: &5Edit Message"));
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    inv.setItem(slot, item);
                    slot++;
                }
            }
        }
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(Format.color("&d&lSmp+ &5(Settings)"))) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.isLeftClick()) {
                    //Set the current event to false
                    if (event.getCurrentItem().getType().equals(Material.LIME_DYE) && event.getCurrentItem().getItemMeta().getDisplayName().contains("✪")) {
                        event.getCurrentItem().setType(Material.RED_DYE);
                        ItemMeta meta = event.getCurrentItem().getItemMeta();
                        List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                        lore.set(0, Format.color("&5Enabled: &4false"));
                        meta.setLore(lore);
                        event.getCurrentItem().setItemMeta(meta);
                        Main.getInstance().getConfig().set("Events." + ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',
                                event.getCurrentItem().getItemMeta().getDisplayName().replace("✪", "").replace(" ", ""))) + ".enabled", false);
                        Main.getInstance().saveConfig();
                        //Set the current event to true
                    } else if (event.getCurrentItem().getType().equals(Material.RED_DYE) && event.getCurrentItem().getItemMeta().getDisplayName().contains("✪")) {
                        event.getCurrentItem().setType(Material.LIME_DYE);
                        ItemMeta meta = event.getCurrentItem().getItemMeta();
                        List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                        lore.set(0, Format.color("&5Enabled: &2true"));
                        meta.setLore(lore);
                        event.getCurrentItem().setItemMeta(meta);
                        Main.getInstance().getConfig().set("Events." + ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',
                                event.getCurrentItem().getItemMeta().getDisplayName().replace("✪", "").replace(" ", ""))) + ".enabled", true);
                        Main.getInstance().saveConfig();
                    }
                }
                if (event.isRightClick()) {
                    if ((event.getCurrentItem().getType().equals(Material.LIME_DYE) || event.getCurrentItem().getType().equals(Material.RED_DYE)) && event.getCurrentItem().getItemMeta().getDisplayName().contains("✪")) {
                        String path = event.getCurrentItem().getItemMeta().getDisplayName().replace("✪", "").replace(" ", "");
                        Data.getDataConfig().set("Settings.player", event.getWhoClicked().getUniqueId().toString());
                        Data.getDataConfig().set("Settings.event", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', path)));
                        Data.saveData();
                        event.getWhoClicked().closeInventory();
                        Messages.prefix(event.getWhoClicked(), "&dEnter the new message in chat: ");
                    }
                }
            }
        }
    }

}
