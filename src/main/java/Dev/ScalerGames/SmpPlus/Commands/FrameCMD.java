package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Methods.InvisFrame;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Int;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FrameCMD implements CommandExecutor {

    FileConfiguration config = Main.getInstance().getConfig();

    public boolean onCommand(CommandSender s, Command cmd , String label, String[] args) {

        if (label.equalsIgnoreCase("itemframe")) {

            if (s instanceof Player) {

                Player p = (Player) s;

                if (p.hasPermission("smp+.itemframe")) {

                    if (args.length == 0) {
                        s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /itemframe [set|give] [player] [amount]"));
                    } else {

                        if (args[0].equalsIgnoreCase("give")) {
                            if (args.length == 1) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:give " + p.getName() + " item_frame{EntityTag:{Invisible:1b}} 1");
                            }

                            if (args.length >= 3) {

                                if (Bukkit.getServer().getPlayerExact(args[1]) != null) {

                                    if (Int.isInt(args[2])) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:give " + args[1] + " item_frame{EntityTag:{Invisible:1b}} " + args[2]);
                                    }else {
                                        s.sendMessage(Format.color(config.getString("Prefix") + " &cInvalid Int"));
                                    }

                                } else {
                                    s.sendMessage(Format.color(config.getString("Prefix") + "&cPlayer not online"));
                                }

                            }
                        }

                        else if (args[0].equalsIgnoreCase("set")) {
                            InvisFrame.setFrame(p);
                        }

                        else if (!(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("give"))) {
                            s.sendMessage(Format.color(config.getString("Prefix") + "&c&cUsage: /itemframe [set|give] [player] [amount]"));
                        }

                    }

                }

            } else {
                s.sendMessage(config.getString("Prefix") + "&cPlayer Only");
            }

        }

        return false;
    }

}
