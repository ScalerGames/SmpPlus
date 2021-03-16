package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

public class onMobDeath implements Listener {

    @EventHandler
    public void onMobDeath(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {

            if (Main.getInstance().getConfig().contains("Events.MobDeath." + event.getEntity().getType().toString().toUpperCase())) {

                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity le = (LivingEntity) event.getEntity();

                    if (event.getDamage() > le.getHealth()) {

                        Player p = (Player) event.getDamager();

                                Bukkit.broadcastMessage(Format.color(Main.getInstance().getConfig().getString("Events.MobDeath." + event.getEntity().getType().toString().toUpperCase() + ".message")
                                .replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName()).replace("{world}", p.getWorld().toString())));

                    }

                }

            }

        }

    }

}
