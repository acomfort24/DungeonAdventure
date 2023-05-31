package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import controller.InventoryController;
import model.EntityType;
import model.dungeonmap.Dungeon;

public class PlayerItemHandler extends CollisionHandler {
    
    public PlayerItemHandler(final EntityType theEntityType) {
        super(EntityType.PLAYER, theEntityType);
    }
    
    @Override
    protected void onHitBoxTrigger(final Entity theP, final Entity theI, final HitBox
            theBoxA, final HitBox theBoxB) { }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theI) {
        String itemType = theI.getType().toString();
        InventoryController.addItem(itemType);
        Dungeon d = FXGL.getGameWorld().getProperties().getObject("dungeon");
        int x = FXGL.getWorldProperties().getInt("playerX");
        int y = FXGL.getWorldProperties().getInt("playerY");
        if ("vision potion".equals(itemType)) {
            d.get(x, y).setVisPot(false);
        } else if ("health potion".equals(itemType)) {
            d.get(x, y).setHealPot(false);
        }
        theI.removeFromWorld();
    }
    
    @Override
    protected void onCollision(final Entity theP, final Entity theI) { }
    @Override
    protected void onCollisionEnd(final Entity theP, final Entity theI) {
    
    }
}
