package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command {

    public static void console(Player p, String path) {

        if (Main.getInstance().getConfig().contains("Events." + path + ".console-cmd")) {

            for (String cmd : Main.getInstance().getConfig().getStringList("Events." + path + ".console-cmd")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("{player}", p.getName()));
            }

        }

    }

    public static void playerCMD(Player p, String path) {

        if (Main.getInstance().getConfig().contains("Events." + path + ".player-cmd")) {

            for (String cmd : Main.getInstance().getConfig().getStringList("Events." + path + ".player-cmd")) {
                Bukkit.dispatchCommand(p, cmd.replace("{player}", p.getName()));
            }

        }

    }

}
