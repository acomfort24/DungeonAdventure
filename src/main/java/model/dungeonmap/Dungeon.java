package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Grid;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

public class Dungeon extends Grid<DungeonRoom> {
    /** */
    private static final int PILLAR_COUNT = 4;
    /** */
    private static final Predicate<DungeonRoom> IS_BASIC = x -> "basic".equals(x.getType());
    /** */
    private final int myWidth;
    /** */
    private final int myHeight;
    /** */
    private final int[][] myDungeon;
    /** */
    private DungeonRoom myEntrance;
    /** */
    private DungeonRoom myExit;
    
    /**
     * Constructs a new dungeon with given width and height.
     *
     * @param theWidth dungeon width
     * @param theHeight dungeon height
     */
    public Dungeon(final int theWidth, final int theHeight) {
        super(DungeonRoom.class, theWidth, theHeight);
        myWidth = theWidth;
        myHeight = theHeight;
        myDungeon = new int[myWidth][myHeight];
        generateDungeon(myDungeon, 0, 0);
        populate((x, y) -> {
            final DungeonRoom room = new DungeonRoom(x, y);
            room.setRoom(myDungeon[x][y]);
            return room;
        });
        setEntrance();
        setExit();
        setPillarRooms();
    }
    
    private void generateDungeon(final int[][] theDungeon, final int theX, final int theY) {
        final DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (final DIR dir : dirs) {
            final int nextX = theX + dir.myDX;
            final int nextY = theY + dir.myDY;
            if (between(nextX, getWidth()) && between(nextY, getHeight())
                    && (theDungeon[nextX][nextY] == 0)) {
                theDungeon[theX][theY] |= dir.myBit;
                theDungeon[nextX][nextY] |= dir.myOppo.myBit;
                generateDungeon(theDungeon, nextX, nextY);
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
                System.out.print((myDungeon[j][i] & 1) == 0 ? "+------" : "+      ");
            }
            System.out.println("+");
            // draw the west edge
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < myWidth; j++) {
                    System.out.print((myDungeon[j][i] & 8) == 0 ? "|      " : "       ");
                }
                System.out.println("|");
            }
        }
        // draw the bottom line
        for (int j = 0; j < myWidth; j++) {
            System.out.print("+------");
        }
        System.out.println("+");
    }
    
    public String getEntranceMap() {
        return myEntrance.getRoom();
    }
    
    public int getEntranceX() {
        return myEntrance.getX();
    }
    public int getEntranceY() {
        return myEntrance.getY();
    }
    private void setEntrance() {
        myEntrance = getRandomCell();
        myEntrance.setType("entrance");
        myEntrance.setVisited(true);
    }
    
    private void setExit() {
        if (getRandomCell(IS_BASIC).isPresent()) {
            myExit = getRandomCell(IS_BASIC).get();
            myExit.setType("exit");
        }
    }
    
    private void setPillarRooms() {
        for (int i = 0; i < PILLAR_COUNT; i++) {
            if (getRandomCell(IS_BASIC).isPresent()) {
                DungeonRoom dr = getRandomCell(IS_BASIC).get();
                dr.setType("pillar");
                dr.setPillar(true);
            }
        }
    }
}
