package controller.collisionhandlers;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;

/**
 * Handles collisions between the player and pits.
 * When a collision occurs, the player's health is
 * decreased by a random amount between 1 and 20.
 * This handler is used to simulate damage to the player when they fall into a pit.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerPitHandler extends CollisionHandler {
    /**
     * Constructs a new PlayerPitHandler.
     * Defines the collision between the player and pits.
     */
    public PlayerPitHandler() {
        super(EntityType.PLAYER, EntityType.PIT);
    }
    /**
     * Handles the beginning of a collision between the player and a pit.
     * Decreases the player's health by a random amount between 1 and 20.
     *
     * @param theP the player entity
     * @param thePill the pit entity
     */
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity thePill) {
        theP.getComponent(HealthDoubleComponent.class).damage(FXGLMath.random(1, 20));
    }
}