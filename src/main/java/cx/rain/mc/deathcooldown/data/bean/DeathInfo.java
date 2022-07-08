package cx.rain.mc.deathcooldown.data.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public record DeathInfo(String name, UUID uuid, Date deathTime, String reason) {
    public boolean canRespawn() {
        var respawnTime = Calendar.getInstance();
        respawnTime.setTime(deathTime);
        respawnTime.add(Calendar.DATE, 3);
        return Calendar.getInstance().after(respawnTime);
    }
}
