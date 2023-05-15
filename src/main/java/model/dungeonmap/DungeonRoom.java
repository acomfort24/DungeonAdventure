package model.dungeonmap;

import com.almasb.fxgl.core.collection.grid.Cell;

public class DungeonRoom extends Cell {
    
    private boolean topWall = false;
    private boolean leftWall = false;
    
    public DungeonRoom(int x, int y) {
        super(x, y);
    }
    
    /**
     * @param leftWall left wall for this cell
     */
    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }
    
    /**
     * @param topWall top wall for this cell
     */
    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }
    
    /**
     * @return if left wall is present
     */
    public boolean hasLeftWall() {
        return leftWall;
    }
    
    /**
     * @return if top wall is present
     */
    public boolean hasTopWall() {
        return topWall;
    }
    
}
