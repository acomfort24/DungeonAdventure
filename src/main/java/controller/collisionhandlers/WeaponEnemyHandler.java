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
import model.dungeonmap.Dungeon;

/**
 * Handles collisions between weapons and enemies.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class WeaponEnemyHandler extends CollisionHandler {
    /** The character component of the player. */
    private final CharacterComponent myCharComp;
    /**
     * Constructs a new WeaponEnemyHandler.
     * Defines the collision between weapons and enemies.
     */
    public WeaponEnemyHandler() {
        super(EntityType.WEAPON, EntityType.MONSTER);
        
        myCharComp = FXGL.getGameWorld().getSingleton(EntityType.PLAYER).
                getComponent(PlayerComponent.class).getMyCharacterComponent();
    }

    /**
     * Handles the beginning of a collision between a weapon and an enemy.
     * Decreases the enemy's health by a random amount between the
     * player's minimum and maximum damage.
     * Applies knockback to the enemy horizontally based on the player's position.
     * If the enemy's health reaches zero, it is removed from the world.
     *
     * @param theW the weapon entity
     * @param theM the enemy entity
     */
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
            final Dungeon dungeon = FXGL.geto("dungeon");
            dungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setMonster(false);
        }
    }
}