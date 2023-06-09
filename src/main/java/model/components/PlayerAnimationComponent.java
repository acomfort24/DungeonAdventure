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

public class PlayerAnimationComponent extends Component {
    /** */
    private final AnimatedTexture myTexture;
    /** */
    private final AnimationChannel myAnimIdle;
    /** */
    private final AnimationChannel myAnimWalk;

    public PlayerAnimationComponent(final String theHeroType) {
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

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(40, 48));
        entity.getViewComponent().addChild(myTexture);
    }

    @Override
    public void onUpdate(final double theTPF) {
        if (FXGL.getGameWorld().getEntitiesByComponent(PlayerComponent.class)
                .get(0).getComponent(PhysicsComponent.class).isMoving()) {

            if (myTexture.getAnimationChannel() == myAnimIdle) {
                myTexture.loopAnimationChannel(myAnimWalk);
            }

        } else {
            if (myTexture.getAnimationChannel() == myAnimWalk) {
                myTexture.loopAnimationChannel(myAnimIdle);
            }
        }
    }
}
