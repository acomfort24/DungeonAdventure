package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Cell;
import com.almasb.fxgl.core.math.FXGLMath;

import java.io.Serializable;

public class DungeonRoom extends Cell implements Serializable {
    /** */
    private static final double SPAWN_CHANCE = 0.1;
    /** */
    private String myRoom;
    /** */
    private String myType;
    /** */
    private boolean myVisPot;
    /** */
    private boolean myHealPot;
    /** */
    private boolean myPit;
    /** */
    private boolean myVisit;
    /** */
    private boolean myPillar;
    /** */
    private boolean myMonster;
    /** */
    private String myMonsterType;
    
    public DungeonRoom(final int theX, final int theY) {
        super(theX, theY);
        myType = "basic";
        myVisPot = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myHealPot = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myPit = FXGLMath.randomBoolean(1);
        myMonsterType = "none";
    }
    public DungeonRoom(final int theX, final int theY,
                       final Boolean theHasVisPot, final Boolean theHasHealthPot,
                       final Boolean theHasPit, final Boolean theHasMonster,
                       final Boolean theHasBeenVisited, final Boolean theHasPillar){
        super(theX, theY);
        myVisPot = theHasVisPot;
        myHealPot = theHasHealthPot;
        myPit = theHasPit;
        myMonster = theHasMonster;
        myVisit = theHasBeenVisited;
        myPillar = theHasPillar;
    }
    
    public String getRoom() {
        return myRoom;
    }
    
    public void setRoom(final int theNum) {
        myRoom = "DungeonRoom" + theNum + ".tmx";
    }
    
    public String getType() {
        return myType;
    }
    
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
    
    public boolean hasVisPot() {
        return myVisPot;
    }
    
    public void setVisPot(final boolean theVisPot) {
        myVisPot = theVisPot;
    }
    
    public boolean hasHealPot() {
        return myHealPot;
    }
    
    public void setHealPot(final boolean theHealPot) {
        myHealPot = theHealPot;
    }
    
    public boolean hasPit() {
        return myPit;
    }
    
    public void setPit(final boolean thePit) {
        myPit = thePit;
    }

    public void setMonster(final boolean theMonster) {
        myMonster = theMonster;
    }
    public boolean hasMonster() {
        return myMonster;
    }
    
    public void setMonsterType(final String theMonsterType) {
        myMonsterType = theMonsterType;
    }
    
    public String getMonsterType() {
        return myMonsterType;
    }

    public boolean hasBeenVisited() {
        return myVisit;
    }

    public void setVisited(final boolean theVisit) {
        myVisit = theVisit;
    }
    
    public boolean hasPillar() {
        return myPillar;
    }
    
    public void setPillar(final boolean thePillar) {
        myPillar = thePillar;
    }
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
