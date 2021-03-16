package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.entity.Player;

public class Title {

    public static void title(Player p, String path) {

        if (Main.getInstance().getConfig().contains("Events." + path + ".title")) {
            if (Main.getInstance().getConfig().contains("Events." + path + ".sub-title")) {
                p.sendTitle(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".title")),
                        Main.getInstance().getConfig().getString(Format.placeholder(p,"Events." + path + ".sub-title")));
            } else {
                p.sendTitle(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".title")), null);
            }

        }

    }

}
