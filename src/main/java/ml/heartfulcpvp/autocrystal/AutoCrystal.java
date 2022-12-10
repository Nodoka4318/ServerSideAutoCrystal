package ml.heartfulcpvp.autocrystal;

import ml.heartfulcpvp.autocrystal.listeners.PlayerInteractEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoCrystal extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("やったぜ");
        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(this), this);
    }
}
