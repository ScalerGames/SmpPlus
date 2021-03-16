package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static void giveItem(Player p, String path) {
        if (Main.getInstance().getConfig().contains("Events." + path + ".items")) {

            for (String item : Main.getInstance().getConfig().getStringList("Events." + path + ".items")) {

                try {
                    ItemStack itemStack = new ItemStack(Material.matchMaterial(item));
                    p.getInventory().addItem(itemStack);
                } catch (Exception e) {
                    Main.getInstance().getLogger().info(Main.getInstance().getConfig().getString("Prefix") + "&cInvalid Item ID In First Join Items");
                }

            }

        }

    }

}
