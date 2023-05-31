package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;


public class PlayerPillarHandler extends CollisionHandler {
    
    public PlayerPillarHandler() {
        super(EntityType.PLAYER, EntityType.PILLAR);
    }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity thePill) {
        FXGL.getWorldProperties().increment("pillars", 1);
        thePill.removeFromWorld();
    }
}
