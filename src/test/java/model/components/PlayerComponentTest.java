package model.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import model.EntityType;
import org.junit.jupiter.api.Test;

import static model.EntityType.HEALTH_POTION;
import static model.EntityType.VISION_POTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the constructor for PlayerComponent
 */
public class PlayerComponentTest {
    Entity myPlayer = new Entity();
    public PlayerComponentTest() {
        myPlayer.setType(EntityType.PLAYER);
        myPlayer.addComponent(new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1));
    }
    @Test
    public void testConstructor() {
        final PlayerComponent player = new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1);
        assertEquals(player.getMyCharacterComponent().getMyName(), "warrior");
        assertEquals(.1, player.getMyChncBlock());
    }
}
