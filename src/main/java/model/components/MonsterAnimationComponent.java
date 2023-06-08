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

public class MonsterAnimationComponent extends Component {
    /** */
    private final AnimatedTexture myTexture;
    /** */
    private final AnimationChannel myAnimIdle;
    
    public MonsterAnimationComponent(final String theImageFile) {
        myAnimIdle = new AnimationChannel(FXGL.image(theImageFile), Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,96,97)),
                new Pair<>(1, new FrameData(96,0,96,97)),
                new Pair<>(2, new FrameData(96+96,0,96,97)),
                new Pair<>(3, new FrameData(96+96+96,0,96,97))
        ));

        myTexture = new AnimatedTexture(myAnimIdle);
        myTexture.loop();
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(48,48));
        entity.getViewComponent().addChild(myTexture);
    }
}
