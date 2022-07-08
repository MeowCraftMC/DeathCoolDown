package cx.rain.mc.deathcooldown.data;

import com.google.gson.Gson;
import cx.rain.mc.deathcooldown.data.bean.DeathInfo;
import cx.rain.mc.deathcooldown.data.bean.LastDeathsData;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class CoolDownDataManager {
    private final Gson gson = new Gson();

    private File dir = null;

    private File deathsDataFile = null;
    private LastDeathsData deathsData = null;

    public CoolDownDataManager(File dataDir) {
        dir = dataDir;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        deathsDataFile = new File(dir, "data.json");
        deathsData = new LastDeathsData();

        if (!deathsDataFile.exists()) {
            save();
        }

        deathsData = load(deathsData, deathsDataFile);
    }

    protected LastDeathsData getDeathsData() {
        return deathsData;
    }

    public void save() {
        save(deathsData, deathsDataFile);
    }

    protected <T> void save(T data, File file) {
        var json = gson.toJson(data);
        try {
            Files.writeString(file.toPath(), json, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected <T> T load(T data, File file) {
        try {
            var json = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            return gson.<T>fromJson(json, data.getClass());
        } catch (IOException ex) {
            ex.printStackTrace();
            return data;
        }
    }

    public void playerDeath(Player player, Date date, String reason) {
        getDeathsData().deaths.put(player.getUniqueId(),
                new DeathInfo(player.getName(), player.getUniqueId(), date, reason));
    }

    public boolean canPlayerRespawn(Player player) {
        return getDeathsData().canRespawn(player);
    }
}
