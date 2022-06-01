package Dev.ScalerGames.SmpPlus.Utils;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static void giveItem(Player p, String path) {
        if (Main.getInstance().getConfig().contains("Events." + path + ".items")) {
            for (String item : Main.getInstance().getConfig().getStringList("Events." + path + ".items")) {
                if (item.contains(":")) {
                    String[] separate = item.split(":");
                    try {
                        if (Tools.isInt(separate[1])) {
                            ItemStack itemStack = new ItemStack(Material.matchMaterial(separate[0]), Integer.parseInt(separate[1]));
                            p.getInventory().addItem(itemStack);
                        }

                    } catch (Exception e) {
                        Main.getInstance().getLogger().info("&cInvalid item id in an event with player :" + p.getDisplayName());
                    }
                } else {
                    try {
                        ItemStack itemStack = new ItemStack(Material.matchMaterial(item));
                        p.getInventory().addItem(itemStack);
                    } catch (Exception e) {
                        Main.getInstance().getLogger().info("&cInvalid item id in an event with player :" + p.getDisplayName());
                    }
                }
            }
        }
    }

}
