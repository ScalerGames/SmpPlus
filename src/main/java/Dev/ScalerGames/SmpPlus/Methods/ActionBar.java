package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void bar(Player p, String path) {

        if (Main.getInstance().getConfig().contains("Events." + path + ".action-bar.msg")) {


            //Multiple Action Bar's
            if (Main.getInstance().getConfig().isList("Events." + path + ".action-bar.msg")) {

                for (String message : Main.getInstance().getConfig().getStringList("Events." + path + ".action-bar.msg")) {

                    //Set the action bar on for longer
                    if (Main.getInstance().getConfig().contains("Events." + path + ".action-bar.time")) {

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
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(p, message)));
                                }
                            }
                        }, 0, 20*1);

                    } else {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(p, message)));
                    }

                }

            }

            //Single Action Bar
            else if (Main.getInstance().getConfig().isString("Events." + path + ".action-bar.msg")) {

                //Set the action bar on for longer
                if (Main.getInstance().getConfig().contains("Events." + path + ".action-bar.time")) {

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
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".action-bar.msg"))));
                            }
                        }
                    }, 0, 20*1);

                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".action-bar.msg"))));
                }

            }

        }

    }

}
