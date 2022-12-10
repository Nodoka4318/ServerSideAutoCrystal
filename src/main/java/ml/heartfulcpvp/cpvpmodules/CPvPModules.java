package ml.heartfulcpvp.cpvpmodules;

import ml.heartfulcpvp.cpvpmodules.listeners.PlayerInteractEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CPvPModules extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("やったぜ");
        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(this), this);
    }
}
