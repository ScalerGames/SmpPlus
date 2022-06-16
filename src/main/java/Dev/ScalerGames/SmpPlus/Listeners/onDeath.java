package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class onDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Events.Death.enabled")) {
            event.setDeathMessage(Format.placeholder(event.getEntity().getPlayer(), Main.getInstance().getConfig().getString("Events.Death.message")
                    .replace("{message}", event.getDeathMessage())));
        }
        if (Main.getInstance().getConfig().getBoolean("Events.PlayerDropHead")) {
            if (event.getEntity().getKiller() != null) {
                ItemStack item = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) item.getItemMeta();
                OfflinePlayer offlinePlayer = event.getEntity().getKiller();
                meta.setOwningPlayer(offlinePlayer);
                item.setItemMeta(meta);
                if (Main.getInstance().getConfig().getBoolean("Events.PlayerDropHead.place-in-inventory")) {
                    event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), item);
                } else {
                    event.getEntity().getKiller().getInventory().addItem(item);
                }
                event.getEntity().getKiller().sendMessage(Format.placeholder(event.getEntity().getKiller(), Main.getInstance().getConfig().getString("Events.PlayerDropHead.message")));
            }
        }
    }

}
