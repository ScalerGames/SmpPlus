package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Gui.GuiCreator;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("help")) {

            if (s.hasPermission("smp.help")) {

                if (Main.getInstance().getConfig().getString("Commands.help.method").equalsIgnoreCase("GUI")) {
                    if (s instanceof Player) {
                        Player p = (Player) s;
                        GuiCreator.generator(p, Main.getInstance().getConfig().getString("Commands.help.gui-name"));
                    } else {
                        s.sendMessage(Format.color(Main.getInstance().getConfig().getString("Prefix") + "&cPlayer only command"));
                    }
                }

                if (Main.getInstance().getConfig().getString("Commands.help.method").equalsIgnoreCase("CHAT")) {
                    for (String help : Main.getInstance().getConfig().getStringList("Commands.help.help-list")) {
                        s.sendMessage(Format.color(help));
                    }
                }

            } else {
                s.sendMessage(Format.color(Main.getInstance().getConfig().getString("Prefix") + "&cInvalid Permission"));
            }

        }

        return false;
    }

}
