package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Int;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static Dev.ScalerGames.SmpPlus.Main.econ;

public class Conditions {

    public static boolean check(String label, Player p) {

        if (Main.getInstance().getConfig().contains("Commands." + label + ".conditions", true)) {

            for (String condition : Main.getInstance().getConfig().getStringList("Commands." + label + ".conditions")) {

                if (condition.contains("MONEY")) {
                    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
                        String[] list = condition.split(":");
                        String amount = list[1];
                        if (Int.isInt(amount)) {
                            if (econ.getBalance(p) >= Integer.parseInt(amount)) {
                                if (list.length >= 3) {
                                    String type = list[2];
                                    if (type.equalsIgnoreCase("TAKE")) {
                                        econ.withdrawPlayer(p, Integer.parseInt(amount));
                                        return true;
                                    }
                                    if (type.equalsIgnoreCase("KEEP")) {
                                        return true;
                                    }
                                } else {
                                    return true;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                            return true;
                        }
                    } else {
                        Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                        return true;
                    }
                }

                if (condition.contains("ITEM")) {

                    String[] list = condition.split(":");
                    String material = list[1];
                    String amount = list[2];

                    if (Int.isInt(amount)) {

                        try {
                            ItemStack itemStack = new ItemStack(Material.matchMaterial(material));

                            if (p.getInventory().containsAtLeast(itemStack, Integer.parseInt(amount))) {

                                if (list.length >= 4) {
                                    String type = list[3];
                                    if (type.equalsIgnoreCase("TAKE")) {
                                        p.getInventory().removeItem(new ItemStack(Material.matchMaterial(material), Integer.parseInt(amount)));
                                        p.updateInventory();
                                        return true;
                                    }

                                    if (type.equalsIgnoreCase("KEEP")) {
                                        return true;
                                    }

                                } else {
                                    return true;
                                }
                            } else {
                                return false;
                            }

                        } catch (Exception e) {
                            Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                            return true;
                        }

                    } else {
                        Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                        return true;
                    }

                }

                if (condition.contains("PERMISSION")) {

                    String[] list = condition.split(":");
                    String perm = list[1];

                    if (p.hasPermission(perm)) {
                        return true;
                    } else {
                        return false;
                    }

                }

                //if (condition.contains("EXP")) {

                    //String[] list = condition.split(":");

                    //if (list.length >= 3) {

                        //String amount = list[1];

                        //if (Int.isInt(amount)) {

                            //String type = list[2];

                            //if (p.getExpToLevel() >= Integer.parseInt(amount)) {
                                //if (type.equalsIgnoreCase("TAKE")) {
                                    //p.giveExpLevels(-Integer.parseInt(amount));
                                    //return true;
                                //}

                                //if (type.equalsIgnoreCase("KEEP")) {
                                    //return true;
                                //}

                            //} else {
                                //return false;
                            //}

                        //} else {
                            //Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                            //return true;
                        //}

                    //} else {
                        //Main.getInstance().getLogger().info(Format.color("&cCondition <" + condition + "> for command " + label + " is invalid"));
                        //return true;
                    //}

                //}

            }

        } else {
            return true;
        }
        return true;
    }

}
