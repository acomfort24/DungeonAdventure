package model.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;
/**
 * The HealerComponent class represents a component that provides healing functionality.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class HealerComponent extends Component {
    /**
     * Generates a random healing value within the specified range.
     *
     * @param theMinHeal the minimum healing value
     * @param theMaxHeal the maximum healing value
     * @return a randomly generated healing value
     */
    public int Heal(final int theMinHeal, final int theMaxHeal) {
        return FXGLMath.random(theMinHeal, theMaxHeal);
    }
}
