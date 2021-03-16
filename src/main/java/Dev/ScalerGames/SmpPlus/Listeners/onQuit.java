package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        if (!(event.getPlayer().getName() == event.getPlayer().getDisplayName())) {
            Data.getDataConfig().set(event.getPlayer().getUniqueId() + ".display-name", event.getPlayer().getDisplayName());
            Data.saveData();
        }

        if (Main.getInstance().getConfig().getBoolean("Events.Quit.enabled")) {

            event.setQuitMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Quit.message")
                    .replace("{player}", event.getPlayer().getName()).replace("{displayname}", event.getPlayer().getDisplayName())
                    .replace("{world}", event.getPlayer().getWorld().toString())));

        }

    }

}
