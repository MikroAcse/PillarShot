package acse.PillarShot;

import java.util.*;

public class Dossier {
    public String player;

    public ArrayList<SimpleBlock> blocks;
    public int lastX;
    public int lastY;
    public String lastType;

    public int warns;
    public long cooldownStartedTime;

    public Dossier(String player) {
        this.player = player;
        blocks = new ArrayList<>();
        lastX = 0;
        lastY = 0;
        lastType = null;
        warns = 0;
    }

    public void push(SimpleBlock block) {
        if(blocks.size() >= Config.getCheckMinimalHeight()) {
            blocks.remove(0);
        }
        blocks.add(block);
    }

    public void push(int x, int y, int z, String type, String world) {
        push(new SimpleBlock(x, y, z, type, world));
    }

    public void clear() {
        blocks.clear();
        lastX = 0;
        lastY = 0;
        lastType = null;
    }
}
