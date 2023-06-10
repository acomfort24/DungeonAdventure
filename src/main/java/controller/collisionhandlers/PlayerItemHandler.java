package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import controller.InventoryController;
import model.EntityType;
import model.dungeonmap.Dungeon;

public class PlayerItemHandler extends CollisionHandler {
    /** */
    private final Dungeon myDungeon;
    
    public PlayerItemHandler(final EntityType theEntityType, final Dungeon theDungeon) {
        super(EntityType.PLAYER, theEntityType);
        myDungeon = theDungeon;
    }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theI) {
        final String itemType = theI.getType().toString();
        InventoryController.addItem(itemType);
        if ("VISION_POTION".equals(itemType)) {
            myDungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setVisPot(false);
        } else if ("HEALTH_POTION".equals(itemType)) {
            myDungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setHealPot(false);
        }
        theI.removeFromWorld();
    }
}
