package controller.collisionhandlers;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.util.Duration;
import model.EntityType;
import model.components.MonsterComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;

/**
 * Handles collisions between the player and monsters.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerMonsterHandler extends CollisionHandler {
    /**
     * Constructs a PlayerMonsterHandler.
     * Specifies the entity types for player and monster entities.
     */
    public PlayerMonsterHandler() {
        super(EntityType.PLAYER, EntityType.MONSTER);
    }

    /**
     * Handles the beginning of a collision between the player and a monster.
     * Performs animations to move the player and monster slightly,
     * and inflicts damage on the player based on the monster's damage range.
     *
     * @param thePlayer the entity representing the player
     * @param theMonster the entity representing the monster
     */
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
