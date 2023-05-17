package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Cell;

public class DungeonRoom extends Cell {
    
    /** */
    private String myRoom;
    
    public DungeonRoom(final int theX, final int theY) {
        super(theX, theY);
        
    }
    
    public void setRoom(final int theNum) {
        myRoom = "dungeonRoom" + theNum + ".tmx";
    }
    
    public String getRoom() {
        return myRoom;
    }
}
