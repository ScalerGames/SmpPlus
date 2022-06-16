package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if (event.getPlayer().getUniqueId().toString().equals(Data.getDataConfig().getString("Settings.player"))) {

            Main.getInstance().getConfig().set("Events." + Data.getDataConfig().getString("Settings.event") + ".message", event.getMessage());
            Main.getInstance().saveConfig();
            event.getPlayer().sendMessage(Format.color(Lang.getLangConfig().getString("Prefix") + "&dSet the " + Data.getDataConfig().getString("Settings.event").toLowerCase() + " event to:&r "
                    + event.getMessage()));
            event.setCancelled(true);
            Data.getDataConfig().set("Settings.player", "");
            Data.getDataConfig().set("Settings.event", "");
            Data.saveData();

        } else {
            if (!Data.getDataConfig().getBoolean("Chat-Muted")) {

                if (event.getPlayer().hasPermission("smp.chat.color") && Main.getInstance().getConfig().getBoolean("Events.Chat.enabled")) {

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
                            .replace("{message}", event.getMessage())));
                }

            } else {
                if (!event.getPlayer().hasPermission("smp.mutechat.override")) {
                    event.setCancelled(true);
                    Messages.prefix(event.getPlayer(), Lang.getLangConfig().getString("Server-Chat-Disabled"));
                } else {
                    event.setFormat(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Chat.format")
                            .replace("{message}", Format.color(event.getMessage()))));
                }
            }
        }

    }

}
