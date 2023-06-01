package model.components;

import com.almasb.fxgl.entity.component.Component;

import java.util.Random;

public class HealerComponent extends Component {
    public int Heal(int theMinHeal, int theMaxHeal) {
        Random r = new Random();
        return r.nextInt(theMaxHeal - theMinHeal) + theMinHeal;
    }
}
