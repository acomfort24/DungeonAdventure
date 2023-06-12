package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Grid;
import com.almasb.fxgl.core.math.FXGLMath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Represents a dungeon composed of dungeon rooms organized in a grid.
 * The dungeon can be generated with a specified width and height,
 * or it can be constructed from existing data.
 * The dungeon contains an entrance, an exit, pillar rooms, and monsters.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class Dungeon extends Grid<DungeonRoom> implements Serializable {
    /** The number of pillars in the dungeon. */
    private static final int PILLAR_COUNT = 4;
    /** Predicate to check if a dungeon room is of type "basic". */
    private static final Predicate<DungeonRoom> IS_BASIC = x -> "basic".equals(x.getType());
    /** The width of the dungeon. */
    private final int myWidth;
    /** The height of the dungeon. */
    private final int myHeight;
    /** The 2D array representing the dungeon layout. */
    private final int[][] myDungeon;
    /** The entrance room of the dungeon. */
    private DungeonRoom myEntrance;
    /** The exit room of the dungeon. */
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
        addMonsters();
    }

    /**
     * Constructs a dungeon from existing data.
     *
     * @param theNumberArray the number array representing the dungeon layout
     * @param theTypeArray   the type array representing the types of dungeon rooms
     * @param theRoomArray   the room array representing the properties of dungeon rooms
     * @param theMonsterArray the monster array representing the types of monsters in dungeon rooms
     */
    public Dungeon(final int[][] theNumberArray, final String[][] theTypeArray,
                   final ArrayList<ArrayList<Map<String, Boolean>>> theRoomArray,
                   final String[][] theMonsterArray) {
        super(DungeonRoom.class, theNumberArray.length, theNumberArray[0].length);
        myWidth = theNumberArray.length;
        myHeight = theNumberArray[0].length;
        myDungeon = theNumberArray;

        populate((x, y) -> {
            final DungeonRoom room = new DungeonRoom(x, y,
                    theRoomArray.get(x).get(y).get("hasVisPot"),
                    theRoomArray.get(x).get(y).get("hasHealPot"),
                    theRoomArray.get(x).get(y).get("hasPit"),
                    theRoomArray.get(x).get(y).get("hasMonster"),
                    theRoomArray.get(x).get(y).get("hasBeenVisited"),
                    theRoomArray.get(x).get(y).get("hasPillar"));
            room.setRoom(myDungeon[x][y]);
            room.setType(theTypeArray[x][y]);
            if (theTypeArray[x][y].equalsIgnoreCase("entrance")) {
                setEntrance(room);
            }
            if (theTypeArray[x][y].equalsIgnoreCase("exit")) {
                setExit(room);
            }
            if (room.hasMonster()) {
                room.setMonsterType(theMonsterArray[x][y]);
            }
            return room;
        });
    }
    /**
     * Generates the dungeon layout recursively using a depth-first search algorithm.
     *
     * @param theDungeon the 2D array representing the dungeon layout
     * @param theX       the current X-coordinate
     * @param theY       the current Y-coordinate
     */
    private void generateDungeon(final int[][] theDungeon, final int theX, final int theY) {
        final DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (final DIR dir : dirs) {
            final int nextX = theX + dir.myDX;
            final int nextY = theY + dir.myDY;
            if (between(nextX, getWidth()) && between(nextY, getHeight())
                    && theDungeon[nextX][nextY] == 0) {
                theDungeon[theX][theY] |= dir.myBit;
                theDungeon[nextX][nextY] |= dir.myOppo.myBit;
                generateDungeon(theDungeon, nextX, nextY);
            }
        }
    }
    /**
     * Checks if a value is between the lower and upper bounds (inclusive).
     *
     * @param theV     the value to check
     * @param theUpper the upper bound
     * @return true if the value is between the lower and upper bounds, false otherwise
     */
    private static boolean between(final int theV, final int theUpper) {
        return theV >= 0 && theV < theUpper;
    }
    /**
     * Represents the cardinal directions.
     */
    private enum DIR {
        /** North, South, East, West.*/
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        /** The bitmask representing the direction. */
        private final int myBit;
        /** The X-coordinate change for the direction. */
        private final int myDX;
        /** The Y-coordinate change for the direction. */
        private final int myDY;
        /** The opposite direction. */
        private DIR myOppo;
        
        static {
            N.myOppo = S;
            S.myOppo = N;
            E.myOppo = W;
            W.myOppo = E;
        }
        /**
         * Constructs a direction with the specified parameters.
         *
         * @param theBit the bitmask representing the direction
         * @param theDX  the X-coordinate change for the direction
         * @param theDY  the Y-coordinate change for the direction
         */
        DIR(final int theBit, final int theDX, final int theDY) {
            this.myBit = theBit;
            this.myDX = theDX;
            this.myDY = theDY;
        }
    }
    /**
     * Returns the map representation of the entrance room.
     *
     * @return the map representation of the entrance room
     */
    public String getEntranceMap() {
        return myEntrance.getRoom();
    }
    /**
     * Returns the X-coordinate of the entrance room.
     *
     * @return the X-coordinate of the entrance room
     */
    public int getEntranceX() {
        return myEntrance.getX();
    }
    /**
     * Returns the Y-coordinate of the entrance room.
     *
     * @return the Y-coordinate of the entrance room
     */
    public int getEntranceY() {
        return myEntrance.getY();
    }
    /**
     * Sets a random room as the entrance.
     */
    private void setEntrance() {
        myEntrance = getRandomCell();
        myEntrance.setType("entrance");
        myEntrance.setVisited(true);
    }
    /**
     * Sets the specified room as the entrance.
     *
     * @param theRoom the room to set as the entrance
     */
    private void setEntrance(final DungeonRoom theRoom) {
        myEntrance = theRoom;
        myEntrance.setType("entrance");
        myEntrance.setVisited(true);
    }
    /**
     * Sets a random basic room as the exit.
     */
    private void setExit() {
        if (getRandomCell(IS_BASIC).isPresent()) {
            myExit = getRandomCell(IS_BASIC).get();
            myExit.setType("exit");
        }
    }
    /**
     * Sets the specified room as the exit.
     *
     * @param theRoom the room to set as the exit
     */
    private void setExit(final DungeonRoom theRoom) {
        myExit = theRoom;
        myExit.setType("exit");
    }
    /**
     * Sets a specified number of random basic rooms as pillar rooms.
     */
    private void setPillarRooms() {
        for (int i = 0; i < PILLAR_COUNT; i++) {
            if (getRandomCell(IS_BASIC).isPresent()) {
                DungeonRoom dr = getRandomCell(IS_BASIC).get();
                dr.setType("pillar");
                dr.setPillar(true);
            }
        }
    }
    /**
     * Adds monsters to random rooms in the dungeon.
     */
    private void addMonsters() {
        int count = 0;
        while (count < 6) {
            DungeonRoom dr = getRandomCell();
            if (dr != myEntrance && dr != myExit && !dr.hasMonster()) {
                dr.setMonster(true);
                dr.setMonsterType(randomMonster());
                count++;
            }
        }
    }
    /**
     * Returns a random monster from a predefined array.
     *
     * @return a random monster
     */
    private static String randomMonster() {
        final String[] monsterArr = {"Skeleton", "Orc", "Gremlin"};
        return FXGLMath.random(monsterArr).get();
    }
    /**
     * Returns the 2D array representing the dungeon layout.
     *
     * @return the dungeon layout array
     */
    public int[][] getMyDungeon() {
        return myDungeon;
    }
    /**
     * Returns a string representation of the dungeon.
     *
     * @return a string representation of the dungeon
     */
    public String toString() {
        final StringBuilder returnedString = new StringBuilder();
        for (int i = 0; i < myHeight; i++) {
            // draw the north edge
            for (int j = 0; j < myWidth; j++) {
                returnedString.append((myDungeon[j][i] & 1) == 0 ? "+------" : "+      ");
            }
            returnedString.append("+\n");
            // draw the west edge
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < myWidth; j++) {
                    final StringBuilder curLine = new StringBuilder();
                    curLine.append((myDungeon[j][i] & 8) == 0 ? "|      " : "       ");
                    if (k == 0 && this.get(i, j).hasMonster()) {
                        curLine.setCharAt(1, 'M');
                    }
                    if (k == 0 && this.get(i, j).hasHealPot()) {
                        curLine.setCharAt(6, 'H');
                    }
                    if (k == 1 && this.get(i, j).hasVisPot()) {
                        curLine.setCharAt(1, 'V');
                    }
                    if (k == 1 && this.get(i, j).hasPillar()) {
                        curLine.setCharAt(6, 'P');
                    }
                    returnedString.append(curLine);
                }
                returnedString.append("|\n");
            }
        }
        // draw the bottom line
        returnedString.append("+------".repeat(Math.max(0, myWidth)));
        returnedString.append("+");
        return returnedString.toString();
    }

}
