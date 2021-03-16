package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Methods.*;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (Data.getDataConfig().contains(event.getPlayer().getUniqueId() + ".display-name")) {
            if (Bukkit.getPluginManager().getPlugin("Essentials") != null || Bukkit.getPluginManager().getPlugin("EssentialsX") != null) {
                Essentials essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
                User user = essentials.getUserMap().getUser(event.getPlayer().getName());
                user.setNickname(Format.color(Data.getDataConfig().getString(event.getPlayer().getUniqueId() + ".display-name")));
            } else {
                event.getPlayer().setDisplayName(Format.color(Data.getDataConfig().getString(event.getPlayer().getUniqueId() + ".display-name")));
            }
        }

        if (event.getPlayer().hasPlayedBefore()) {

            if (Main.getInstance().getConfig().getBoolean("Events.Join.enabled")) {

                event.setJoinMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Join.message").replace("{player}", event.getPlayer().getName())
                        .replace("{displayname}", event.getPlayer().getDisplayName()).replace("{world}", event.getPlayer().getWorld().toString())));

                Message.message(event.getPlayer(), "Join");
                Bar.bar(event.getPlayer(), "Join");
                Title.title(event.getPlayer(), "Join");
                Items.giveItem(event.getPlayer(), "Join");
                Command.console(event.getPlayer(), "Join");
                Command.playerCMD(event.getPlayer(), "Join");


            }

        } else {

            if (Main.getInstance().getConfig().getBoolean("Events.FirstJoin.enabled")) {

                event.setJoinMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Join.message")
                        .replace("{player}", event.getPlayer().getName()).replace("{displayname}", event.getPlayer().getDisplayName()).replace("{world}", event.getPlayer().getWorld().toString())));

                Message.message(event.getPlayer(), "FirstJoin");
                Bar.bar(event.getPlayer(), "FirstJoin");
                Title.title(event.getPlayer(), "FirstJoin");
                Items.giveItem(event.getPlayer(), "FirstJoin");
                Command.console(event.getPlayer(), "FirstJoin");
                Command.playerCMD(event.getPlayer(), "FirstJoin");

            }

        }


    }


}
