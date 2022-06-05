package Dev.ScalerGames.SmpPlus.Gui;

import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemHandler {

    public static void item(Inventory inv, String name, String slot, Player p) {

        String itemname = slot.substring(slot.lastIndexOf(".") + 1);

        try {

            ItemStack item = new ItemStack(Material.valueOf(Gui.getGuiConfig().getString("Menus." + name + ".items." + itemname + ".item")));
            ItemMeta meta = item.getItemMeta();

            displayName(meta, p, name, itemname);

            lore(meta, p, name, itemname);

            enchants(meta, p, name, itemname);

            flags(meta, p, name, itemname);

            item.setItemMeta(meta);
            try {
                if (Gui.getGuiConfig().isInt("Menus." + name + ".items." + itemname + ".slot")) {
                    inv.setItem(Gui.getGuiConfig().getInt("Menus." + name + ".items." + itemname + ".slot"), item);
                }
                if (Gui.getGuiConfig().isString("Menus." + name + ".items." + itemname + ".slot")) {
                    String[] list = Gui.getGuiConfig().getString("Menus." + name + ".items." + itemname + ".slot").split("-");
                    if (list.length == 0) {
                        inv.setItem(Integer.parseInt(Gui.getGuiConfig().getString("Menus." + name + ".items." + itemname + ".slot")), item);
                    }
                    else {
                        String from = list[0];
                        String too = list[1];
                        for (int i = Integer.parseInt(from); i <= Integer.parseInt(too); i++) {
                            inv.setItem(i, item);
                        }
                    }
                }
                if (Gui.getGuiConfig().isList("Menus." + name + ".items." + itemname + ".slot")) {
                    for (String integer : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemname + ".slot")) {
                        String[] list = integer.split("-");
                        if (list.length == 0) {
                            inv.setItem(Integer.parseInt(integer), item);
                        } else {
                            String from = list[0];
                            String too = list[1];
                            for (int i = Integer.parseInt(from); i <= Integer.parseInt(too); i++) {
                                inv.setItem(i, item);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Messages.logger("&cAn item could not find it's slot in the gui");
            }


        } catch (Exception e) {
            ItemStack item = new ItemStack(Material.BARRIER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Format.color("&4&lInvalid Item"));
            item.setItemMeta(meta);
            inv.setItem(Gui.getGuiConfig().getInt("Menus." + name + ".items." + itemname + ".slot"), item);
            Messages.logger("&cInvalid Item in " + p.getName() + "'s open gui");
        }

    }

    public static void displayName(ItemMeta meta, Player p, String name, String itemname) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemname + ".display-name", true)) {
            meta.setDisplayName(Format.placeholder(p, Gui.getGuiConfig().getString("Menus." + name + ".items." + itemname + ".display-name")));
        }
    }

    public static void lore(ItemMeta meta, Player p, String name, String itemname) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemname + ".lore", true)) {
            List<String> lore = new ArrayList<String>();
            for (String list : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemname + ".lore")) {
                lore.add(Format.placeholder(p, list));

            }
            meta.setLore(lore);
        }
    }

    public static void enchants(ItemMeta meta, Player p, String name, String itemname) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemname + ".enchantments", true)) {

            for (String enchantments : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemname + ".enchantments")) {

                String[] split = enchantments.split(":");
                String enchant = split[0];
                int level = Integer.parseInt(split[1]);

                try {
                    meta.addEnchant(Enchantment.getByName(enchant), level, true);
                } catch (Exception e) {
                    Messages.logger("&cInvalid Enchant in " + p.getName() + "'s open gui");
                }

            }
        }
    }

    public static void flags(ItemMeta meta, Player p, String name, String itemname) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemname + ".flags", true)) {

            for (String flags : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemname + ".flags")) {

                try {
                    meta.addItemFlags(ItemFlag.valueOf(flags));
                } catch (Exception e) {
                    Messages.logger("&cInvalid Flag in " + p.getName() + "'s open gui");
                }

            }

        }
    }

}
