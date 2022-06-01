package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Gui.GuiCreator;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("rules")) {
            if (s.hasPermission("smp.rules")) {
                if (Main.getInstance().getConfig().getString("Commands.rules.method").equalsIgnoreCase("GUI")) {
                    if (s instanceof Player) {
                        GuiCreator.generator((Player) s, Main.getInstance().getConfig().getString("Commands.rules.gui-name"));
                    } else {
                        Messages.prefix(s, Lang.getLangConfig().getString("Player-Only-Command"));
                    }
                }

                if (Main.getInstance().getConfig().getString("Commands.rules.method").equalsIgnoreCase("CHAT")) {
                    StringBuilder ruleList = new StringBuilder();
                    for (String rule : Main.getInstance().getConfig().getStringList("Commands.rules.rule-list")) {
                        ruleList.append(rule).append("\n");
                    }
                    s.sendMessage(Format.placeholder((Player) s, String.valueOf(ruleList)));
                }
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        if (label.equalsIgnoreCase("help")) {
            if (s.hasPermission("smp.help")) {
                if (Main.getInstance().getConfig().getString("Commands.help.method").equalsIgnoreCase("GUI")) {
                    if (s instanceof Player) {
                        GuiCreator.generator((Player) s, Main.getInstance().getConfig().getString("Commands.help.gui-name"));
                    } else {
                        Messages.prefix(s, Lang.getLangConfig().getString("Player-Only-Command"));
                    }
                }

                if (Main.getInstance().getConfig().getString("Commands.help.method").equalsIgnoreCase("CHAT")) {
                    StringBuilder helpList = new StringBuilder();
                    for (String help : Main.getInstance().getConfig().getStringList("Commands.help.help-list")) {
                        helpList.append(help).append("\n");
                    }
                    if (s instanceof Player) {
                        s.sendMessage(Format.placeholder((Player) s, String.valueOf(helpList)));
                    } else {
                        s.sendMessage(Format.color(String.valueOf(helpList)));
                    }

                }
            }
        }

        return false;
    }

}
