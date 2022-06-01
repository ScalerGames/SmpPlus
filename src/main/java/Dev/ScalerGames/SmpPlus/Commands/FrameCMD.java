package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import Dev.ScalerGames.SmpPlus.Utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FrameCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender s, Command cmd, String label , String[] args) {

        if (label.equalsIgnoreCase("itemframe")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (s.hasPermission("smp.itemframe")) {
                    if (args.length == 0) {
                        Messages.prefix(s, "&cUsage: /itemframe [set|give] <player> <amount>");
                    } else {

                        if (args[0].equalsIgnoreCase("give")) {
                            if (args.length == 1) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:give " + p.getName() + " item_frame{EntityTag:{Invisible:1b}} 1");
                            } else if (args.length >= 3) {
                                if (Bukkit.getServer().getPlayerExact(args[1]) != null) {
                                    if (Tools.isInt(args[2])) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:give " + args[1] + " item_frame{EntityTag:{Invisible:1b}} " + args[2]);
                                    } else {
                                        Messages.prefix(s, "&cInvalid number");
                                    }
                                } else {
                                    Messages.prefix(s, "&cPlayer not online");
                                }
                            } else {
                                Messages.prefix(s, "&cUsage: /itemframe give <player> <amount>");
                            }
                        } else if (args[0].equalsIgnoreCase("set")) {
                            setFrame(p);
                        } else {
                            Messages.prefix(s, "&cUsage: /itemframe [set|give] <player> <amount>");
                        }
                    }
                }
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Player-Only-Command"));
            }
        }

        return false;
    }

    public void setFrame(Player p) {

        List<Entity> entityList = p.getNearbyEntities(Main.getInstance().getConfig().getDouble("ItemFrames.Range.X"),
                Main.getInstance().getConfig().getDouble("ItemFrames.Range.Y"), Main.getInstance().getConfig().getDouble("ItemFrames.Range.Z"));

        for (Entity current: entityList) {
            if (current instanceof ItemFrame) {
                ((ItemFrame) current).setVisible(false);
            }
        }

    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("set"); arguments.add("give");
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
