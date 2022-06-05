package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class MerchantCMD implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("merchant")) {
            if (s.hasPermission("smp.merchant")) {

                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("summon") && args.length >= 2) {
                        if (Gui.getGuiConfig().getConfigurationSection("Merchants.").contains(args[1])) {
                            List<MerchantRecipe> recipes = new ArrayList<>();

                            for (String trade : Gui.getGuiConfig().getConfigurationSection("Merchants." + args[1] + ".trades").getKeys(false)) {
                                recipes.add(addTrade(args, trade));
                            }

                            Player p = (Player) s;
                            try {
                                Villager vil = (Villager) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
                                vil.setAdult();
                                vil.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.0);
                                vil.setCustomNameVisible(true);
                                vil.setCustomName(Format.color(Gui.getGuiConfig().getString("Merchants." + args[1] + ".name")));
                                vil.setProfession(Villager.Profession.valueOf(Gui.getGuiConfig().getString("Merchants." + args[1] + ".profession")));
                                vil.setVillagerType(Villager.Type.valueOf(Gui.getGuiConfig().getString("Merchants." + args[1] + ".type")));
                                vil.setVillagerLevel(Gui.getGuiConfig().getInt("Merchants." + args[1] + ".level"));
                                conditions(args, vil);
                                vil.setRecipes(recipes);
                            } catch (Exception e) {
                                Messages.logger("&cThe merchant has an invalid field");
                                Messages.prefix(s, "&cThe merchant has an invalid field");
                            }

                        } else {
                            Messages.prefix(s, "&cInvalid merchant name");
                        }
                    }

                    if (args[0].equalsIgnoreCase("list")) {
                        StringBuilder msg = new StringBuilder();
                        for (String merchants : Gui.getGuiConfig().getConfigurationSection("Merchants.").getKeys(false)) {
                            msg.append(merchants + ", ");
                        }
                        Messages.prefix(s, "&dThe merchants are: &3" + msg);
                    }

                    if (args[0].equalsIgnoreCase("kill") && args.length >= 2) {
                        Player p = (Player) s;
                        List<Entity> entityList = p.getNearbyEntities(5.0, 5.0, 5.0);
                        for (Entity current : entityList) {
                            if (current instanceof Villager && current.getCustomName() != null && current.getCustomName().equals(Format.color(Gui.getGuiConfig().getString("Merchants." + args[1] + ".name")))) {
                                current.remove();
                            }
                        }
                    }

                    if (args[0].equalsIgnoreCase("help")) {
                        s.sendMessage(Format.color("&d/merchant summon <merchant-name>" +
                                "\n&5- Summons a merchant located where the sender of the command is standing\n&d/merchant kill <merchant-name>" +
                                "\n&5- This kills the merchants that's input within a 5 block radius\n&d/merchant list\n&5- Gives a list of all merchants"));
                    }
                } else {
                    Messages.prefix(s, "&cUsage: /merchant [summon|kill|list|help] <merchant-name>");
                }

            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("summon"); arguments.add("kill");
            arguments.add("list"); arguments.add("help");
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

    public MerchantRecipe addTrade(String[] args, String trade) {
        //Add the item for sale
        if (Gui.getGuiConfig().contains("Merchants." + args[1] + ".trades." + trade + ".sell.item")
                && Gui.getGuiConfig().contains("Merchants." + args[1] + ".trades." + trade + ".sell.quantity")) {
            ItemStack item = new ItemStack(Material.valueOf(Gui.getGuiConfig().getString("Merchants." + args[1] + ".trades."
                    + trade + ".sell.item")), Gui.getGuiConfig().getInt("Merchants." + args[1] + ".trades."
                    + trade + ".sell.quantity"));

            MerchantRecipe recipe = new MerchantRecipe(item, Gui.getGuiConfig().getInt("Merchants." + args[1] + ".trades." + trade + ".max-uses"));
            //This is the first payment item
            try {
                ItemStack buy_item = new ItemStack(Material.valueOf(Gui.getGuiConfig().getString("Merchants." + args[1] + ".trades."
                        + trade + ".buy-1.item")), Gui.getGuiConfig().getInt("Merchants." + args[1] + ".trades." + trade + ".buy-1.quantity"));
                recipe.addIngredient(buy_item);
            } catch (Exception e) {
                Messages.logger("&cThe 1st payment item is invalid in the summoned merchant");
            }
            //This is the second payment item
            if (Gui.getGuiConfig().contains("Merchants." + args[1] + ".trades." + trade + ".buy-2")) {
                try {
                    ItemStack buy_item = new ItemStack(Material.valueOf(Gui.getGuiConfig().getString("Merchants." + args[1] + ".trades."
                            + trade + ".buy-2.item")), Gui.getGuiConfig().getInt("Merchants." + args[1] + ".trades." + trade + ".buy-2.quantity"));
                    recipe.addIngredient(buy_item);
                } catch (Exception e) {
                    Messages.logger("&cThe 2nd payment item is invalid in the summoned merchant");
                }
            }

            return recipe;

        } else {
            Messages.logger("&cAn item for sale in the summoned merchant is invalid");
        }
        return null;
    }

    public void conditions(String[] args, Villager vil) {
        //Ai boolean
        if (Gui.getGuiConfig().contains("Merchants." + args[1] + ".ai")) {
            vil.setAI(Gui.getGuiConfig().getBoolean("Merchants." + args[1] + ".ai"));
        }
        //Can pick up items
        if (Gui.getGuiConfig().contains("Merchants." + args[1] + ".pick-up-items")) {
            vil.setCanPickupItems(Gui.getGuiConfig().getBoolean("Merchants." + args[1] + ".pick-up-items"));
        }
        //Set invulnerability
        if (Gui.getGuiConfig().contains("Merchants." + args[1] + ".invulnerable")) {
            vil.setInvulnerable(Gui.getGuiConfig().getBoolean("Merchants." + args[1] + ".invulnerable"));
        }
        //Attributes
        if (Gui.getGuiConfig().contains("Merchants." + args[1] + ".attributes")) {
            for (String attribute : Gui.getGuiConfig().getStringList("Merchants." + args[1] + ".attributes")) {
                try {
                    String[] split = attribute.split(":");
                    vil.getAttribute(Attribute.valueOf(split[0])).setBaseValue(Double.parseDouble(split[1]));
                } catch (Exception e) {
                    Messages.logger("&cThe summoned villager contains an invalid attribute");
                }
            }
        }
    }

}
