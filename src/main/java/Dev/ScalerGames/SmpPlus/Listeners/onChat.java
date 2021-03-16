package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if (event.getPlayer().hasPermission("smp.chat.color")) {

            if (event.getPlayer().hasPermission("smp.chat.emoji")) {
                event.setMessage(Format.color(event.getMessage()).replace(":heart:", "♥").replace(":tick:", "✔").replace(":cross:", "✖")
                        .replace(":warn:", "⚠").replace(":smile:", "☺").replace(":happy:", "☻").replace(":sad:", "☹").replace(":tickbox:", "☑")
                        .replace(":crossbox:", "☒").replace(":star:", "⭐").replace(":sword:", "⚔").replace(":pickaxe:", "⛏").replace(":axe:", "🪓")
                        .replace(":bow:", "🏹"));
            } else {
                event.setMessage(Format.color(event.getMessage()));
            }
        }

        if (Main.getInstance().getConfig().getBoolean("Events.Chat.enabled")) {
            event.setFormat(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Chat.format")
            .replace("{player}", event.getPlayer().getName()).replace("{displayname}", event.getPlayer().getDisplayName())
                    .replace("{message}", event.getMessage()).replace("{world}", event.getPlayer().getWorld().toString().toLowerCase())
                    .replace("{WORLD}", event.getPlayer().getWorld().toString().toUpperCase())));
        }

    }

}
