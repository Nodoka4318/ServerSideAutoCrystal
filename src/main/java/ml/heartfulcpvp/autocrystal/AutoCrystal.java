package ml.heartfulcpvp.autocrystal;

import com.comphenix.protocol.PacketType;
import ml.heartfulcpvp.autocrystal.commands.AutoCrystalCommand;
import ml.heartfulcpvp.autocrystal.listeners.PlayerInteractEventListener;
import ml.heartfulcpvp.autocrystal.playerdata.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AutoCrystal extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            PlayerData.createPlayerDataFile();
            PlayerData.initPlayerData();
            getLogger().info("Loaded " + PlayerData.getPlayerData().getPlayerDataList().size() + " player's data.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(this), this);
        getCommand("autocrystal").setExecutor(new AutoCrystalCommand());
    }

    @Override
    public void onDisable() {
        try {
            PlayerData.write();
            getLogger().info("Saved " + PlayerData.getPlayerData().getPlayerDataList().size() + " player's data.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendInfoMessage(Player player, String message) {
        player.sendMessage("&c&l[&f&lAutoCrystal&c&l] &f" + message);
    }
}
