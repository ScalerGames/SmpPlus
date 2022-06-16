package Dev.ScalerGames.SmpPlus.Listeners;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onClickBlock implements Listener {

    @EventHandler
    public void onClickBlock(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getBlockData() instanceof Directional) {
                if (event.getClickedBlock().getType().toString().contains("GLAZED_TERRACOTTA")) {
                    if (event.getItem() != null && check(event.getItem())) {
                        Directional d = (Directional) event.getClickedBlock().getBlockData();
                        if (d.getFacing().equals(BlockFace.NORTH)) {
                            d.setFacing(BlockFace.EAST);
                        }
                        else if (d.getFacing().equals(BlockFace.EAST)) {
                            d.setFacing(BlockFace.SOUTH);
                        }
                        else if (d.getFacing().equals(BlockFace.SOUTH)) {
                            d.setFacing(BlockFace.WEST);
                        }
                        else if (d.getFacing().equals(BlockFace.WEST)) {
                            d.setFacing(BlockFace.NORTH);
                        }
                        event.getClickedBlock().setBlockData(d);
                    }
                }
            }
        }
    }

    public boolean check(ItemStack item) {
        if (item.getType().equals(Material.matchMaterial(Main.getInstance().getConfig().getString("TerracottaRotator.item")))) {
            if (item.getItemMeta() != null && item.getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("TerracottaRotator.display_name"))) {
                return true;
            }
        }
        return false;
    }

}
