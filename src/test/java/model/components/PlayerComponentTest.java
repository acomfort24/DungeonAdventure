package model.components;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests whether the PlayerComponent constructor updates both
 * the CharacterComponent and PlayerComponent values.
 */
public class PlayerComponentTest {
    @Test
    public void testConstructor() {
        final PlayerComponent player = new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1);
        assertEquals(player.getMyCharacterComponent().getMyName(), "warrior");
        assertEquals(.1, player.getMyChncBlock());
    }

}
