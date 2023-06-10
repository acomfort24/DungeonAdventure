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
    /** The minimum amount of healing the monster can perform. */
    private final int myMinHeal;

    /** The maximum amount of healing the monster can perform. */
    private final int myMaxHeal;

    /** The healer component attached to the monster. */
    private HealerComponent myHealerComponent;

    /** The character component representing the attributes and behavior of the monster. */
    private final CharacterComponent myCharacterComponent;

    /**
     * Constructs a new MonsterComponent.
     *
     * @param theMinHeal the minimum amount of healing the monster can perform
     * @param theMaxHeal the maximum amount of healing the monster can perform
     * @param theMinDmg the minimum damage the monster can inflict
     * @param theMaxDmg the maximum damage the monster can inflict
     * @param theAtkSpd the attack speed of the monster
     * @param theChncHit the chance of the monster hitting its target
     * @param theHealth the maximum health of the monster
     * @param theName the name of the monster
     */
    public MonsterComponent(final int theMinHeal, final int theMaxHeal, final int theMinDmg,
                            final int theMaxDmg, final int theAtkSpd, final Double theChncHit,
                            final int theHealth, final String theName) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg,
                theMaxDmg, theAtkSpd, theChncHit, theHealth, theName);
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
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
            getEntity().translateTowards(player.getPosition(), 2.5);
        }
    }
    /**
     * Retrieves the character component of the monster.
     *
     * @return the character component
     */
    public CharacterComponent getMyCharacterComponent() {
        return myCharacterComponent;
    }
}
