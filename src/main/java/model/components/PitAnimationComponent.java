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


public class PitAnimationComponent extends Component {

    private final AnimatedTexture myTexture;
    private final AnimationChannel myAnimSpikes;

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

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(myTexture);
    }
}
