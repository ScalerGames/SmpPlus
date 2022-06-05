package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static Dev.ScalerGames.SmpPlus.Main.econ;

public class Conditions {

    //This feature will be reworked and re added in a future update

    public static boolean check(String label, Player p) {
        if (Main.getInstance().getConfig().contains("Commands." + label + ".conditions", true)) {
            for (String condition : Main.getInstance().getConfig().getStringList("Commands." + label + ".conditions")) {

                if (condition.contains("MONEY")) {
                    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
                        String[] list = condition.split(":");
                        if (Tools.isInt(list[1])) {
                            if (econ.getBalance(p) >= Integer.parseInt(list[1])) {
                                if (list.length >= 3) {
                                    if (list[2].equalsIgnoreCase("TAKE")) {
                                        econ.withdrawPlayer(p, Integer.parseInt(list[1]));
                                        return true;
                                    }
                                    if (list[2].equalsIgnoreCase("KEEP")) {
                                        return true;
                                    }
                                }
                            } else {
                                return false;
                            }
                        } else {
                            Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                        }
                    } else {
                        Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                    }
                }

                if (condition.contains("ITEM")) {
                    String[] list = condition.split(":");
                    if (Tools.isInt(list[2]) && list.length == 4) {
                        try {
                            ItemStack item = new ItemStack(Material.matchMaterial(list[1]));
                            if (p.getInventory().containsAtLeast(item, Integer.parseInt(list[2]))) {
                                if (list[3].equalsIgnoreCase("TAKE")) {
                                    p.getInventory().removeItem(new ItemStack(Material.matchMaterial(list[1]), Integer.parseInt(list[2])));
                                    p.updateInventory();
                                    return true;
                                }
                                if (list[3].equalsIgnoreCase("KEEP")) {
                                    return true;
                                }
                            } else {
                                return false;
                            }
                        } catch (NullPointerException e) {
                            Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                        }
                    } else {
                        Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                    }
                }

                if (condition.contains("PERMISSION")) {
                    String[] list = condition.split(":");
                    if (list.length == 2) {
                        if (p.hasPermission(list[1])) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                    }
                }

                if (condition.contains("EXP")) {
                    String[] list = condition.split(":");
                    if (list.length == 3 && Tools.isInt(list[1])) {
                        if (p.getExpToLevel() >= Integer.parseInt(list[1])) {
                            if (list[2].equalsIgnoreCase("TAKE")) {
                                p.giveExpLevels(-Integer.parseInt(list[1]));
                                return true;
                            }
                            if (list[2].equalsIgnoreCase("KEEP")) {
                                return true;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                    }
                }

            }
        }
        return true;
    }

}
