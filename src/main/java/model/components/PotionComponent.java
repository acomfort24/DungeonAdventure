package model.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.image.Image;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class PotionComponent extends Component {
    
    public PotionComponent() {
        Image image = image("healthpotion.png");
    }

}