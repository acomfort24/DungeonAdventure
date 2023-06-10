package model;

import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import controller.InventoryController;
import model.components.PlayerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chad
 * Tests health values/methods related to the Player Entity
 *
 */
// I wanted to test collisions as well, but that is not within the scope of this project.
public class PlayerEntityTest {
    private final Entity player = new Entity();
    private PlayerEntityTest() {
        player.addComponent(new HealthDoubleComponent(50));
        player.addComponent(new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1));
    }

    @BeforeEach
    public void setHealth() {
        player.getComponent(HealthDoubleComponent.class).setValue(50);
    }
    @Test
    public void takeDmgTest() {
        HealthDoubleComponent health = player.getComponent(HealthDoubleComponent.class);
        health.damage(20);
        assertEquals(30, health.getValue());
    }
    @Test
    public void healDmgTest() {
        HealthDoubleComponent health = player.getComponent(HealthDoubleComponent.class);
        health.damage(26);
        health.restore(25);
        assertEquals(49, health.getValue());
    }
    @Test
    public void healDmgOverTest() {
        HealthDoubleComponent health = player.getComponent(HealthDoubleComponent.class);
        health.restore(25);
        assertEquals(50, health.getValue());
    }
}
