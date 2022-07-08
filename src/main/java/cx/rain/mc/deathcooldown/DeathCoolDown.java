package cx.rain.mc.deathcooldown;

import cx.rain.mc.deathcooldown.command.RespawnCommand;
import cx.rain.mc.deathcooldown.data.CoolDownDataManager;
import cx.rain.mc.deathcooldown.event.PlayerDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathCoolDown extends JavaPlugin {
    private static DeathCoolDown INSTANCE = null;

    private CoolDownDataManager dataManager;

    public DeathCoolDown() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        dataManager = new CoolDownDataManager(getDataFolder());

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getCommand("respawn").setExecutor(new RespawnCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        dataManager.save();
    }

    public static DeathCoolDown getInstance() {
        return INSTANCE;
    }

    public CoolDownDataManager getDataManager() {
        return dataManager;
    }
}
