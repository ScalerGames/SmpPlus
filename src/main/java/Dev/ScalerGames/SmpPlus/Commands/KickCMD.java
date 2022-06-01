package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import Dev.ScalerGames.SmpPlus.Utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label , String[] args) {

        if (label.equalsIgnoreCase("kick")) {
            if (s.hasPermission("smp.kick")) {
                if (args.length >= 2) {
                    if (Bukkit.getServer().getPlayer(args[0]) != null) {
                        if (!Bukkit.getServer().getPlayer(args[0]).hasPermission("smp.kick.override")) {
                            Bukkit.getPlayer(args[0]).kickPlayer(Format.placeholder(Bukkit.getPlayer(args[0]), Tools.join(args, 1)));
                        } else {
                            Messages.prefix(s, "&cYou can't kick that player!");
                        }
                    } else {
                        Messages.prefix(s, "&cThat player dose not exist!");
                    }
                } else {
                    Messages.prefix(s, "&cUsage /kick <player> <reason>");
                }
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        if (label.equalsIgnoreCase("kickall")) {
            if (s.hasPermission("smp.kickall")) {
                if (args.length >= 1) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!p.hasPermission("smp.kick.override")) {
                            p.kickPlayer(Format.placeholder(p, Tools.join(args, 0)));
                        }
                    }
                } else {
                    Messages.prefix(s, "&cUsage: /kickall <reason>");
                }
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        return false;
    }

}
