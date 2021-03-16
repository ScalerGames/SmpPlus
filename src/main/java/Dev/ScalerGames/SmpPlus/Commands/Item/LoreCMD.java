package Dev.ScalerGames.SmpPlus.Commands.Item;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Methods.Conditions;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Int;
import Dev.ScalerGames.SmpPlus.Utils.StringJoin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LoreCMD implements CommandExecutor {

    FileConfiguration config = Main.getInstance().getConfig();

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("itemlore")) {

            if (s instanceof Player) {

                Player p = (Player) s;

                if (p.hasPermission("smp.itemlore")) {

                    if (args.length == 0 || args.length == 1) {
                        s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /itemlore [add|set|remove] [lore]"));
                    }

                    if (args.length >= 2) {

                        if (args[0].equalsIgnoreCase("add")) {

                            if (!(p.getItemInHand().getType().isAir())) {

                                if (Conditions.check(label, p)) {

                                    ItemStack item = p.getInventory().getItemInMainHand();
                                    ItemMeta meta = item.getItemMeta();
                                    if (meta.hasLore()) {
                                        List<String> lore = meta.getLore();
                                        lore.add(Format.color(StringJoin.arrays(args, 1)));
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        p.sendMessage(Format.color(config.getString("Prefix") + "&2Add the lore line: &r" + StringJoin.arrays(args, 1)));
                                    } else {
                                        List<String> lore = new ArrayList<String>();
                                        lore.add(Format.color(StringJoin.arrays(args, 1)));
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        p.sendMessage(Format.color(config.getString("Prefix") + "&2Add the lore line: &r" + StringJoin.arrays(args, 1)));
                                    }

                                }


                            } else {
                                s.sendMessage(Format.color(config.getString("Prefix") + "&cYou must be holding an item"));
                            }

                        }

                        if (args[0].equalsIgnoreCase("set")) {

                            if (args.length >= 3) {

                                if (!(p.getItemInHand().getType().isAir())) {

                                    if (Int.isInt(args[1])) {

                                        ItemStack item = p.getInventory().getItemInMainHand();
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta.hasLore()) {
                                            List<String> lore = meta.getLore();
                                            if (lore.size() >= Integer.parseInt(args[1])) {
                                                lore.set(Integer.parseInt(args[1]), Format.color(StringJoin.arrays(args, 2)));
                                                meta.setLore(lore);
                                                item.setItemMeta(meta);
                                                p.sendMessage(Format.color(config.getString("Prefix") + "&2Set line " + args[1] + " to: &r" + StringJoin.arrays(args, 2)));
                                            } else {
                                                s.sendMessage(Format.color(config.getString("Prefix") + "&cLine either empty or invalid"));
                                            }
                                        } else {
                                            List<String> lore = new ArrayList<String>();
                                            if (lore.size() >= Integer.parseInt(args[1])) {
                                                lore.set(Integer.parseInt(args[1]), Format.color(StringJoin.arrays(args, 2)));
                                                meta.setLore(lore);
                                                item.setItemMeta(meta);
                                                p.sendMessage(Format.color(config.getString("Prefix") + "&2Set line " + args[1] + " to: &r" + StringJoin.arrays(args, 2)));
                                            } else {
                                                s.sendMessage(Format.color(config.getString("Prefix") + "&cLine either empty or invalid"));
                                            }
                                        }

                                    } else {
                                        s.sendMessage(Format.color(config.getString("Prefix") + "&cInvalid Int"));
                                    }

                                } else {
                                    s.sendMessage(Format.color(config.getString("Prefix") + "&cYou must be holding an item"));
                                }

                            } else {
                                s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /itemlore set [line] [lore]"));
                            }


                        }

                        if (args[0].equalsIgnoreCase("remove")) {

                            if (!(p.getItemInHand().getType().isAir())) {

                                if (Int.isInt(args[1])) {

                                    ItemStack item = p.getItemInHand();
                                    ItemMeta meta = item.getItemMeta();
                                    if (meta.hasLore()) {
                                        List<String> lore = meta.getLore();
                                        if (lore.size() >= Integer.parseInt(args[1])) {
                                            lore.remove(Integer.parseInt(args[1]));
                                            meta.setLore(lore);
                                            item.setItemMeta(meta);
                                            s.sendMessage(Format.color(config.getString("Prefix") + "&2Removed line " + args[1] + " of lore"));
                                        } else {
                                            s.sendMessage(Format.color(config.getString("Prefix") + "&cLine either empty or Invalid"));
                                        }
                                    } else {
                                        List<String> lore = new ArrayList<String>();
                                        lore.remove(Integer.parseInt(args[1]));
                                        if (lore.size() >= Integer.parseInt(args[1])) {
                                            meta.setLore(lore);
                                            item.setItemMeta(meta);
                                            s.sendMessage(Format.color(config.getString("Prefix") + "&2Removed line " + args[1] + " of lore"));
                                        } else {
                                            s.sendMessage(Format.color(config.getString("Prefix") + "&cLine either empty or Invalid"));
                                        }

                                    }

                                } else {
                                    s.sendMessage(Format.color(config.getString("Prefix") + "&cInvalid Int"));
                                }

                            }

                        }

                        if(!(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("set"))) {
                            s.sendMessage(Format.color(config.getString("Prefix") + "&cUsage: /itemlore [add|set|remove] [lore]"));
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
