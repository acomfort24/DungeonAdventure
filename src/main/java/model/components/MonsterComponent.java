package model.components;

import com.almasb.fxgl.entity.component.Component;

import java.util.Random;

public class MonsterComponent extends Component implements Healable {
    private int myMinHeal;
    private int myMaxHeal;
    private CharacterComponent myCharacterComponent;
    MonsterComponent(int theMinHeal, int theMaxHeal, int theMinDmg, int theMaxDmg, int theAtkSpd, Double theChncHit, String theName) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg, theMaxDmg, theAtkSpd, theChncHit, theName);
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }

    @Override
    public int Heal() {
        Random r = new Random();
        return r.nextInt(myMaxHeal-myMinHeal) + myMinHeal;
    }
}
