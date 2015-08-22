package acse.PillarShot;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class BlockListener implements Listener {

    public static PillarShot plugin;

    public BlockListener(PillarShot instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        if(!Config.isEnabled()) {
            return;
        }

        Player p = e.getPlayer();

        if(p.hasPermission(Config.getBypassPermission()) || p.isOp()) {
            return;
        }

        String world = p.getWorld().getName();
        List<String> disabledWorlds = Config.getDisabledWorlds();
        if(disabledWorlds.contains(world.toLowerCase())) {
            return;
        }

        List<String> gamemodes = Config.getGamemodesEnabled();
        String gamemode = p.getGameMode().name();

        if(!gamemodes.contains(gamemode.toLowerCase())) {
            return;
        }

        Block b = e.getBlock();
        Dossier dossier = Database.getDossier(p);

        int cooldown = Config.getPlacementCooldown();
        if(cooldown > 0) {
            int diff = (int)((System.currentTimeMillis() - dossier.cooldownStartedTime)/1000);

            if(diff < cooldown) {
                String msg = Config.getLocale("cooldownBlock");
                msg = msg.replaceAll("%cooldown%", String.valueOf(cooldown - diff));
                Utils.say(msg, p);
                e.setCancelled(true);
                return;
            }
        }

        if(Config.getDisableInRegions()) {
            RegionManager rgmanager = WorldGuardPlugin.inst().getRegionManager(p.getWorld());
            ApplicableRegionSet regions = rgmanager.getApplicableRegions(b.getLocation());

            Utils.debug("Regions count: " + regions.size());
            if(regions.size() > 0) {
                dossier.clear();
                return;
            }
        }

        if(b.getY() < Config.getCheckMinimalY()) {
            dossier.clear();
            return;
        }

        dossier.push(b.getX(), b.getY(), b.getZ(), b.getType().name(), b.getWorld().getName());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(PillarShot.plugin, new PillarTask(dossier, p, b), 20);
    }
}
