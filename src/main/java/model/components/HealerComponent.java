package model.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;

public class HealerComponent extends Component {
    public int Heal(int theMinHeal, int theMaxHeal) {
        return FXGLMath.random(theMinHeal, theMaxHeal);
    }
}
