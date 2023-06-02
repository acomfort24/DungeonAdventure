package model.components;

import com.almasb.fxgl.entity.component.Component;

public class PotionComponent extends Component {
    /** */
    private final String myName;
    public PotionComponent(final String theImage) {
        super();
        myName = theImage.substring(0, theImage.length() - 4);
    }
    public String getName() {
        return myName;
    }
}