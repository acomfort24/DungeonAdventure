package model.components;

import com.almasb.fxgl.entity.component.Component;

public class MonsterComponent extends Component {
    private final int myMinHeal;
    private final int myMaxHeal;

    //doesn't get assigned a value here. Gets assigned through flgl
    private HealerComponent myHealerComponent;
    private final CharacterComponent myCharacterComponent;
    public MonsterComponent(final int theMinHeal, final int theMaxHeal, final int theMinDmg,
                            final int theMaxDmg, final int theAtkSpd, final Double theChncHit,
                            final int theHealth, final String theName) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg,
                theMaxDmg, theAtkSpd, theChncHit, theHealth, theName);
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }
}
