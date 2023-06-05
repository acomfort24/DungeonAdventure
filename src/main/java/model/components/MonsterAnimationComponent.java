package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;

import java.util.List;

public class MonsterAnimationComponent extends Component {

    final private AnimatedTexture texture;
    final private AnimationChannel animIdle, animDeath;


    public MonsterAnimationComponent(String imageFile) {
        animIdle = new AnimationChannel(FXGL.image(imageFile + "IdleSheet.png"), Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,96,96)),
                new Pair<>(1, new FrameData(96,0,96,96)),
                new Pair<>(2, new FrameData(96+96,0,96,96)),
                new Pair<>(3, new FrameData(96+96+96,0,96,96))
        ));

        animDeath = new AnimationChannel(FXGL.image(imageFile + "DeathSheet.png"), Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,96,96)),
                new Pair<>(1, new FrameData(96,0,96,96)),
                new Pair<>(2, new FrameData(96+96,0,96,96)),
                new Pair<>(3, new FrameData(96+96+96,0,96,96))
        ));


        texture = new AnimatedTexture(animIdle);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(48,48));
        entity.getViewComponent().addChild(texture);
    }

}
