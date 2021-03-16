package Dev.ScalerGames.SmpPlus.Commands.Item;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Methods.Conditions;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.StringJoin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NameCMD implements CommandExecutor {

    FileConfiguration config = Main.getInstance().getConfig();

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("itemname")) {

            if (s instanceof Player) {

                Player p = (Player) s;

                if (p.hasPermission("smp.itemname")) {

                    if (args.length == 0) {
                        s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /itemname [name]"));
                    }

                    else if (args.length >= 1) {

                        if (!(p.getItemInHand().getType().isAir())) {

                            if (Conditions.check(label, p)) {

                                ItemStack item = p.getInventory().getItemInMainHand();
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(Format.color(StringJoin.arrays(args, 0)));
                                item.setItemMeta(meta);
                                p.sendMessage(Format.color(config.getString("Prefix") + "&2Set item name too: &r" + StringJoin.arrays(args, 0)));

                            } else {
                                s.sendMessage(Format.color(config.getString("Prefix") + config.getString("Commands.itemname.deny-message")));
                            }

                        } else {
                            s.sendMessage(Format.color(config.getString("Prefix") + "&cYou must have a item in your hand"));
                        }

                    }

                } else {
                    s.sendMessage(Format.color(config.getString("Prefix") + "&cInvalid Permission"));
                }

            } else {
                s.sendMessage(Format.color(config.getString("Prefix") + "&cPlayer only command"));
            }

        }

        return false;
    }

}
