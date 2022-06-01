package Dev.ScalerGames.SmpPlus.Utils;

import Dev.ScalerGames.SmpPlus.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class Methods {

    public static void message(Player p, String path) {
        if (Main.getInstance().getConfig().contains("Events." + path + ".direct-message", true)) {
            String msg = Main.getInstance().getConfig().getString("Events." + path + ".direct-message");
            if (msg.contains("<hover>")) {
                String[] newMesssage =  msg.split(Pattern.quote("<hover>"));
                TextComponent text = new TextComponent(Format.placeholder(p, newMesssage[0]));
                text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Format.placeholder(p, newMesssage[1])).create()));
                p.spigot().sendMessage(text);
            } else {
                p.sendMessage(Format.placeholder(p, msg));
            }
        }
    }

    public static void bar(Player p, String path) {
        if (Main.getInstance().getConfig().contains("Events." + path + ".action-bar.msg")) {
            if (Main.getInstance().getConfig().contains("Events." + path + ".action-bar.time") && Main.getInstance().getConfig().isInt("Events." + path + ".action-bar.time")) {
                int task = 0;
                int finalTask = task;
                task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                    int time = 0;
                    @Override
                    public void run() {
                        if (time == Main.getInstance().getConfig().getInt("Events." + path + ".action-bar.time")) {
                            Bukkit.getScheduler().cancelTask(finalTask);
                        } else {
                            time++;
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + "action-bar.msg"))));
                        }
                    }
                }, 0, 20*1);
            } else {
                Main.getInstance().getLogger().info(Format.color("&cThe event using the action bar for " + p.getDisplayName() + " failed to send"));
            }
        }
    }

    public static void title(Player p, String path) {
        if (Main.getInstance().getConfig().contains("Events." + path + ".title")) {
            if (Main.getInstance().getConfig().contains("Events." + path + ".sub-title")) {
                p.sendTitle(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".title")),
                        Main.getInstance().getConfig().getString(Format.placeholder(p, "Events." + path + ".sub-title")));
            } else {
                p.sendTitle(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".title")), null);
            }
        }
    }

    public static void consoleCMD(Player p, String path) {
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
