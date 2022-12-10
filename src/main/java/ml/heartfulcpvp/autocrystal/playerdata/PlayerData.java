package ml.heartfulcpvp.autocrystal.playerdata;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.List;

public class PlayerData {
    private static final File folder = new File("./plugins/ServerSideAutoCrystal");
    private static final File playerData = new File("./plugins/ServerSideAutoCrystal/playerdata.txt");

    private String uuid;
    private boolean isEnabled;
    private int minDamage;

    public PlayerData(String uuid, boolean isEnabled, int minDamage) {
        this.uuid = uuid;
        this.isEnabled = isEnabled;
        this.minDamage = minDamage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public static void createPlayerDataFile() throws IOException {
        if (!folder.exists())
            folder.mkdir();
        if (!playerData.exists())
            Files.write(playerData.toPath(), List.of(""), StandardCharsets.UTF_8);
    }

    public String build() {
        return uuid + ":" + isEnabled + ":" + minDamage;
    }

    public static PlayerData fromLine(String line) {
        var elems = line.split(":");
        return new PlayerData(elems[0], Boolean.getBoolean(elems[1]), Integer.getInteger(elems[2]));
    }
}
