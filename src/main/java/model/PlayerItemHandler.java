package model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;

public class PlayerItemHandler extends CollisionHandler {
    
    public PlayerItemHandler() {
        super(EntityType.PLAYER, EntityType.ITEM);
    }
    
    @Override
    protected void onHitBoxTrigger(Entity p, Entity i, HitBox
            boxA, HitBox boxB) { }
    
    @Override
    protected void onCollisionBegin(Entity p, Entity i) {
        i.removeFromWorld();
    }
    
    @Override
    protected void onCollision(Entity p, Entity i) { }
    @Override
    protected void onCollisionEnd(Entity p, Entity i) { }
}
