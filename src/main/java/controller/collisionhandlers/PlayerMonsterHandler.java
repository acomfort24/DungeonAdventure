package controller.collisionhandlers;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import controller.InventoryController;
import javafx.util.Duration;
import model.EntityType;
import model.components.CharacterComponent;
import model.components.MonsterComponent;
import model.components.PlayerComponent;
import model.dungeonmap.Dungeon;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;

public class PlayerMonsterHandler extends CollisionHandler {

    public PlayerMonsterHandler() {
        super(EntityType.PLAYER, EntityType.MONSTER);
    }

    @Override
    protected void onCollisionBegin(final Entity thePlayer, final Entity theMonster) {
        double knockback = 40;
        
        if (thePlayer.getX() > theMonster.getX()) {
            knockback *= -1;
        }
        
        animationBuilder()
                .interpolator(Interpolators.LINEAR.EASE_OUT())
                .duration(Duration.seconds(0.4))
                .translate(theMonster)
                .from(theMonster.getPosition())
                .to(theMonster.getPosition().add(knockback, 0))
                .buildAndPlay();
        
        final double minDmg = theMonster.getComponent(MonsterComponent.class).
                getMyCharacterComponent().getMyMinDmg();
        final double maxDmg = theMonster.getComponent(MonsterComponent.class).
                getMyCharacterComponent().getMyMaxDmg();
        
        thePlayer.getComponent(HealthDoubleComponent.class).damage(
                FXGLMath.random(minDmg, maxDmg));
        
    }
}
