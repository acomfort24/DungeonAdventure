package model.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;
import model.EntityType;


import java.util.List;

public class PitComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animSpikes;

    public PitComponent() {
        animSpikes = new AnimationChannel(FXGL.image("SpikeSheet.png"), Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,1052,693)),
                new Pair<>(1, new FrameData(1052,0,1052,693)),
                new Pair<>(2, new FrameData(2104,0,1052,693)),
                new Pair<>(3, new FrameData(1052,0,1052,693)),
                new Pair<>(4, new FrameData(0,0,1052,693))
        ));

        texture = new AnimatedTexture(animSpikes);
        texture.play();
        final Entity player = FXGL.geto("player");
        player.getComponent(HealthDoubleComponent.class).damage(FXGLMath.random(1, 20));
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16,21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        if (FXGL.getGameWorld().getEntitiesByType(EntityType.PLAYER).get(0).isColliding(
                FXGL.getGameWorld().getEntitiesByComponent(PitComponent.class).get(0))) {
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
