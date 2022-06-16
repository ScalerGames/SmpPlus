package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class EventCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("event")) {

            if (args.length == 0) {
                if (Main.getInstance().getConfig().getConfigurationSection("CustomEvents.main-command") != null) {
                    if (Main.getInstance().getConfig().contains("CustomEvents.main-command.commands")) {
                        for (String Command : Main.getInstance().getConfig().getStringList("CustomEvents.main-command.commands")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Command);
                        }
                    }
                    if (Main.getInstance().getConfig().contains("CustomEvents.main-command.messages")) {
                        for (String msg : Main.getInstance().getConfig().getStringList("CustomEvents.main-command.messages")) {
                            s.sendMessage(Format.placeholder((Player) s, msg));
                        }
                    }
                }
            }

            if (args.length >= 1) {
                if (Main.getInstance().getConfig().getConfigurationSection("CustomEvents.sub-commands") != null) {
                    if (Main.getInstance().getConfig().getConfigurationSection("CustomEvents.sub-commands").getKeys(false).contains(args[0])) {

                        //Functions
                        if (Main.getInstance().getConfig().contains("CustomEvents.sub-commands." + args[0] + ".commands")) {
                            for (String Command :Main.getInstance().getConfig().getStringList("CustomEvents.sub-commands." + args[0] + ".commands")) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Command);
                            }
                        }

                        if (Main.getInstance().getConfig().contains("CustomEvents.sub-commands." + args[0] + ".messages")) {
                            for (String msg : Main.getInstance().getConfig().getStringList("CustomEvents.sub-commands." + args[0] + ".messages")) {
                                s.sendMessage(Format.placeholder((Player) s, msg));
                            }
                        }

                    } else {
                        Messages.prefix(s, "&cUnknown command");
                    }
                }
            }

        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            for (String arg : Main.getInstance().getConfig().getConfigurationSection("CustomEvents.sub-commands").getKeys(false)) {
                arguments.add(arg);
            }
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
