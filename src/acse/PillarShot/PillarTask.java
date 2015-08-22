package acse.PillarShot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class PillarTask implements Runnable {
    Dossier dossier;
    Player player;
    Block block;

    public PillarTask(Dossier dossier, Player player, Block block) {
        this.dossier = dossier;
        this.player = player;
        this.block = block;
    }

    @Override
    public void run() {
        boolean isPillar = Utils.checkPillar(dossier);

        if(isPillar) {
            if(Config.getRemoveLastBlocks() > 0) {
                if (block.getY() >= Config.getMinimalY()) {
                    if(Config.getDecreaseFallDamage()) {
                        player.addPotionEffect(PotionEffectType.JUMP.createEffect(40, 255));
                    }
                    Utils.removeBlocks(dossier.blocks, Config.getRemoveLastBlocks());
                    dossier.clear();
                }
            }

            Utils.say(Config.getLocale("warn"), player);
            CommandParser.parse(Config.getWarnedCommand(), player);
            int warnCount = Config.getWarnCount();
            if (warnCount > 0) {
                dossier.warns++;
                if(dossier.warns >= warnCount) {
                    CommandParser.parse(Config.getTooMuchWarnsCommand(), player);
                    dossier.warns = 0;
                }
            }

            int cooldown = Config.getPlacementCooldown();
            if(cooldown > 0) {
                dossier.cooldownStartedTime = System.currentTimeMillis();
            }
        }
    }
}
