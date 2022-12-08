package ml.heartfulcpvp.autocrystal.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceEventListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        var player = e.getPlayer();

        if (e.getBlockPlaced().getType() == Material.END_CRYSTAL) {
            var crystal = (EnderCrystal) e.getBlockPlaced();
            // TODO: ダメージ計算、距離計算、有効か無効か
            player.attack(crystal);
        }
    }
}
