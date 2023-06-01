package model.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.image.Image;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class PillarComponent extends Component {
    public PillarComponent() {
        super();
        final Image image = image("pillar.png");
    }
}
