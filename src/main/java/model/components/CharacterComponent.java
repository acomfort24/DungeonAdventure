package model.components;

import com.almasb.fxgl.entity.component.Component;

public class CharacterComponent extends Component {
    private final int myMaxDmg;
    private final int myMinDmg;

    private final double myAtkSpd;
    private final double myChncHit;
    private final int myMaxHealth;
    private final String myName;

    public CharacterComponent(final int theMinDmg, final int theMaxDmg, final int theAtkSpd,
                              final double theChncHit, final int theHealth,
                              final String theName) {
        myMinDmg = theMinDmg;
        myMaxDmg = theMaxDmg;
        myAtkSpd = (10.0 - theAtkSpd) / 10.0;
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

    public double getMyAtkSpd() {
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
