package Dev.ScalerGames.SmpPlus.Commands;

import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoordinatesCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("coordinates")) {
            if (s.hasPermission("smp.coordinates")) {
                if (s instanceof Player) {
                    Player p = (Player) s;
                    Messages.prefix(s, "&9You are located at: " + "&9&lX:" + p.getLocation().getBlockX() + "&9&lY:" + p.getLocation().getBlockY() + "&9&lZ:" + p.getLocation().getBlockZ() + " &9in world &9&n" + p.getWorld().toString().toLowerCase());
                } else {
                    Messages.prefix(s, Lang.getLangConfig().getString("Player-Only-Command"));
                }
            } else {
                Messages.prefix(s, Lang.getLangConfig().getString("Invalid-Permission"));
            }
        }

        return false;
    }

}
