package controller.collisionhandlers;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;

public class PlayerPitHandler extends CollisionHandler {
    public PlayerPitHandler() {
        super(EntityType.PLAYER, EntityType.PIT);
    }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity thePill) {
        theP.getComponent(HealthDoubleComponent.class).damage(FXGLMath.random(1, 20));
    }
}