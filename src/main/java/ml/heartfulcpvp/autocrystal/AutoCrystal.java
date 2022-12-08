package ml.heartfulcpvp.autocrystal;

import ml.heartfulcpvp.autocrystal.listeners.BlockPlaceEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoCrystal extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockPlaceEventListener(), this);
    }
}
