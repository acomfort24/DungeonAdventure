package controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.text.Text;
import model.EntityType;
import model.components.CharacterComponent;
import model.components.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class HealthController {
    public static void loseHealth(int theAmount){
        Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        HealthIntComponent health = player.getComponent(HealthIntComponent.class);
        System.out.println(health.getValue());

        health.setValue(health.getValue() + theAmount);
        System.out.println(health.getValue());
        if (health.getValue() <= 0) {
            gameOver();
        }
//        if (health.getValue() > player.getComponent (myCharacterComponent.class).getMyMaxHealth()) {
//            health.setValue(player.getComponent(myCharacterComponent.class).getMyMaxHealth());
//        }
    }
    private static void gameOver() {
        getDialogService().showMessageBox("Game over man.", FXGL.getGameController()::gotoMainMenu);
    }
}
