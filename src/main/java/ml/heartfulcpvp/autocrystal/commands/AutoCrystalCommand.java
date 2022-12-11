package ml.heartfulcpvp.autocrystal.commands;

import ml.heartfulcpvp.autocrystal.AutoCrystal;
import ml.heartfulcpvp.autocrystal.playerdata.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AutoCrystalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            var player = (Player) sender;
            if (PlayerData.getPlayerData().isEnabled(player)) {
                PlayerData.getPlayerData().setEnabled(player, false);
                AutoCrystal.sendInfoMessage(player, "Disabled AutoCrystal.");
            } else {
                PlayerData.getPlayerData().setEnabled(player, true);
                AutoCrystal.sendInfoMessage(player, "Enabled AutoCrystal.");
            }
            return true;
        }
        return false;
    }
}
