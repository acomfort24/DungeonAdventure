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

public class AttackComponent extends Component {
    /** */
    private final AnimatedTexture myTexture;
    /** */
    private final AnimationChannel myAnimAttack;
    public AttackComponent() {
        final int width = 110;
        final int height = 80;
        
        myAnimAttack = new AnimationChannel(FXGL.image("AttackSheet.png"),
                Duration.seconds(0.5), List.of(
                new Pair<>(0, new FrameData(0, 0, width, height)),
                new Pair<>(1, new FrameData(110, 0, width, height)),
                new Pair<>(2, new FrameData(220, 0, width, height)),
                new Pair<>(3, new FrameData(330, 0, width, height)),
                new Pair<>(4, new FrameData(440, 0, width, height)),
                new Pair<>(5, new FrameData(550, 0, width, height))
        ));
        
        myTexture = new AnimatedTexture(myAnimAttack);
    }
    
    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(myTexture);
        myTexture.play();
    }
}