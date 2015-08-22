package acse.PillarShot;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Database {
    private static HashMap<String, Dossier> dossiers;

    public static void initialize() {
        dossiers = new HashMap<>();
    }

    public static Dossier getDossier(Player player) {
        return getDossier(player.getName());
    }

    public static Dossier getDossier(String player) {
        player = player.toLowerCase();
        Dossier dossier = dossiers.get(player);

        if(dossier == null) {
            dossier = new Dossier(player);
            dossiers.put(dossier.player, dossier);
        }

        return dossier;
    }

    public static void reset() {
        dossiers.clear();
    }
}
