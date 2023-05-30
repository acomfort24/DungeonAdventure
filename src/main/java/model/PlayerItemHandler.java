package model;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import controller.InventoryController;

public class PlayerItemHandler extends CollisionHandler {
    
    public PlayerItemHandler() {
        super(EntityType.PLAYER, EntityType.HEALTH_POTION);
    }
    
    @Override
    protected void onHitBoxTrigger(final Entity theP, final Entity theI, final HitBox
            theBoxA, final HitBox theBoxB) { }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theI) {
        theI.removeFromWorld();
    }
    
    @Override
    protected void onCollision(final Entity theP, final Entity theI) { }
    @Override
    protected void onCollisionEnd(final Entity theP, final Entity theI) {
        InventoryController.addItem(theI.getType().toString());
    }
}
