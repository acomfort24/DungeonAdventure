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
 * Represents a pit animation component that adds
 * an animated texture of spikes to a pit entity.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PitAnimationComponent extends Component {
    /**
     * The animated texture representing the spikes' animation.
     */
    private final AnimatedTexture myTexture;
    /**
     * The animation channel defining the frames and duration of the spikes' animation.
     */
    private final AnimationChannel myAnimSpikes;

    /**
     * Constructs a PitAnimationComponent.
     * Initializes the spikes animation with frames and creates the animated texture.
     */
    public PitAnimationComponent() {
        myAnimSpikes = new AnimationChannel(FXGL.image("SpikeSheet.png"),
                Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0, 0, 1052, 693)),
                new Pair<>(1, new FrameData(1052, 0, 1052, 693)),
                new Pair<>(2, new FrameData(2104, 0, 1052, 693)),
                new Pair<>(3,  new FrameData(1052, 0, 1052, 693)),
                new Pair<>(4,  new FrameData(0, 0, 1052, 693))
        ));

        myTexture = new AnimatedTexture(myAnimSpikes);
        myTexture.play();
    }
    /**
     * Called when this component is added to an entity.
     * Sets the scale origin and adds the animated texture to the entity's view.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(myTexture);
    }
}
