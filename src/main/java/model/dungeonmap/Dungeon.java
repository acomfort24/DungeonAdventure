package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Grid;

import java.util.Arrays;
import java.util.Collections;

public class Dungeon extends Grid<DungeonRoom> {
    private final int width;
    private final int height;
    private final int[][] dungeon;
    /**
     * Constructs a new maze with given width and height.
     *
     * @param w maze width
     * @param h maze height
     */
    public Dungeon(int w, int h) {
        super(DungeonRoom.class, w, h);
        width = w;
        height = h;
        dungeon = new int[width][height];
        generateDungeon(dungeon, 0, 0);
        
        populate((x, y) -> {
            DungeonRoom room = new DungeonRoom(x, y);
            if ((dungeon[x][y] & 1) == 0)
                room.setTopWall(true);
            
            if ((dungeon[x][y] & 8) == 0)
                room.setLeftWall(true);
            
            return room;
        });
    }
    
    private void generateDungeon(int[][] theDungeon, int cx, int cy) {
        DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (DIR dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, getWidth()) && between(ny, getHeight()) && (theDungeon[nx][ny] == 0)) {
                theDungeon[cx][cy] |= dir.bit;
                theDungeon[nx][ny] |= dir.opposite.bit;
                generateDungeon(theDungeon, nx, ny);
            }
        }
    }
    
    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }
    
    private enum DIR {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIR opposite;
        
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }
        
        DIR(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    }
    
    public void display() {
        for (int i = 0; i < height; i++) {
            // draw the north edge
            for (int j = 0; j < width; j++) {
                System.out.print((dungeon[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < width; j++) {
                System.out.print((dungeon[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < width; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
    public static void main(String[] args) {
        Dungeon d = new Dungeon(5,5);
        d.display();
    }
}
