package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Gui.SettingsGUI;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SmpCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("smp")) {

            if (s.hasPermission("smp.cmd")) {

                if (args.length == 0) {
                    Messages.prefix(s, "&dYou are running version &d&n"
                            + Main.getInstance().getDescription().getVersion() + " &dof SMP+");
                }

                if (args.length >= 1) {
                    //Command for reloading the plugin
                    if (args[0].equalsIgnoreCase("reload")) {
                        Main.getInstance().reloadConfig();
                        Data.reloadData();
                        Lang.reloadLang();
                        Gui.reloadGui();
                        int i = 0;
                        for (String menu : Gui.getGuiConfig().getConfigurationSection("Menus").getKeys(false)) {
                            Data.getDataConfig().getStringList("Gui-Listener").set(i,
                                    Format.color(Data.getDataConfig().getString("Menus." + menu.substring(menu.lastIndexOf(".") + 1) + ".name")));
                            i++;
                        }
                        Main.getInstance().getLogger().info(Format.color(Data.getDataConfig().getStringList("Gui-Listener").toString()));
                        Messages.prefix(s, "&2Reloaded Smp+");
                    }
                    //Command for checking the version of the plugin
                    if (args[0].equalsIgnoreCase("version")) {
                        Messages.prefix(s, "&dThis server is running version &d&n"
                                + Main.getInstance().getDescription().getVersion() + "&d of SMP+");
                    }

                    if (args[0].equalsIgnoreCase("settings")) {
                        SettingsGUI.generate((Player) s);
                    }

                    if (!(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("version") || args[0].equals("settings"))) {
                        Messages.prefix(s, "&cUsage: /smp [reload|version]");
                    }

                }

            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }

        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty())  {
            arguments.add("version"); arguments.add("reload");
            arguments.add("settings");
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
