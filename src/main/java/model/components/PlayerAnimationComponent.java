package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.state.EntityState;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;
import model.EntityType;

/**
 * Represents a player animation component that handles the animation of a player entity.
 * The component switches between idle and walk animations based on player movement.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerAnimationComponent extends Component {
    /** */
    private static final EntityState DIE = new EntityState("DIE");
    /** */
    private static final EntityState IDLE = new EntityState("IDLE");
    /** */
    private static final EntityState WALK = new EntityState("WALK");
    /**
     * The animated texture representing the player's animation.
     */
    private final AnimatedTexture myTexture;
    /**
     * The animation channel for the walk animation.
     */
    private final AnimationChannel myAnimDie;
    /**
     * The animation channel for the idle animation.
     */
    private final AnimationChannel myAnimIdle;
    /**
     * The animation channel for the walk animation.
     */
    private final AnimationChannel myAnimWalk;
    
    
    
    /**
     * Constructs a PlayerAnimationComponent with the specified hero type.
     *
     * @param theHeroType the hero type determining the sprite sheet to load
     */
    public PlayerAnimationComponent(final String theHeroType) {
        super();
        myAnimIdle = new AnimationChannel(FXGL.image(theHeroType + "IdleSheet.png"),
                Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0, 0, 96, 110)),
                new Pair<>(1, new FrameData(96, 0, 96, 110)),
                new Pair<>(2, new FrameData(96 + 96, 0, 96, 110)),
                new Pair<>(3,  new FrameData(96 + 96 + 96, 0, 96, 110))
                ));

        myAnimWalk = new AnimationChannel(FXGL.image(theHeroType + "RunSheet.png"),
                Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0, 0, 96, 110)),
                new Pair<>(1, new FrameData(198, 0, 96, 110)),
                new Pair<>(2, new FrameData(392, 0, 96, 110)),
                new Pair<>(3, new FrameData(580, 0, 96, 110)),
                new Pair<>(4, new FrameData(770, 0, 96, 110)),
                new Pair<>(5, new FrameData(964, 0, 96, 110))
        ));
        
        myAnimDie = new AnimationChannel(FXGL.image(theHeroType + "DeathSheet.png"),
                Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0, 0, 192, 96)),
                new Pair<>(1, new FrameData(192, 0, 192, 96)),
                new Pair<>(2, new FrameData(384, 0, 192, 96)),
                new Pair<>(3, new FrameData(576, 0, 192, 96)),
                new Pair<>(4, new FrameData(768, 0, 192, 96)),
                new Pair<>(5, new FrameData(960, 0, 192, 96))
        ));

        myTexture = new AnimatedTexture(myAnimIdle);
        myTexture.loop();
    }
    /**
     * Called when this component is added to an entity.
     * Sets the scale origin and adds the animated texture to the entity's view.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(40, 48));
        entity.getViewComponent().addChild(myTexture);
    }
    /**
     * Called on every game update tick.
     * Switches between idle and walk animations based on player movement.
     *
     * @param theTPF the time per frame
     */
    @Override
    public void onUpdate(final double theTPF) {
        final Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        if (player.getComponent(PhysicsComponent.class).isMoving()) {

            if (myTexture.getAnimationChannel().equals(myAnimIdle)) {
                myTexture.loopAnimationChannel(myAnimWalk);
            }

        } else {
            if (myTexture.getAnimationChannel().equals(myAnimWalk)) {
                myTexture.loopAnimationChannel(myAnimIdle);
            }
        }
        if (player.getComponent(HealthDoubleComponent.class).isZero()) {
            myTexture.set(new AnimatedTexture(myAnimDie));
            myTexture.play();
        }
    }
}
