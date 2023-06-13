package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.component.Component;
import controller.DungeonApp;
import model.EntityType;

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
    private final String myType;
    /**
     * Constructs a PotionComponent with the specified image.
     *
     * @param theType the type of the potion
     */
    public PotionComponent(final String theType) {
        super();
        myType = theType;
    }

    public void use() {
        if ("health".equals(myType)) {
            FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
                    .getComponent(HealthDoubleComponent.class).restore(25);
        } else {
            DungeonApp.getMyDungeonMap().setRevealedRooms();
        }
    }

    /**
     * Returns the name of the potion.
     *
     * @return the name of the potion
     */
    public String getType() {
        return myType;
    }
}