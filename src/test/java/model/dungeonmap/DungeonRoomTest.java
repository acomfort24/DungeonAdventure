package model.dungeonmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests constructor, toString and getters and setters using
 * the toString
 */
public class DungeonRoomTest {
    DungeonRoom room;
    public DungeonRoomTest() {
        room = new DungeonRoom(0, 0,
                true, true,
                true, true,
                true, true);
    }
    @Test
    public void dungeonConstructorTest() {
        assertEquals(0, room.getX());
        assertEquals(0, room.getY());
        assertTrue(room.hasVisPot());
        assertTrue(room.hasHealPot());
        assertTrue(room.hasPit());
        assertTrue(room.hasMonster());
        assertTrue(room.hasBeenVisited());
        assertTrue(room.hasPillar());
    }
    @Test
    public void toStringTrueTest() {
        String roomString = "|+++++|\n|M   H|\n|V   P|\n|+++++|";
        assertEquals(roomString, room.toString());
    }
    @Test
    public void toStringFalseTest() {
        room.setMonster(false);
        room.setHealPot(false);
        room.setVisPot(false);
        room.setPillar(false);
        String roomString = "|+++++|\n|     |\n|     |\n|+++++|";
        assertEquals(roomString, room.toString());
    }
}
