package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.Damageable;

public class onDestroy implements Listener {

    @EventHandler
    public void onDestroy(BlockBreakEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Durability.enabled")) {
            if (!event.getPlayer().getInventory().getItemInMainHand().getType().isAir()) {
                if (event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() &&((Damageable) event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).hasDamage()) {
                    Damageable dmg = (Damageable) event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                    if ((event.getPlayer().getInventory().getItemInMainHand().getType().getMaxDurability() - dmg.getDamage()) == Main.getInstance().getConfig().getInt("Durability.amount")) {
                        event.getPlayer().sendMessage(Format.placeholder(event.getPlayer(),
                                Main.getInstance().getConfig().getString("Durability.message").replace("{item}",
                                        event.getPlayer().getInventory().getItemInMainHand().getType().toString().toUpperCase())));
                    }
                }
            }
        }
    }

}
