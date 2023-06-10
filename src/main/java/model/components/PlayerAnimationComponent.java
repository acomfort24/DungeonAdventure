package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;

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
    /**
     * The animated texture representing the player's animation.
     */
    private final AnimatedTexture myTexture;
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

        myTexture = new AnimatedTexture(myAnimIdle);
        myTexture.loop();
    }
    /**
     * Called when this component is added to an entity.
     * Sets the scale origin and adds the animated texture to the entity's view.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(48, 48));
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
        if (FXGL.getGameWorld().getEntitiesByComponent(PlayerComponent.class).
                get(0).getComponent(PhysicsComponent.class).isMoving()) {

            if (myTexture.getAnimationChannel().equals(myAnimIdle)) {
                myTexture.loopAnimationChannel(myAnimWalk);
            }

        } else {
            if (myTexture.getAnimationChannel().equals(myAnimWalk)) {
                myTexture.loopAnimationChannel(myAnimIdle);
            }
        }
    }
}
