package model.dungeonmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the new game dungeon for having 4 pillars, an exit, and
 * an entrance. It does this for both square and rectangular shapes
 * of different sizes.
 */
public class DungeonTest {
    @Test
    public void simpleConstructor3x3Test() {
        Dungeon dungeon = new Dungeon(3,3);
        assertEquals(4, countPillars(dungeon));
        assertTrue(checkForExit(dungeon));
        assertTrue(checkForEntrance(dungeon));
    }
    @Test
    public void simpleConstructor1x6Test() {
        Dungeon dungeon = new Dungeon(1,6);
        assertEquals(4, countPillars(dungeon));
        assertTrue(checkForExit(dungeon));
        assertTrue(checkForEntrance(dungeon));
    }
    @Test
    public void simpleConstructor6x1Test() {
        Dungeon dungeon = new Dungeon(6,1);
        assertEquals(4, countPillars(dungeon));
        assertTrue(checkForExit(dungeon));
        assertTrue(checkForEntrance(dungeon));
    }
    @Test
    public void simpleConstructor2x3Test() {
        Dungeon dungeon = new Dungeon(2,3);
        assertEquals(4, countPillars(dungeon));
        assertTrue(checkForExit(dungeon));
        assertTrue(checkForEntrance(dungeon));
    }
    @Test
    public void simpleConstructor3x2Test() {
        Dungeon dungeon = new Dungeon(3,2);
        assertEquals(4, countPillars(dungeon));
        assertTrue(checkForExit(dungeon));
        assertTrue(checkForEntrance(dungeon));
    }
    @Test
    public void simpleConstructor5x5Test() {
        Dungeon dungeon = new Dungeon(5,5);
        assertEquals(4, countPillars(dungeon));
        assertTrue(checkForExit(dungeon));
        assertTrue(checkForEntrance(dungeon));
    }
//    @Test
//    public void simpleConstructor100x100Test() {
//        Dungeon dungeon = new Dungeon(100,100);
//        assertEquals(4, countPillars(dungeon));
//        assertTrue(checkForExit(dungeon));
//        assertTrue(checkForEntrance(dungeon));
//    }
    //helper method to count pillars
    public int countPillars(Dungeon theDungeon) {
        int pillarCount = 0;
        for (int i = 0; i < theDungeon.getMyDungeon().length; i++) {
            for (int j = 0; j < theDungeon.getMyDungeon()[i].length; j++) {
                if(theDungeon.get(i, j).hasPillar()) {
                    pillarCount++;
                }
            }
        }
        return pillarCount;
    }

    //helper method to ensure dungeon has an exit
    public Boolean checkForExit(Dungeon theDungeon) {
        boolean hasExit = false;
        for (int i = 0; i < theDungeon.getMyDungeon().length; i++) {
            for (int j = 0; j < theDungeon.getMyDungeon()[i].length; j++) {
                if(theDungeon.get(i, j).getType().equals("exit")) {
                    hasExit = true;
                }
            }
        }
        return hasExit;
    }

    //helper method to ensure dungeon has an exit
    public Boolean checkForEntrance(Dungeon theDungeon) {
        boolean hasEntrance = false;
        for (int i = 0; i < theDungeon.getMyDungeon().length; i++) {
            for (int j = 0; j < theDungeon.getMyDungeon()[i].length; j++) {
                if(theDungeon.get(i, j).getType().equals("entrance")) {
                    hasEntrance = true;
                }
            }
        }
        return hasEntrance;
    }
}
