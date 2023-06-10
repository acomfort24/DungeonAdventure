package controller.collisionhandlers;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.util.Duration;
import model.EntityType;
import model.components.MonsterComponent;

public class PlayerMonsterHandler extends CollisionHandler {

    public PlayerMonsterHandler() {
        super(EntityType.PLAYER, EntityType.MONSTER);
    }

    @Override
    protected void onCollisionBegin(final Entity thePlayer, final Entity theMonster) {
        animationBuilder()
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .duration(Duration.seconds(0.5))
                .translate(thePlayer)
                .from(thePlayer.getPosition())
                .to(thePlayer.getPosition().add(-50, 0))
                .buildAndPlay();
            
        animationBuilder()
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .duration(Duration.seconds(0.5))
                .translate(theMonster)
                .from(theMonster.getPosition())
                .to(theMonster.getPosition().add(50, 0))
                .buildAndPlay();
            
        final double minDmg = theMonster.getComponent(MonsterComponent.class).
                getMyCharComponent().getMyMinDmg();
        final double maxDmg = theMonster.getComponent(MonsterComponent.class).
                getMyCharComponent().getMyMaxDmg();
        
        thePlayer.getComponent(HealthDoubleComponent.class).damage(
                FXGLMath.random(minDmg, maxDmg));
        
    }
}
