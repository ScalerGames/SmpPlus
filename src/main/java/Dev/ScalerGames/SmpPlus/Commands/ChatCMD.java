package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("mutechat")) {
            if (s.hasPermission("smp.mutechat")) {
                Data.getDataConfig().set("Chat-Muted", true);
                Data.saveData();
                Bukkit.getServer().broadcastMessage(Format.color(Lang.getLangConfig().getString("Prefix")
                        + Lang.getLangConfig().getString("Server-Chat-Disable")));
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        if (label.equalsIgnoreCase("unmutechat")) {
            if (s.hasPermission("smp.unmutechat")) {
                Data.getDataConfig().set("Chat-Muted", false);
                Data.saveData();
                Bukkit.getServer().broadcastMessage(Format.color(Lang.getLangConfig().getString("Prefix")
                        + Lang.getLangConfig().getString("Server-Chat-Enable")));
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        return false;
    }

}
