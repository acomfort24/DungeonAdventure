package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Grid;
import java.util.Arrays;
import java.util.Collections;

public class Dungeon extends Grid<DungeonRoom> {
    
    /** */
    private final int myWidth;
    /** */
    private final int myHeight;
    /** */
    private final int[][] myDungeon;
    
    /**
     * Constructs a new maze with given width and height.
     *
     * @param theWidth maze width
     * @param theHeight maze height
     */
    public Dungeon(final int theWidth, final int theHeight) {
        super(DungeonRoom.class, theWidth, theHeight);
        myWidth = theWidth;
        myHeight = theHeight;
        myDungeon = new int[myWidth][myHeight];
        generateDungeon(myDungeon, 0, 0);
        
        populate((x, y) -> {
            final DungeonRoom room = new DungeonRoom(x, y);
            if ((myDungeon[x][y] & 1) == 0) {
                room.setTopWall(true);
            }
            if ((myDungeon[x][y] & 8) == 0) {
                room.setLeftWall(true);
            }
            return room;
        });
    }
    
    private void generateDungeon(final int[][] theDungeon, final int theX, final int theY) {
        final DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (final DIR dir : dirs) {
            final int newX = theX + dir.myDX;
            final int newY = theY + dir.myDY;
            if (between(newX, getWidth()) && between(newY, getHeight())
                    && (theDungeon[newX][newY] == 0)) {
                theDungeon[theX][theY] |= dir.myBit;
                theDungeon[newX][newY] |= dir.myOppo.myBit;
                generateDungeon(theDungeon, newX, newY);
            }
        }
    }
    
    private static boolean between(final int theV, final int theUpper) {
        return (theV >= 0) && (theV < theUpper);
    }
    
    private enum DIR {
        /** North, South, East, West.*/
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        /** */
        private final int myBit;
        /** */
        private final int myDX;
        /** */
        private final int myDY;
        /** */
        private DIR myOppo;
        
        static {
            N.myOppo = S;
            S.myOppo = N;
            E.myOppo = W;
            W.myOppo = E;
        }
        
        DIR(final int theBit, final int theDX, final int theDY) {
            this.myBit = theBit;
            this.myDX = theDX;
            this.myDY = theDY;
        }
    }
    
    public void display() {
        for (int i = 0; i < myHeight; i++) {
            // draw the north edge
            for (int j = 0; j < myWidth; j++) {
                System.out.print((myDungeon[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < myWidth; j++) {
                System.out.print((myDungeon[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < myWidth; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
    public static void main(final String[] theArgs) {
        final Dungeon d = new Dungeon(5, 5);
        d.display();
    }
}
