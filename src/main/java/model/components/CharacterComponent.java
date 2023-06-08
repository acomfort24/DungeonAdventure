package model.components;

import com.almasb.fxgl.entity.component.Component;

public class CharacterComponent extends Component {
    private final int myMaxDmg;
    private final int myMinDmg;

    private final int myAtkSpd;
    private final double myChncHit;


    private final int myMaxHealth;
    private final String myName;

    public CharacterComponent(final int theMinDmg, final int theMaxDmg, final int theAtkSpd,
                              final Double theChncHit, final int theHealth,
                              final String theName) {
        myMinDmg = theMinDmg;
        myMaxDmg = theMaxDmg;
        myAtkSpd = theAtkSpd;
        myChncHit = theChncHit;
        myMaxHealth = theHealth;
        myName = theName;

    }
    public int getMyMinDmg() {
        return myMinDmg;
    }

    public int getMyMaxDmg() {
        return myMaxDmg;
    }

    public int getMyAtkSpd() {
        return myAtkSpd;
    }

    public double getMyChncHit() {
        return myChncHit;
    }

    public int getMyMaxHealth() {
        return myMaxHealth;
    }
    public String getMyName() {
        return myName;
    }

}
