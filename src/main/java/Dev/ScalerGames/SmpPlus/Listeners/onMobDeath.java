package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onMobDeath implements Listener {

    @EventHandler
    public void onMobDeath(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (Main.getInstance().getConfig().contains("Events.MobDeath." + event.getEntity().getType().toString().toUpperCase())) {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity le = (LivingEntity) event.getEntity();
                    if (event.getDamage() > le.getHealth()) {
                        Bukkit.broadcastMessage(Format.placeholder((Player) event.getDamager(), Main.getInstance().getConfig().getString("Event.MobDeath"
                                + event.getEntity().getType().toString().toUpperCase() + ".message")));
                    }
                }
            }
        }
    }

}
