package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import Dev.ScalerGames.SmpPlus.Utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LockCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (s.hasPermission("smp.lockdown")) {
            if (args.length >= 1) {

                if (args[0].equalsIgnoreCase("on")) {
                    Data.getDataConfig().set("Server-Lock", true);
                    Data.getDataConfig().set("Server-Lock-Message", Tools.join(args, 1));
                    Data.saveData();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!p.hasPermission("smp.lockdown.override")) {
                            p.kickPlayer(Format.placeholder(p, "&4&l>> Server Lockdown <<"
                                    + "\n" + Data.getDataConfig().getString("Server-Lock-Message")));
                        }
                    }
                    Bukkit.broadcastMessage(Format.color(Lang.getLangConfig().getString("Prefix")
                            + Lang.getLangConfig().getString("Server-Lockdown-Enabled")));

                }

                if (args[0].equalsIgnoreCase("off")) {
                    Data.getDataConfig().set("Server-Lock", false);
                    Data.saveData();
                    Bukkit.broadcastMessage(Format.color(Lang.getLangConfig().getString("Prefix")
                            + Lang.getLangConfig().getString("Server-Lockdown-Disabled")));
                }

                if (!(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off"))) {
                    Messages.prefix(s, "&cUsage: /lockdown [on|off] <reason>");
                }

            } else {
                Messages.prefix(s, "&cUsage: /lockdown [on|off] <reason>");
            }
        } else {
            Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty())  {
            arguments.add("on"); arguments.add("off");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }

}
