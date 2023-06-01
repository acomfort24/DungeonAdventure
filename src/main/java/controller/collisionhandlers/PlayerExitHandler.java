package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;
public class PlayerExitHandler extends CollisionHandler {
    
    public PlayerExitHandler() {
        super(EntityType.PLAYER, EntityType.EXIT);
    }
    
    @Override
    protected void onCollision(final Entity theP, final Entity theE) {
        //FXGL.getUIFactoryService().
    }
}