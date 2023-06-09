package controller.collisionhandlers;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.util.Duration;
import model.EntityType;
import model.components.CharacterComponent;
import model.components.PlayerComponent;

public class WeaponEnemyHandler extends CollisionHandler {
    /** */
    private final CharacterComponent myCharComp;
    public WeaponEnemyHandler() {
        super(EntityType.WEAPON, EntityType.MONSTER);
        
        myCharComp = FXGL.getGameWorld().getSingleton(EntityType.PLAYER).
                getComponent(PlayerComponent.class).getMyCharacterComponent();
    }
    
    @Override
    protected void onCollisionBegin(final Entity theW, final Entity theM) {
        final var hp = theM.getComponent(HealthDoubleComponent.class);
        hp.damage(FXGLMath.random(myCharComp.getMyMinDmg(), myCharComp.getMyMaxDmg()));
        double knockback = 30;
        // Checking coordinates to determine knockback direction, needs to be modified
        final Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        if (player.getX() > theM.getX()) {
            knockback *= -1;
        }
        
        animationBuilder()
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .duration(Duration.seconds(0.5))
                .translate(theM)
                .from(theM.getPosition())
                .to(theM.getPosition().add(knockback, 0))
                .buildAndPlay();
        
        if (hp.isZero()) {
            theM.removeFromWorld();
        }
    }
}