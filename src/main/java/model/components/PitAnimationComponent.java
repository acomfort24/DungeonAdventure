package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;
import model.EntityType;


import java.util.List;

public class PitAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animSpikes;

    public PitAnimationComponent() {
        animSpikes = new AnimationChannel(FXGL.image("SpikeSheet.png"), Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,960,680)),
                new Pair<>(1, new FrameData(960,0,960,680)),
                new Pair<>(2, new FrameData(960 + 960,0,960,680)),
                new Pair<>(3, new FrameData(960,0,960,680)),
                new Pair<>(4, new FrameData(0,0,960,680))
        ));

        texture = new AnimatedTexture(animSpikes);
        texture.loop();

    }

    @Override
    public void onAdded() {
       entity.getTransformComponent().setScaleOrigin(new Point2D(16,21));
       entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        if (FXGL.getGameWorld().getEntitiesByType(EntityType.PLAYER).get(0).isColliding(FXGL.getGameWorld().getEntitiesByComponent(PitAnimationComponent.class).get(0))) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            texture.stop();
                            cancel();
                        }
                    },
                    4000
            );
        }
    }

}
