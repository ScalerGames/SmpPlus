package Dev.ScalerGames.SmpPlus.Utils;

import Dev.ScalerGames.SmpPlus.Files.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messages {

    public static void prefix(CommandSender s, String msg) {
        if (s instanceof Player) {
            s.sendMessage(Format.placeholder((Player) s, Lang.getLangConfig().getString("Prefix") + msg));
        } else {
            s.sendMessage(Format.color(Lang.getLangConfig().getString("Prefix") + msg));
        }
    }

}
