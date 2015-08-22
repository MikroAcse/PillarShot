package acse.PillarShot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Utils {

    public static boolean checkPillar(Dossier dossier) {
        ArrayList<SimpleBlock> blocks = dossier.blocks;
        int len = blocks.size();

        if(len < Config.getCheckMinimalHeight()) {
            return false;
        }

        SimpleBlock lastBlock = blocks.get(0);
        boolean checkType = Config.getCheckBlockType();
        int maxError = Config.getError();
        int errorRadius = Config.getErrorRadius();
        int error = 0;

        for (int i = 1; i < len; i++) {
            SimpleBlock block = blocks.get(i);

            if(checkType && !lastBlock.type.equalsIgnoreCase(block.type)) {
                return false;
            }

            if(!lastBlock.world.equalsIgnoreCase(block.world)) {
                return false;
            }

            Utils.debug("Check: " + Boolean.toString(lastBlock.x == block.x && lastBlock.z == block.z && lastBlock.y < block.y));

            if(lastBlock.x == block.x && lastBlock.z == block.z && lastBlock.y < block.y) {
                lastBlock = block;
                continue;
            } else if(maxError > 0) {
                if(errorRadius > 0) {
                    double distance = Math.sqrt(Math.pow(block.x - lastBlock.x, 2)
                            + Math.pow(block.y - lastBlock.y, 2)
                            + Math.pow(block.z - lastBlock.z, 2));

                    Utils.debug("Error, distance, errorRadius, maxError: " + error + ", " + distance + ", " + errorRadius + ", " + maxError);
                    if(distance <= errorRadius) {
                        error++;
                        if(error > maxError) {
                            return false;
                        }
                        continue;
                    }
                } else {
                    error++;
                    if(error > maxError) {
                        return false;
                    }
                    continue;
                }
            }

            return false;
        }

        return true;
    }

    public static void removeBlocks(ArrayList<SimpleBlock> blocks, int count) {
        String lastWorld = blocks.get(0).world;
        int len = blocks.size();
        int blocksRemoved = 0;
        for (int i = len - 1; i >= 0; i--) {
            SimpleBlock block = blocks.get(i);

            if(!block.world.equalsIgnoreCase(lastWorld)) {
                return;
            }

            World world = Bukkit.getWorld(block.world);
            world.getBlockAt(block.x, block.y, block.z).breakNaturally();
            blocksRemoved++;

            if(blocksRemoved >= count) {
                return;
            }
        }
    }

    public static String replacePlaceholders(String str, Player player) {
        str = str.replaceAll("%player%", player.getName());
        str = str.replaceAll("%playername%", player.getDisplayName());
        return str;
    }

    public static void debug(String msg) {
        if(Config.isDebug()) {
            Bukkit.getServer().getConsoleSender().sendMessage(msg);
        }
    }

    public static void say(String msg, Player player) {
        say(msg, player, true);
    }

    public static void say(String msg, CommandSender sender) {
        if(msg.length() == 0) {
            return;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void say(String msg, Player player, boolean placeholders) {
        if(msg.length() == 0) {
            return;
        }
        if(placeholders) {
            msg = replacePlaceholders(msg, player);
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
