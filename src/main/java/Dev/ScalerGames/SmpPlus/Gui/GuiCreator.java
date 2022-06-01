package Dev.ScalerGames.SmpPlus.Gui;

import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiCreator {

    public static void generator(Player p, String name) {

        Inventory inv = Bukkit.createInventory(null, Gui.getGuiConfig().getInt("Menus." + name + ".size"),
                Format.placeholder(p, Gui.getGuiConfig().getString("Menus." + name + ".name")));

        ConfigurationSection invslot = Gui.getGuiConfig().getConfigurationSection("Menus." + name + ".items");

        for (String slot : invslot.getKeys(false)) {
            ItemHandler.item(inv, name, slot, p);
        }

        p.openInventory(inv);

    }

}
