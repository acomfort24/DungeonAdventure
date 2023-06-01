package model.components;

import com.almasb.fxgl.entity.component.Component;

public class CharacterComponent extends Component {
    private int myMinDmg;
    private int myMaxDmg;
    private int myAtkSpd;
    private double myChncHit;
    private String myName;

    public CharacterComponent(int theMinDmg, int theMaxDmg, int theAtkSpd, Double theChncHit, String theName) {
        myMinDmg = theMinDmg;
        myMaxDmg = theMaxDmg;
        myAtkSpd = theAtkSpd;
        myChncHit = theChncHit;
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


    public String getMyName() {
        return myName;
    }

}
