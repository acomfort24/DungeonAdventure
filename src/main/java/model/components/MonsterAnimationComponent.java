package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;
/**
 * The MonsterAnimationComponent class represents a component that handles the animation
 * of a monster entity.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class MonsterAnimationComponent extends Component {
    /** The animated texture used for the monster. */
    private final AnimatedTexture myTexture;
    /** The animation channel for the idle animation. */
    private final AnimationChannel myAnimIdle;
    /** The animation channel for the walk animation. */
    private final AnimationChannel myAnimWalk;
    /**
     * Constructs a new MonsterAnimationComponent with the specified image file.
     *
     * @param theImageFile the file path of the monster image
     */
    public MonsterAnimationComponent(final String theImageFile) {
        myAnimIdle = new AnimationChannel(FXGL.image(theImageFile + "IdleSheet.png"),
                Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0, 96, 97)),
                new Pair<>(1, new FrameData(96, 0, 96, 97)),
                new Pair<>(2, new FrameData(96 + 96, 0, 96, 97)),
                new Pair<>(3, new FrameData(96 + 96 + 96, 0, 96, 97))
        ));

        myAnimWalk = new AnimationChannel(FXGL.image(theImageFile + "RunSheet.png"),
                Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(60, 0, 112, 116)),
                new Pair<>(1, new FrameData(240, 0, 112, 116)),
                new Pair<>(2, new FrameData(440, 0, 112, 116)),
                new Pair<>(3, new FrameData(630, 0, 112, 116)),
                new Pair<>(4, new FrameData(822, 0, 112, 116)),
                new Pair<>(5, new FrameData(1016, 0, 112, 116))
        ));

        myTexture = new AnimatedTexture(myAnimIdle);
        myTexture.loop();
    }

    /**
     * Called when the component is added to an entity.
     * This method sets the scale origin of the entity's transform component
     * and adds the animated texture to the entity's view component.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(38, 48));
        entity.getViewComponent().addChild(myTexture);
    }

    /**
     * Called when the game state is updated.
     * Checks if the monster entity is moving and updates the animation accordingly.
     * This will get added back later, but is not currently in use
     * @param theTPF the time per frame
     */

//    @Override
//    public void onUpdate(final double theTPF) {
//        if (FXGL.getGameWorld().getEntitiesByComponent(MonsterComponent.class)
//                .get(0).getComponent(PhysicsComponent.class).isMoving()) {
//
//            if (myTexture.getAnimationChannel() == myAnimIdle) {
//                myTexture.loopAnimationChannel(myAnimWalk);
//            }
//
//        } else {
//            if (myTexture.getAnimationChannel() == myAnimWalk) {
//                myTexture.loopAnimationChannel(myAnimIdle);
//            }
//        }
//    }
}
