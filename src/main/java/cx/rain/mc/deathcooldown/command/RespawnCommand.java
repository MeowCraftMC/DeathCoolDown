package cx.rain.mc.deathcooldown.command;

import cx.rain.mc.deathcooldown.DeathCoolDown;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class RespawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that!");
            return false;
        }

        var player = (Player) sender;
        if (player.getGameMode() != GameMode.SPECTATOR) {
            sender.sendMessage("Are you in the right mode?");
            return false;
        }

        if (DeathCoolDown.getInstance().getDataManager().canPlayerRespawn(player)) {
            var loc = player.getBedSpawnLocation();
            if (loc == null) {
                player.teleport(player.getWorld().getSpawnLocation());
            } else {
                player.teleport(loc);
            }
            player.setGameMode(GameMode.SURVIVAL);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // Todo
        return null;
    }
}
