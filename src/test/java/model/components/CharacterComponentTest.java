package model.components;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the constructor for CharacterComponent
 */
public class CharacterComponentTest {
    @Test
    public void testConstructor() {
        CharacterComponent character = new CharacterComponent(2, 4, 3, .1, 40, "frank");
        assertEquals(2, character.getMyMinDmg());
        assertEquals(4, character.getMyMaxDmg());
        assertEquals(.7, character.getMyAtkSpd());
        assertEquals(.1, character.getMyChncHit());
        assertEquals("frank", character.getMyName());
    }
}
