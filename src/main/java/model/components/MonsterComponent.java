package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import model.EntityType;

/**
 * A component representing a monster entity.
 * This component handles the behavior and attributes of a monster.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */

public class MonsterComponent extends Component {
    /**
     * Constructs a new MonsterComponent.
     */
    public MonsterComponent() {
        super();
    }
    /**
     * Called when the game state is updated.
     * Updates the monster's behavior to move towards the player entity.
     *
     * @param theTpf the time per frame
     */
    @Override
    public void onUpdate(final double theTpf) {
        final Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        
        if (getEntity().distance(player) > 48) {
            getEntity().translateTowards(player.getPosition(), 2.75);
        }
        
        if (getEntity().getX() < player.getX()) {
            getEntity().setScaleX(1);
        } else {
            getEntity().setScaleX(-1);
        }
    }
}