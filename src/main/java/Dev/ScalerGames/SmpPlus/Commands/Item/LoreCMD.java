package Dev.ScalerGames.SmpPlus.Commands.Item;

import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import Dev.ScalerGames.SmpPlus.Utils.Tools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LoreCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("itemlore")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (s.hasPermission("smp.itemlore")) {

                    if (args.length >= 2) {

                        if (args[0].equalsIgnoreCase("add")) {
                            if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                                //ADD CONDITIONS IN
                                ItemStack item = p.getInventory().getItemInMainHand();
                                ItemMeta meta = item.getItemMeta();
                                if (meta.hasLore()) {
                                    List<String> lore = meta.getLore();
                                    lore.add(Format.color(Tools.join(args, 1)));
                                    meta.setLore(lore);
                                } else {
                                    List<String> lore = new ArrayList<String>();
                                    lore.add(Format.color(Tools.join(args, 1)));
                                    meta.setLore(lore);
                                }
                                item.setItemMeta(meta);
                                Messages.prefix(s, "&2Added the lore line:&r "
                                        + Tools.join(args, 1));
                            } else {
                                Messages.prefix(s, "&cYou must have an item in your hand!");
                            }
                        }

                        else if (args[0].equalsIgnoreCase("set")) {
                            if (args.length >= 3) {
                                if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                                    if (Tools.isInt(args[1]) && (p.getInventory().getItemInMainHand().getItemMeta().getLore() != null)) {

                                        ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                                        if (meta.hasLore()) {
                                            if (Integer.parseInt(args[1]) <= p.getInventory().getItemInMainHand().getItemMeta().getLore().size()) {
                                                List<String> lore = meta.getLore();
                                                lore.set(Integer.parseInt(args[1]) - 1, Format.color(Tools.join(args,2)));
                                                meta.setLore(lore);
                                                p.getInventory().getItemInMainHand().setItemMeta(meta);
                                                Messages.prefix(s, "&2Set the lore line " + args[1] + " to:&r "
                                                        + Tools.join(args, 2));
                                            } else {
                                                Messages.prefix(s, "&cThe item dose not contain lore line: " + args[1]);
                                            }
                                        } else {
                                            Messages.prefix(s, "&cYou can't set an empty lore line");
                                        }
                                    } else {
                                        Messages.prefix(s,"&cYou need a valid lore line or that item dose not have a lore");
                                    }
                                } else {
                                    Messages.prefix(s, "&cYou must have an item in your hand");
                                }
                            } else {
                                Messages.prefix(s, "&cUsage: /itemlore set <line> <lore>");
                            }
                        }

                        else if (args[0].equalsIgnoreCase("remove")) {
                            if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                                if (Tools.isInt(args[1])) {
                                    ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                                    if (meta.hasLore()) {
                                        if (Integer.parseInt(args[1]) <= p.getInventory().getItemInMainHand().getItemMeta().getLore().size()) {
                                            List<String> lore = meta.getLore();
                                            lore.remove(Integer.parseInt(args[1]) - 1);
                                            meta.setLore(lore);
                                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                                            Messages.prefix(s, "&2Removed line " + args[1] + " of the lore");
                                        } else {
                                            Messages.prefix(s, "&cThe item dose not contain lore line: " + args[1]);
                                        }
                                    } else {
                                        Messages.prefix(s, "&cThe lore is already empty");
                                    }
                                } else {
                                    Messages.prefix(s, "&cInvalid lore line");
                                }
                            } else {
                                Messages.prefix(s, "&cYou must have an item in your hand");
                            }
                        }

                        else {
                            Messages.prefix(s, "&cUsage: /itemlore [add|set|remove] <lore>");
                        }

                    } else {
                        Messages.prefix(s, "&cUsage: /itemlore [add|set|remove] <lore>");
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

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("add"); arguments.add("set");
            arguments.add("remove");
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
