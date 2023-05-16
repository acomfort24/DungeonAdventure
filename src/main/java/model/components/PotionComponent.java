package model.components;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.image.Image;

public class PotionComponent extends Component {
    
    public PotionComponent() {
        super();
        final Image image = image("healthpotion.png");
    }

}