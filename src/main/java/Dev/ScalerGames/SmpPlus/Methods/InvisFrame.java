package Dev.ScalerGames.SmpPlus.Methods;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.List;

public class InvisFrame {

    static FileConfiguration config = Main.getInstance().getConfig();

    public static void setFrame(Player p) {

        List<Entity> entityList = p.getNearbyEntities(config.getDouble("ItemFrames.SetRange.X"),
                config.getDouble("ItemFrames.SetRange.Y"), config.getDouble("ItemFrames.SetRange.Z"));

        for (Entity current : entityList) {
            if (current instanceof ItemFrame) {
                ((ItemFrame) current).setVisible(false);
                current.setGlowing(true);
            }
        }

    }

}
