package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;

public class onSleep implements Listener {

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Events.Sleep.single-player-sleep")) {
            Bukkit.getServer().getScheduler().runTaskLater((Plugin) Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (event.getPlayer().isSleeping()) {
                        event.getPlayer().getWorld().setTime(0L); event.getPlayer().getWorld().setStorm(false);
                        event.getPlayer().getWorld().setThundering(false);
                        Bukkit.broadcastMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Sleep.message")));
                    }
                }
            }, 100L);
        }
    }

}
