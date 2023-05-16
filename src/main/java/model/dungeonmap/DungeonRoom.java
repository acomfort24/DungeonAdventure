package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Cell;

public class DungeonRoom extends Cell {
    
    /** */
    private boolean myTopWall;
    /** */
    private boolean myLeftWall;
    
    public DungeonRoom(final int theX, final int theY) {
        super(theX, theY);
    }
    
    /**
     * @param theLeftWall left wall for this cell
     */
    public void setLeftWall(final boolean theLeftWall) {
        myLeftWall = theLeftWall;
    }
    
    /**
     * @param theTopWall top wall for this cell
     */
    public void setTopWall(final boolean theTopWall) {
        myTopWall = theTopWall;
    }
    
    /**
     * @return if left wall is present
     */
    public boolean hasLeftWall() {
        return myLeftWall;
    }
    
    /**
     * @return if top wall is present
     */
    public boolean hasTopWall() {
        return myTopWall;
    }
    
}
