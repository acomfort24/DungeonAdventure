package model.components;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.image.Image;


public class PillarComponent extends Component {
    public PillarComponent() {
        super();
        final Image image = image("pillar.png");
    }
}
