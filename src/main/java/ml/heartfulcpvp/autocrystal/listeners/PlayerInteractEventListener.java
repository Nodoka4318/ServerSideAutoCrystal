package ml.heartfulcpvp.autocrystal.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import ml.heartfulcpvp.autocrystal.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
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
    private final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();
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

                            PacketContainer packet = new PacketContainer(PacketType.Play.Client.USE_ENTITY);

                            packet.getEntityUseActions().write(0, EnumWrappers.EntityUseAction.ATTACK);
                            packet.getModifier().write(0, entity.getEntityId());
                            try {
                                protocol.recieveClientPacket(e.getPlayer(), packet);
                            } catch (IllegalAccessException | InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }, 0);
        }
    }
}