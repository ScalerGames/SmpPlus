package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Gui.GuiCreator;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class SmpCMD implements CommandExecutor {

    FileConfiguration config = Main.getInstance().getConfig();

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("smp")) {

            if (s.hasPermission("smp.cmd")) {

                if (args.length == 0) {
                    s.sendMessage(Format.color(config.getString("Prefix") + "&3You are running version &3&n"
                            + Main.getInstance().getDescription().getVersion() + "&3 of Smp+"));
                }

                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("reload")) {

                        Main.getInstance().reloadConfig();
                        Gui.reloadGui();
                        ConfigurationSection section = Gui.getGuiConfig().getConfigurationSection("Menus");
                        for (String menu : section.getKeys(false)) {
                            String menuname = menu.substring(menu.lastIndexOf(".") + 1);
                            GuiCreator.store(menuname, ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', Gui.getGuiConfig().getString("Menus." + menuname + ".name"))));
                        }
                        Data.reloadData();
                        s.sendMessage(Format.color(config.getString("Prefix") + "&2Reloaded Smp+"));

                    }

                    if (args[0].equalsIgnoreCase("version")) {

                        s.sendMessage(Format.color(config.getString("Prefix") + "&3This server is running version &3&n"
                        + Main.getInstance().getDescription().getVersion() + "&3 of Smp+"));

                    }

                    if (!(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("version"))) {
                        s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /smp [reload|version]"));
                    }

                }

                if (args.length >= 2) {
                    s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /smp [reload|version]"));
                }

            } else {
                s.sendMessage(Format.color(config.getString("Prefix") + "&cYou lack permission"));
            }

        }

        return false;
    }

}
