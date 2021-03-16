package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.entity.Player;

public class Message {

    public static void message(Player p, String path) {
        if (Main.getInstance().getConfig().contains("Events." + path + ".direct-message", true)) {
            p.sendMessage(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".direct-message")));
        }
    }

}
