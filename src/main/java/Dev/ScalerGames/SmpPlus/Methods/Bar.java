package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class Bar {

    public static void bar(Player p, String path) {

        if (Main.getInstance().getConfig().contains("Events." + path + ".action-bar", true)) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(p, Main.getInstance().getConfig().getString("Events." + path + ".action-bar"))));
        }

    }


}
