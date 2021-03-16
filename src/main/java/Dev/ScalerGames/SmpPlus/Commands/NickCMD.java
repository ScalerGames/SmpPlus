package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Methods.Conditions;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class NickCMD implements CommandExecutor {

    FileConfiguration config = Main.getInstance().getConfig();

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("nickname")) {

            if (s instanceof Player) {

                Player p = (Player) s;

                if (p.hasPermission("smp.nickname")) {

                    if (args.length == 0) {
                        p.sendMessage(Format.color(Main.getInstance().getConfig().getString("Prefix") + "&cUsage: /nickname [name]"));
                    }

                    if (args.length >= 1) {

                        String name = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', args[0]));

                        if (name.equalsIgnoreCase(p.getName())) {

                            if (Conditions.check("nickname", p)) {
                                if (Bukkit.getPluginManager().getPlugin("Essentials") != null || Bukkit.getPluginManager().getPlugin("EssentialsX") != null) {
                                    Essentials essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
                                    User user = essentials.getUserMap().getUser(p.getName());
                                    user.setNickname(Format.color(args[0]));
                                } else {
                                    p.setDisplayName(Format.color(args[0]));
                                }
                                p.sendMessage(Format.color(config.getString("Prefix") + "&2Your name is now: " + args[0]));
                                Data.getDataConfig().set(p.getUniqueId() + ".display-name", args[0]);
                                Data.saveData();
                            } else {
                                p.sendMessage(Format.color(config.getString("Prefix") + config.getString("Commands." + label + ".deny-message")));
                            }

                        } else {
                            p.sendMessage(Format.color(Main.getInstance().getConfig().getString("Prefix") + "&cMust Contain Your Name"));
                        }


                    }

                }

            }

        }

        return false;
    }

}
