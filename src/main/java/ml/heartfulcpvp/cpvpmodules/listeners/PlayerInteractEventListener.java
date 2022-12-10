package ml.heartfulcpvp.cpvpmodules.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PlayerInteractEventListener implements Listener {
    private final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();
    private Plugin plugin;

    public PlayerInteractEventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            var block = e.getClickedBlock().getType();
            if (!(block == Material.OBSIDIAN || block == Material.BEDROCK))
                return;

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                public void run(){
                    List<Entity> entities = e.getPlayer().getNearbyEntities(5, 5, 4);

                    for (Entity entity : entities) {
                        if (entity.getType() == EntityType.ENDER_CRYSTAL) {
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
                }
            }, 0);
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        plugin.getLogger().warning(e.getDamager().getType().name() + " " + e.getEntity().getType().name());
    }
}
