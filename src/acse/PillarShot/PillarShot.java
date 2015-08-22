package acse.PillarShot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class PillarShot extends JavaPlugin {
    public static PillarShot plugin;

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        plugin = this;

        Database.initialize();

        getServer().getPluginManager().registerEvents(new BlockListener(this), this);

        getLogger().log(Level.INFO, "Plugin has been enabled.");
    }

    @Override
    public void onDisable(){
        plugin = null;

        getLogger().log(Level.INFO, "Plugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (player != null && !player.hasPermission("pillarshot.admin") && !player.isOp()) {
                Utils.say(Config.getLocale("noPermission"), sender);
                return true;
            }
            this.reloadConfig();
            Utils.say(Config.getLocale("pluginReloaded"), sender);
            return true;
        } else if (args[0].equalsIgnoreCase("version")) {
            if (player != null && !player.hasPermission("pillarshot.admin") && !player.isOp()) {
                Utils.say(Config.getLocale("noPermission"), sender);
                return true;
            }

            Utils.say(Config.getLocale("version") + this.getDescription().getVersion(), sender);

            return true;
        } else if (args[0].equalsIgnoreCase("reset")) {
            if (player != null && !player.hasPermission("pillarshot.admin") && !player.isOp()) {
                Utils.say(Config.getLocale("noPermission"), sender);
                return true;
            }

            Database.reset();

            Utils.say(Config.getLocale("databaseReset"), sender);
            return true;
        } else {
            Utils.say(Config.getLocale("help"), sender);
        }

        return true;
    }
}
