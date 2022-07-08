package cx.rain.mc.deathcooldown.data.bean;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LastDeathsData {
    public Map<UUID, DeathInfo> deaths = new HashMap<>();

    public boolean canRespawn(Player player) {
        var uuid = player.getUniqueId();
        if (deaths.containsKey(uuid)) {
            return deaths.get(uuid).canRespawn();
        } else {
            return true;
        }
    }
}
