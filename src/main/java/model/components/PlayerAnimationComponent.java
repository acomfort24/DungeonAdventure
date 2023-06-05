package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import kotlin.Pair;
import model.EntityType;

import java.awt.*;
import java.util.List;

public class PlayerAnimationComponent extends Component {

      final private AnimatedTexture texture;
      final private AnimationChannel animIdle, animWalk, animDeath;

    public PlayerAnimationComponent(String theHeroType) {
        animIdle = new AnimationChannel(FXGL.image(theHeroType + "IdleSheet.png"), Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,96,110)),
                new Pair<>(1, new FrameData(96,0,96,110)),
                new Pair<>(2, new FrameData(96+96,0,96,110)),
                new Pair<>(3, new FrameData(96+96+96,0,96,110))
                ));

        animWalk = new AnimationChannel(FXGL.image(theHeroType + "RunSheet.png"),Duration.seconds(1), List.of(
                new Pair<>(0, new FrameData(0,0,96,110)),
                new Pair<>(1, new FrameData(198,0,96,110)),
                new Pair<>(2, new FrameData(392,0,96,110)),
                new Pair<>(3, new FrameData(580,0,96,110)),
                new Pair<>(4, new FrameData(770,0,96,110)),
                new Pair<>(5, new FrameData(964,0,96,110))
        ));

        animDeath = new AnimationChannel(FXGL.image(theHeroType + "DeathSheet.png"),Duration.seconds(2), List.of(
                new Pair<>(0, new FrameData(66,0,64,110)),
                new Pair<>(1, new FrameData(258,0,96,110)),
                new Pair<>(2, new FrameData(450,0,96,110)),
                new Pair<>(3, new FrameData(642,0,96,110)),
                new Pair<>(4, new FrameData(834,0,96,110)),
                new Pair<>(5, new FrameData(1026,0,96,110))
        ));

        texture = new AnimatedTexture(animIdle);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(48,48));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double theTPF) {

        if(FXGL.getGameWorld().getEntitiesByComponent(PlayerComponent.class).get(0).getComponent(PhysicsComponent.class).isMoving()) {

            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animWalk);

            }

        } else {
            if (texture.getAnimationChannel() == animWalk) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }




}
