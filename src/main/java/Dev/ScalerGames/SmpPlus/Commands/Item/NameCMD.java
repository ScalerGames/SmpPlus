package Dev.ScalerGames.SmpPlus.Commands.Item;

import Dev.ScalerGames.SmpPlus.Commands.Conditions;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import Dev.ScalerGames.SmpPlus.Utils.Tools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NameCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("itemname")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (p.hasPermission("smp.itemname")) {
                    if (args.length >= 1) {

                        if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                            ItemStack item = p.getInventory().getItemInMainHand();
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(Format.color(Tools.join(args, 0)));
                            item.setItemMeta(meta);
                            Messages.prefix(s, "&2Set item name to:&r "
                                    + Tools.join(args, 0));
                        } else {
                            Messages.prefix(s, "&cYou must have an item in your hand");
                        }

                    } else {
                        Messages.prefix(s, "&cUsage: /itemname <name>");
                    }
                } else {
                    Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
                }
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Player-Only-Command"));
            }
        }

        return false;
    }

}
