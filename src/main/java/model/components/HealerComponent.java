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
    /** */
    private final int myMinHeal;
    /** */
    private final int myMaxHeal;
    
    public HealerComponent(final int theMinHeal, final int theMaxHeal) {
        super();
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }
    /**
     * Generates a random healing value within the specified range.
     *
     * @return a randomly generated healing value
     */
    public int heal() {
        return FXGLMath.random(myMinHeal, myMaxHeal);
    }
}
