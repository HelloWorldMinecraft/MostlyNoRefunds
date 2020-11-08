package ws.mcserver.in.mostlynorefunds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class RefundExecutor implements CommandExecutor {
    private JavaPlugin plugin;

    public RefundExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String name, String[] args) {
        if (args.length != 2) return false;

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            commandSender.sendMessage("Invalid Player: " + args[0]);
            return true;
        }

        File file = new File(plugin.getDataFolder(), UUID.fromString(args[1]) + ".yml");
        if (!file.exists()) {
            commandSender.sendMessage("Invalid Death: " + args[1]);
        }

        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            commandSender.sendMessage("Invalid Configuration Data. Check console for details.");
            exception.printStackTrace();
        }

        Collection<ItemStack> leftover = player.getInventory().addItem(((List<ItemStack>) config.get("data")).toArray(new ItemStack[0])).values();
        leftover.forEach(item -> player.getWorld().dropItem(player.getLocation(), item));

        return true;
    }
}
