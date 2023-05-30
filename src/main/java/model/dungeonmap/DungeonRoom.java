package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Cell;
import com.almasb.fxgl.core.math.FXGLMath;

public class DungeonRoom extends Cell {
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
    
    public DungeonRoom(final int theX, final int theY) {
        super(theX, theY);
        setType("basic");
        myVisPot = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myHealPot = FXGLMath.randomBoolean(SPAWN_CHANCE);
        myPit = FXGLMath.randomBoolean(SPAWN_CHANCE);
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
        if ("entrance".equals(myType) || "pillar".equals(myType) || "exit".equals(myType)) {
            setVisPot(false);
            setHealPot(false);
            setPit(false);
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

    public boolean hasBeenVisited() {
        return myVisit;
    }

    public void setVisited(final boolean theVisit) {
        myVisit = theVisit;
    }
}
