package model.components;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.image.Image;

/**
 * A component representing a pillar entity.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PillarComponent extends Component {
    public PillarComponent() {
        super();
        final Image image = image("pillar.png");
    }
}
