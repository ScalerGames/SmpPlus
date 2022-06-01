package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Events.Quit.enabled")) {
            event.setQuitMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Quit.message")));
        }
    }

}
