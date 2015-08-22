package acse.PillarShot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandParser {
    public static void parse(String str, Player player) {
        if(str.length() == 0) {
            return;
        }

        String[] strings = str.split(">;");
        int length = strings.length;

        for (int i = 0; i < length; i++) {
            String string = strings[i];

            run(string, player);
        }
    }

    public static void run(String str, Player player) {
        str = Utils.replacePlaceholders(str, player);
        if (str.startsWith("/")) {
            Server server = Bukkit.getServer();
            ConsoleCommandSender console = server.getConsoleSender();
            String cmd = str.substring(1);

            server.dispatchCommand(console, ChatColor.translateAlternateColorCodes('&', cmd));
        } else {
            Utils.say(str, player);
        }
    }
}
