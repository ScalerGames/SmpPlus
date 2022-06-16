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
                    Messages.prefix(s, "&9You are located at: " + "&3X:" + p.getLocation().getBlockX() + " &3Y:" + p.getLocation().getBlockY() + " &3Z:" + p.getLocation().getBlockZ() + " &9in world: &3" + p.getWorld().getName().toUpperCase());
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
