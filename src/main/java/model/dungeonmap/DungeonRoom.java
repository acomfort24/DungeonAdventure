package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Cell;
import com.almasb.fxgl.core.math.FXGLMath;

import java.io.Serializable;

/**
 * Represents a room in the dungeon.
 * Stores information about the room's properties and state.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class DungeonRoom extends Cell implements Serializable {
    /** The chance of certain properties spawning in the room. */
    private static final double SPAWN_CHANCE = 0.1;
    /** The file name of the room. */
    private String myRoom;
    /** The type of the room. */
    private String myType;
    /** Indicates if the room has a visibility potion. */
    private boolean myVisPot;
    /** Indicates if the room has a healing potion. */
    private boolean myHealPot;
    /** Indicates if the room has a pit. */
    private boolean myPit;
    /** Indicates if the room has been visited. */
    private boolean myVisit;
    /** Indicates if the room has a pillar. */
    private boolean myPillar;
    /** Indicates if the room has a monster. */
    private boolean myMonster;
    /** The type of the monster in the room. */
    private String myMonsterType;

    /**
     * Constructs a DungeonRoom object with the specified coordinates.
     *
     * @param theX the x-coordinate of the room
     * @param theY the y-coordinate of the room
     */
    public DungeonRoom(final int theX, final int theY) {
        super(theX, theY);
        myType = "basic";
        myVisPot = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myHealPot = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myPit = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myMonsterType = "none";
    }
    /**
     * Constructs a DungeonRoom object with the specified coordinates and properties.
     *
     * @param theX           the x-coordinate of the room
     * @param theY           the y-coordinate of the room
     * @param theHasVisPot   indicates if the room has a visibility potion
     * @param theHasHealthPot indicates if the room has a healing potion
     * @param theHasPit      indicates if the room has a pit
     * @param theHasMonster  indicates if the room has a monster
     * @param theHasBeenVisited indicates if the room has been visited
     * @param theHasPillar   indicates if the room has a pillar
     */
    public DungeonRoom(final int theX, final int theY,
                       final Boolean theHasVisPot, final Boolean theHasHealthPot,
                       final Boolean theHasPit, final Boolean theHasMonster,
                       final Boolean theHasBeenVisited, final Boolean theHasPillar) {
        super(theX, theY);
        myVisPot = theHasVisPot;
        myHealPot = theHasHealthPot;
        myPit = theHasPit;
        myMonster = theHasMonster;
        myVisit = theHasBeenVisited;
        myPillar = theHasPillar;
    }
    /**
     * Returns the file name of the room.
     *
     * @return the file name of the room
     */
    public String getRoom() {
        return myRoom;
    }
    /**
     * Sets the file name of the room.
     *
     * @param theNum the number to append to the file name
     */
    public void setRoom(final int theNum) {
        myRoom = "DungeonRoom" + theNum + ".tmx";
    }
    /**
     * Returns the type of the room.
     *
     * @return the type of the room
     */
    public String getType() {
        return myType;
    }
    /**
     * Sets the type of the room.
     *
     * @param theType the type of the room
     */
    public void setType(final String theType) {
        myType = theType;
        switch (myType) {
            case "entrance", "exit" -> {
                setVisPot(false);
                setHealPot(false);
                setPit(false);
                setMonster(false);
            }
            case "pillar" -> {
                setVisPot(false);
                setHealPot(false);
                setPit(false);
            }
            default -> {
                // Do nothing
            }
        }
    }
    /**
     * Checks if the room has a visibility potion.
     *
     * @return true if the room has a visibility potion, false otherwise
     */
    public boolean hasVisPot() {
        return myVisPot;
    }
    /**
     * Sets the visibility potion property of the room.
     *
     * @param theVisPot true to set the room to have a visibility potion, false otherwise
     */
    public void setVisPot(final boolean theVisPot) {
        myVisPot = theVisPot;
    }
    /**
     * Checks if the room has a healing potion.
     *
     * @return true if the room has a healing potion, false otherwise
     */
    public boolean hasHealPot() {
        return myHealPot;
    }
    /**
     * Sets the healing potion property of the room.
     *
     * @param theHealPot true to set the room to have a healing potion, false otherwise
     */
    public void setHealPot(final boolean theHealPot) {
        myHealPot = theHealPot;
    }
    /**
     * Checks if the room has a pit.
     *
     * @return true if the room has a pit, false otherwise
     */
    public boolean hasPit() {
        return myPit;
    }
    /**
     * Sets the pit property of the room.
     *
     * @param thePit true to set the room to have a pit, false otherwise
     */
    public void setPit(final boolean thePit) {
        myPit = thePit;
    }
    /**
     * Sets the monster property of the room.
     *
     * @param theMonster true to set the room to have a monster, false otherwise
     */
    public void setMonster(final boolean theMonster) {
        myMonster = theMonster;
    }
    /**
     * Checks if the room has a monster.
     *
     * @return true if the room has a monster, false otherwise
     */
    public boolean hasMonster() {
        return myMonster;
    }
    /**
     * Sets the type of the monster in the room.
     *
     * @param theMonsterType the type of the monster
     */
    public void setMonsterType(final String theMonsterType) {
        myMonsterType = theMonsterType;
    }
    /**
     * Returns the type of the monster in the room.
     *
     * @return the type of the monster
     */
    public String getMonsterType() {
        return myMonsterType;
    }
    /**
     * Checks if the room has been visited.
     *
     * @return true if the room has been visited, false otherwise
     */
    public boolean hasBeenVisited() {
        return myVisit;
    }
    /**
     * Sets the visited property of the room.
     *
     * @param theVisit true to set the room as visited, false otherwise
     */
    public void setVisited(final boolean theVisit) {
        myVisit = theVisit;
    }
    /**
     * Checks if the room has a pillar.
     *
     * @return true if the room has a pillar, false otherwise
     */
    public boolean hasPillar() {
        return myPillar;
    }
    /**
     * Sets the pillar property of the room.
     *
     * @param thePillar true to set the room to have a pillar, false otherwise
     */
    public void setPillar(final boolean thePillar) {
        myPillar = thePillar;
    }

    /**
     * Returns a string representation of the room.
     * The string contains characters representing the room's properties.
     * M - Monster
     * H - Healing Potion
     * V - Visibility Potion
     * P - Pillar
     *
     * @return a string representation of the room
     */
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        for (int k = 0; k < 2; k++) {
            final StringBuilder curLine = new StringBuilder();
            curLine.append("       ");
            if (k == 0 && hasMonster()) {
                curLine.setCharAt(1, 'M');
            }
            if (k == 0 && hasHealPot()) {
                curLine.setCharAt(6, 'H');
            }
            if (k == 1 && hasVisPot()) {
                curLine.setCharAt(1, 'V');
            }
            if (k == 1 && hasPillar()) {
                curLine.setCharAt(6, 'P');
            }
            returnedString.append(curLine);
            returnedString.append("\n");
        }
        return returnedString.toString();
    }
}
