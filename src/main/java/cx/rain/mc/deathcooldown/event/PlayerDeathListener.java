package cx.rain.mc.deathcooldown.event;

import cx.rain.mc.deathcooldown.DeathCoolDown;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Date;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        var player = event.getEntity();
        DeathCoolDown.getInstance().getDataManager().playerDeath(player,
                new Date(), event.getDeathMessage());
    }
}
