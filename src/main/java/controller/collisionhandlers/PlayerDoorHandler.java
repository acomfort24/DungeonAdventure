package controller.collisionhandlers;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppHeight;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;

/**
 * Handles collisions between the player and doors.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerDoorHandler extends CollisionHandler {
    /** The property key for player's Y coordinate. */
    private static final String PLAYER_Y_PROPERTY = "playerY";
    /** The property key for player's X coordinate. */
    private static final String PLAYER_X_PROPERTY = "playerX";

    /**
     * Constructs a PlayerDoorHandler with the specified entity type.
     *
     * @param theEntityType the entity type of the door
     */
    public PlayerDoorHandler(final EntityType theEntityType) {
        super(EntityType.PLAYER, theEntityType);
    }

    /**
     * Handles the collision between the player and a door.
     * Determines the type of door collided with and performs actions accordingly.
     * Updates the spawn positions and player coordinates based on the door type.
     *
     * @param theP the entity representing the player
     * @param theD   the entity representing the door
     */
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theD) {
        final String type = theD.getType().toString();
        switch (type) {
            case "NORTH_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) getAppWidth() / 2 - 48);
                FXGL.getWorldProperties().setValue("spawnY", (double) getAppHeight() - 160);
                FXGL.getWorldProperties().increment(PLAYER_Y_PROPERTY, -1);
            }
            case "SOUTH_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) getAppWidth() / 2 - 48);
                FXGL.getWorldProperties().setValue("spawnY", (double) 150);
                FXGL.getWorldProperties().increment(PLAYER_Y_PROPERTY, 1);
            }
            case "WEST_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) getAppWidth() - 170);
                FXGL.getWorldProperties().setValue("spawnY", (double) getAppHeight() / 2 - 48);
                FXGL.getWorldProperties().increment(PLAYER_X_PROPERTY, -1);
            }
            case "EAST_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) 70);
                FXGL.getWorldProperties().setValue("spawnY", (double) getAppHeight() / 2 - 48);
                FXGL.getWorldProperties().increment(PLAYER_X_PROPERTY, 1);
            }
            default -> {
                // handle unexpected door types here
            }
        }

    }
}