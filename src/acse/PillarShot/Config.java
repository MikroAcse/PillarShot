package acse.PillarShot;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class Config {
    public static Configuration getConfig() {
        return PillarShot.plugin.getConfig();
    }

    public static ConfigurationSection getSection(String path) {
        return getConfig().getConfigurationSection(path);
    }

    public static int getCheckMinimalHeight() {
        return getConfig().getInt("checkMinimalHeight");
    }

    public static int getCheckMinimalY() {
        return getConfig().getInt("checkMinimalY");
    }

    public static int getRemoveLastBlocks() {
        return getConfig().getInt("removeLastBlocks");
    }

    public static boolean getDisableInRegions() {
        return getConfig().getBoolean("disableInRegions");
    }

    public static int getError() {
        return getConfig().getInt("error");
    }

    public static int getErrorRadius() {
        return getConfig().getInt("errorRadius");
    }

    public static boolean getCheckBlockType() {
        return getConfig().getBoolean("checkBlockType");
    }

    public static List<String> getGamemodesEnabled() {
        return getConfig().getStringList("gamemodesEnabled");
    }

    public static String getBypassPermission() {
        return getConfig().getString("bypassPermission");
    }

    public static boolean getDecreaseFallDamage() {
        return getConfig().getBoolean("decreaseFallDamage");
    }

    public static List<String> getDisabledWorlds() {
        return getConfig().getStringList("disabledWorlds");
    }

    public static int getWarnCount() {
        return getConfig().getInt("warnCount");
    }

    public static String getTooMuchWarnsCommand() {
        return getConfig().getString("tooMuchWarnsCommand");
    }

    public static int getPlacementCooldown() {
        return getConfig().getInt("placementCooldown");
    }

    public static String getWarnedCommand() {
        return getConfig().getString("warnedCommand");
    }

    public static String getCooledDownCommand() {
        return getConfig().getString("cooledDownCommand");
    }

    public static int getMinimalY() {
        return getConfig().getInt("minimalY");
    }

    public static boolean isEnabled() {
        return getConfig().getBoolean("enabled");
    }

    public static String getLocale(String name) {
        return ChatColor.translateAlternateColorCodes('&', getSection("locale").getString(name));
    }

    public static boolean isDebug() {
        return getConfig().getBoolean("debug");
    }
}
