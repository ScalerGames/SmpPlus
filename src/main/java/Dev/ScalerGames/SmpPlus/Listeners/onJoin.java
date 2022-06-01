package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Items;
import Dev.ScalerGames.SmpPlus.Utils.Methods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (Data.getDataConfig().getBoolean("Server-Lock")) {
            if (!event.getPlayer().hasPermission("smp.lockdown.override")) {
                event.getPlayer().kickPlayer(Format.placeholder(event.getPlayer(), Lang.getLangConfig().getString("Lockdown-Deny-Join")
                        + "\n" + Data.getDataConfig().getString("Server-Lock-Message")));
            }
        } else {

            if (event.getPlayer().hasPlayedBefore()) {

                if (Main.getInstance().getConfig().getBoolean("Events.Join.enabled")) {

                    //Setting the join message
                    event.setJoinMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.Join.message")));
                    //Custom settings changed in the configs, for when the user joins
                    Methods.message(event.getPlayer(), "Join");
                    Methods.bar(event.getPlayer(), "Join");
                    Methods.title(event.getPlayer(), "Join");
                    Methods.consoleCMD(event.getPlayer(), "Join");
                    Methods.playerCMD(event.getPlayer(), "Join");
                    Items.giveItem(event.getPlayer(), "Join");
                }

            } else {

                if (Main.getInstance().getConfig().getBoolean("Events.FirstJoin.enabled")) {

                    //Setting the join message
                    event.setJoinMessage(Format.placeholder(event.getPlayer(), Main.getInstance().getConfig().getString("Events.FirstJoin.message")));
                    //Custom setting changed in the configs, for the first time a player joins
                    Methods.message(event.getPlayer(), "FirstJoin");
                    Methods.bar(event.getPlayer(), "FirstJoin");
                    Methods.title(event.getPlayer(), "FirstJoin");
                    Methods.consoleCMD(event.getPlayer(), "FirstJoin");
                    Methods.playerCMD(event.getPlayer(), "FirstJoin");
                    Items.giveItem(event.getPlayer(), "FirstJoin");
                }

            }

        }

    }

}
