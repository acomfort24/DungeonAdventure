package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import model.EntityType;

public class MonsterComponent extends Component {
    /** */
    private final int myMinHeal;
    /** */
    private final int myMaxHeal;
    /** */
    private HealerComponent myHealerComponent;
    /** */
    private final CharacterComponent myCharComponent;
    
    public MonsterComponent(final int theMinHeal, final int theMaxHeal, final int theMinDmg,
                            final int theMaxDmg, final int theAtkSpd, final Double theChncHit,
                            final int theHealth, final String theName) {
        super();
        myCharComponent = new CharacterComponent(theMinDmg,
                theMaxDmg, theAtkSpd, theChncHit, theHealth, theName);
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }
    
    @Override
    public void onUpdate(final double theTpf) {
        final Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        
        if (getEntity().distance(player) > 48) {
            getEntity().translateTowards(player.getPosition(), 2.5);
        }
    }
    
    public CharacterComponent getMyCharComponent() {
        return myCharComponent;
    }
}
