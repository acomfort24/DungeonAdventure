package controller.collisionhandlers;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;


/**
 * Handles collisions between the player and the exit.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerExitHandler extends CollisionHandler {
    /**
     * Constructs a PlayerExitHandler.
     * Specifies the entity types for player and exit entities.
     */
    public PlayerExitHandler() {
        super(EntityType.PLAYER, EntityType.EXIT);
    }

    /**
     * Handles the collision between the player and the exit.
     * Displays a congratulatory message and returns to the main menu.
     * Clears the world properties to reset the game state.
     *
     * @param theP the entity representing the player
     * @param theE   the entity representing the exit
     */
    @Override
    protected void onCollision(final Entity theP, final Entity theE) {
        getDialogService().showMessageBox("congrats bucko.", () -> {
            FXGL.getWorldProperties().clear();
            FXGL.getGameController().gotoMainMenu();
        });
    }
}