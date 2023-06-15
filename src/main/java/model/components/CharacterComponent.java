package model.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * The CharacterComponent class represents the character attributes of an entity.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class CharacterComponent extends Component {
    /** The maximum amount of damage a character may deal. */
    private final int myMaxDmg;
    /** The minimum amount of damage a character may deal. */
    private final int myMinDmg;
    /** The attack speed of the character. */
    private final double myAtkSpd;
    /** The chance to deal damage to a character. */
    private final double myChncHit;
    /** The maximum health of the character. */
    private final int myMaxHealth;
    /** The name of the character. */
    private final String myName;

    /**
     * Constructs a new CharacterComponent with the specified attributes.
     *
     * @param theMinDmg   the minimum damage the character can deal
     * @param theMaxDmg   the maximum damage the character can deal
     * @param theAtkSpd   the attack speed of the character
     * @param theChncHit  the chance of a successful hit by the character
     * @param theHealth   the maximum health of the character
     * @param theName     the name of the character
     */
    public CharacterComponent(final int theMinDmg, final int theMaxDmg,
                              final int theAtkSpd, final double theChncHit,
                              final int theHealth, final String theName) {
        super();
        myMinDmg = theMinDmg;
        myMaxDmg = theMaxDmg;
        myAtkSpd = (10.0 - theAtkSpd) / 10.0;
        myChncHit = theChncHit;
        myMaxHealth = theHealth;
        myName = theName;

    }
    /**
     * Returns the minimum damage of the character.
     *
     * @return the minimum damage value
     */
    public int getMinDmg() {
        return myMinDmg;
    }
    /**
     * Returns the maximum damage of the character.
     *
     * @return the maximum damage value
     */
    public int getMaxDmg() {
        return myMaxDmg;
    }
    /**
     * Returns the attack speed of the character.
     *
     * @return the attack speed value
     */
    public double getAtkSpd() {
        return myAtkSpd;
    }
    /**
     * Returns the chance of a successful hit by the character.
     *
     * @return the chance to hit value
     */
    public double getChncHit() {
        return myChncHit;
    }
    /**
     * Returns the maximum health of the character.
     *
     * @return the maximum health value
     */
    public int getMaxHealth() {
        return myMaxHealth;
    }
    /**
     * Returns the name of the character.
     *
     * @return the name of the character
     */
    public String getName() {
        return myName;
    }

}
