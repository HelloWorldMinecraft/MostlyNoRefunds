package ws.mcserver.in.mostlynorefunds;

import org.bukkit.plugin.java.JavaPlugin;

public class MostlyNoRefunds extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("refund").setExecutor(new RefundExecutor(this));
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }
}
