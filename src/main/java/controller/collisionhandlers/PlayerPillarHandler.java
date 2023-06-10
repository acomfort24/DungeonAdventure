package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;
import model.dungeonmap.Dungeon;

/**
 * Handles collisions between the player and pillars.
 * When a collision occurs, the pillar is removed from the world,
 * and the number of pillars is incremented in the game's world properties.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerPillarHandler extends CollisionHandler {
    /** The dungeon instance used to access and modify the dungeon map. */
    private final Dungeon myDungeon;

    /**
     * Constructs a new PlayerPillarHandler with the specified Dungeon instance.
     *
     * @param theDungeon the dungeon instance
     */
    public PlayerPillarHandler(final Dungeon theDungeon) {
        super(EntityType.PLAYER, EntityType.PILLAR);
        myDungeon = theDungeon;
    }

    /**
     * Handles the beginning of a collision between the player and a pillar.
     * Increments the number of pillars in the world properties,
     * marks the corresponding position in the dungeon map as having no pillar,
     * and removes the pillar entity from the world.
     *
     * @param theP the player entity
     * @param thePill the pillar entity
     */
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity thePill) {
        FXGL.getWorldProperties().increment("pillars", 1);
        myDungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setPillar(false);
        thePill.removeFromWorld();
    }
}
