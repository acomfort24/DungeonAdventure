package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.EntityType;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getDialogService;

public class PlayerExitHandler extends CollisionHandler {
    
    public PlayerExitHandler() {
        super(EntityType.PLAYER, EntityType.EXIT);
    }
    
    @Override
    protected void onCollision(final Entity theP, final Entity theE) {
        getDialogService().showMessageBox("congrats bucko.", FXGL.getGameController()::gotoMainMenu);
    }
}