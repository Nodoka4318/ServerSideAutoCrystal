package ml.heartfulcpvp.autocrystal.listeners;

import ml.heartfulcpvp.autocrystal.playerdata.PlayerData;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.protocol.game.PacketPlayInUseEntity;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class PlayerInteractEventListener implements Listener {
    private Plugin plugin;

    public PlayerInteractEventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!PlayerData.getPlayerData().isEnabled(e.getPlayer()))
            return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            var block = Objects.requireNonNull(e.getClickedBlock()).getType();
            if (!(block == Material.OBSIDIAN || block == Material.BEDROCK))
                return;

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                List<Entity> entities = e.getPlayer().getNearbyEntities(4, 4, 3);

                for (Entity entity : entities) {
                    if (entity.getType() == EntityType.ENDER_CRYSTAL) {
                        var top = entity.getLocation().getBlock().getRelative(BlockFace.UP);
                        if (top.getType() != Material.AIR) {
                            entity.remove();
                            e.setCancelled(true);
                            return;
                        }

                        var below = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);
                        if (e.getClickedBlock().equals(below)) {
                            var p = (CraftPlayer) e.getPlayer();
                            var connection = p.getHandle().b;

                            // 難読化解読無理ゲーすぎて
                        }
                    }
                }
            }, 0);
        }
    }
}