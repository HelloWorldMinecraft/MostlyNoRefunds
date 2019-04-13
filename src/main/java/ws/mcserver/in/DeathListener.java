package ws.mcserver.in;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DeathListener implements Listener {
    private JavaPlugin plugin;

    public DeathListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        UUID deathUUID = UUID.randomUUID();

        plugin.getLogger().info(event.getEntity().getName() + " has died at " + event.getEntity().getLocation() + " with " + event.getEntity().getExp() + " EXP.");
        plugin.getLogger().info("To restore items, run /refund " + event.getEntity().getName() + " " + deathUUID + "");

        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("data", event.getDrops());

        try {
            configuration.save(new File(plugin.getDataFolder(), deathUUID + ".yml"));
        } catch (IOException exception) {
            plugin.getLogger().info("Error: Death was not saved.");
            exception.printStackTrace();
        }
    }
}
