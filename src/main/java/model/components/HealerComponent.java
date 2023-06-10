package model.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;

public class HealerComponent extends Component {
    
    private HealerComponent() { }
    public int heal(final int theMinHeal, final int theMaxHeal) {
        return FXGLMath.random(theMinHeal, theMaxHeal);
    }
}
