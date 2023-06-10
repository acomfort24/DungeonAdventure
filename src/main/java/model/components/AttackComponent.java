package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.FrameData;
import java.util.List;
import javafx.util.Duration;
import kotlin.Pair;

/**
 * The AttackComponent class handles the attack animation for an entity.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class AttackComponent extends Component {
    /**
     * The animation channel for the left attack animation.
     */
    private final AnimationChannel myAnimAttackL;
    /**
     * The animation channel for the right attack animation.
     */
    private final AnimationChannel myAnimAttackR;
    /**
     * The player entity.
     */
    private final Entity myPlayer;
    /**
     * Constructs a new AttackComponent.
     */
    public AttackComponent() {
        super();
        myPlayer = FXGL.getWorldProperties().getObject("player");
        final double attackSpeed = myPlayer.getComponent(PlayerComponent.class).getAtkSpeed();
        final int width = 110;
        final int height = 80;
        
        myAnimAttackL = new AnimationChannel(FXGL.image("AttackSheetL.png"),
                Duration.seconds(attackSpeed), List.of(
                new Pair<>(0, new FrameData(550, 0, width, height)),
                new Pair<>(1, new FrameData(440, 0, width, height)),
                new Pair<>(2, new FrameData(330, 0, width, height)),
                new Pair<>(3, new FrameData(220, 0, width, height)),
                new Pair<>(4, new FrameData(110, 0, width, height)),
                new Pair<>(5, new FrameData(0, 0, width, height))
        ));
        
        myAnimAttackR = new AnimationChannel(FXGL.image("AttackSheetR.png"),
                Duration.seconds(attackSpeed), List.of(
                new Pair<>(0, new FrameData(0, 0, width, height)),
                new Pair<>(1, new FrameData(110, 0, width, height)),
                new Pair<>(2, new FrameData(220, 0, width, height)),
                new Pair<>(3, new FrameData(330, 0, width, height)),
                new Pair<>(4, new FrameData(440, 0, width, height)),
                new Pair<>(5, new FrameData(550, 0, width, height))
        ));
    }

    /**
     * Called when the AttackComponent is added to an entity.
     * Adds the attack animation to the entity's view component.
     */
    @Override
    public void onAdded() {
        final AnimatedTexture texture;
        if (myPlayer.getScaleX() < 0) {
            texture = new AnimatedTexture(myAnimAttackL);
        } else {
            texture = new AnimatedTexture(myAnimAttackR);
        }
        entity.getViewComponent().addChild(texture);
        texture.play();
    }
}