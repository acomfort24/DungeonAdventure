package model.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * Represents a potion component that holds the name
 * and image of the potion.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PotionComponent extends Component {
    /**
     * The name of the potion.
     */
    private final String myName;
    /**
     * Constructs a PotionComponent with the specified image.
     *
     * @param theImage the image filename of the potion
     */
    public PotionComponent(final String theImage) {
        super();
        myName = theImage.substring(0, theImage.length() - 4);
    }
    /**
     * Returns the name of the potion.
     *
     * @return the name of the potion
     */
    public String getName() {
        return myName;
    }
}