package model.components;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterComponentTest {
    @Test
    public void testConstructor() {
        CharacterComponent character = new CharacterComponent(2, 4, 3, .1, 40, "frank");
        assertEquals(character.getMyMinDmg(), 2);
        assertEquals(character.getMyMaxDmg(), 4);
        assertEquals(character.getMyAtkSpd(), 3);
        assertEquals(character.getMyChncHit(), .1);
        assertEquals(character.getMyName(), "frank");
    }
}
