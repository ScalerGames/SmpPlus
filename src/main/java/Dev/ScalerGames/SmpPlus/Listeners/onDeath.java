package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Events.Death.enabled")) {
            event.setDeathMessage(Format.placeholder(event.getEntity().getPlayer(), Main.getInstance().getConfig().getString("Events.Death.message")
                    .replace("{message}", event.getDeathMessage())));
        }
    }

}
