package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import controller.InventoryController;
import model.EntityType;
import model.components.PlayerComponent;
import model.dungeonmap.Dungeon;

/**
 * Handles collisions between the player and items.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerItemHandler extends CollisionHandler {
    /** The dungeon object representing the game world. */
    private final Dungeon myDungeon;
    /**
     * Constructs a PlayerItemHandler.
     * Specifies the entity types for player and item entities.
     *
     * @param theEntityType the entity type for item entities
     * @param theDungeon    the dungeon object representing the game world
     */
    public PlayerItemHandler(final EntityType theEntityType, final Dungeon theDungeon) {
        super(EntityType.PLAYER, theEntityType);
        myDungeon = theDungeon;
    }

    /**
     * Handles the beginning of a collision between the player and an item.
     * Adds the item to the player's inventory, updates the dungeon map,
     * and removes the item from the game world.
     *
     * @param theP the entity representing the player
     * @param theI   the entity representing the item
     */
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theI) {
        final String itemType = theI.getType().toString();
        theP.getComponent(PlayerComponent.class).getInventory().incrementQuantity(theI, 1);
        if ("VISION_POTION".equals(itemType)) {
            myDungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setVisPot(false);
        } else if ("HEALTH_POTION".equals(itemType)) {
            myDungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setHealPot(false);
        }
        theI.removeFromWorld();
    }

}
