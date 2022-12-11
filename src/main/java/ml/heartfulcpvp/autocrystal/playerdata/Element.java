package ml.heartfulcpvp.autocrystal.playerdata;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

public class Element {
    // jsonのライブラリ入れるのだるいから我流フォーマットゆるして:pleading_face:
    private UUID uuid;
    private boolean isEnabled;
    private float minDamage;

    public Element(UUID uuid, boolean isEnabled, float minDamage) {
        this.uuid = uuid;
        this.isEnabled = isEnabled;
        this.minDamage = minDamage;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public float getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(float minDamage) {
        this.minDamage = minDamage;
    }

    public String build() {
        return uuid + ":" + isEnabled + ":" + minDamage;
    }

    public static Element fromLine(String line) {
        var elems = line.split(":");
        return new Element(UUID.fromString(elems[0]), Boolean.getBoolean(elems[1]), Float.parseFloat(elems[2]));
    }
}
