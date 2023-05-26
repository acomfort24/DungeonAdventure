package model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

public class PlayerDoorHandler extends CollisionHandler {
    public PlayerDoorHandler() {
        super(EntityType.PLAYER, EntityType.DOOR);
    }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theD) {
        
        FXGL.getGameWorld().getProperties().increment("playerX", 1);
    }
}
