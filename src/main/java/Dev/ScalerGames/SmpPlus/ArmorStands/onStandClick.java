package Dev.ScalerGames.SmpPlus.ArmorStands;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class onStandClick implements Listener {

    public static ArmorStand as;

    public void onStandClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            Player p = (Player) event.getPlayer();
            if (p.hasPermission("smp.stand.editor")) {
                as = (ArmorStand) event.getRightClicked();
            }
        }
    }

}
