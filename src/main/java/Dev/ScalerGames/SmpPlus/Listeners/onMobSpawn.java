package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class onMobSpawn implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Shulkers.Respawn")) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Enderman) {
                if (entity.getWorld().getEnvironment() == World.Environment.THE_END && (entity.getLocation().getBlock().getBiome() == Biome.END_HIGHLANDS
                        || entity.getLocation().getBlock().getBiome() == Biome.END_MIDLANDS) && EndCity(entity.getLocation().getBlock())) {

                    if (entity.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType().toString().contains("PURPUR") || entity.getLocation().getBlock().getType().toString().contains("PURPUR")) {
                        double rand = Math.random();
                        if (rand <= Main.getInstance().getConfig().getDouble("Shulkers.Chance")) {
                            event.setCancelled(true);
                            entity.getWorld().spawn(entity.getLocation(), Shulker.class);
                        }
                    }
                }
            }
        }
    }

    public boolean EndCity(Block block) {
        int count = 0;
        Location loc = block.getLocation();
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(0.0D, 0.0D, 1.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(0.0D, 0.0D, 1.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(0.0D, 0.0D, 1.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        if (count >= 3)
            return true;
        loc = loc.getBlock().getLocation().subtract(0.0D, 1.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(0.0D, 0.0D, 1.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(0.0D, 0.0D, 1.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().subtract(0.0D, 0.0D, 1.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        loc = loc.getBlock().getLocation().add(1.0D, 0.0D, 0.0D);
        if (loc.getBlock().getType().toString().contains("PURPUR"))
            count++;
        if (count >= 3)
            return true;
        return false;
    }

}
