package model.components;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class PillarComponent extends Component {
    public PillarComponent() {
        super();
    }

    public void use() {
        final int pillars = FXGL.geti("pillars");
        switch (pillars) {
            case 0: getDialogService().showMessageBox("You have collected 0 pillars."
                    + " Collect 4 more pillars to activate the exit shrine!");

            case 1: getDialogService().showMessageBox("You have collected 1 pillar."
                    + " Collect 3 more pillars to activate the exit shrine!");

            case 2: getDialogService().showMessageBox("You have collected 2 pillar."
                    + " Collect 2 more pillars to activate the exit shrine!");

            case 3: getDialogService().showMessageBox("You have collected 3 pillar."
                    + " Collect 1 more pillar to activate the exit shrine!");

            case 4: getDialogService().showMessageBox("You have collected all 4 pillars."
                    + " Find the exit shrine to escape the dungeon!");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pillars);
        }

    }
}