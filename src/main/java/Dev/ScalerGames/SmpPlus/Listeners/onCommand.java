package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class onCommand implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        List<String> commands = Arrays.asList("help","rules");
        commands.forEach(cmd -> {
            if (event.getMessage().toLowerCase().contains("/" + cmd.toLowerCase())) {
                if (Main.getInstance().getConfig().contains("Commands." + cmd + ".enabled") &&
                        !Main.getInstance().getConfig().getBoolean("Commands." + cmd + ".enabled")) {
                    event.setCancelled(true);
                    if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
                        Bukkit.dispatchCommand(event.getPlayer(), "essentials:" + event.getMessage().replace("/", ""));
                    } else {
                        Bukkit.dispatchCommand(event.getPlayer(), "minecraft:" + cmd);
                    }
                }
            }
        });
    }

}
