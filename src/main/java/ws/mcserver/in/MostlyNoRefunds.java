package ws.mcserver.in;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MostlyNoRefunds extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("refund").setExecutor(new RefundExecutor(this));
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }
}
