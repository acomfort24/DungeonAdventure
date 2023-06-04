package model.components;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;

public class CharacterComponent extends Component {
    private int myMinDmg;
    private int myMaxDmg;
    private int myAtkSpd;
    private double myChncHit;
    private int myCurHealth;

    private int myMaxHealth;
    private String myName;

    public CharacterComponent(int theMinDmg, int theMaxDmg, int theAtkSpd, Double theChncHit, int theHealth, String theName) {
        myMinDmg = theMinDmg;
        myMaxDmg = theMaxDmg;
        myAtkSpd = theAtkSpd;
        myChncHit = theChncHit;
        myCurHealth = theHealth;
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
