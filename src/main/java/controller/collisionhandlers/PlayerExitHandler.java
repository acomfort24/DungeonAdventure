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
public class PlayerExitHandler extends CollisionHandler {
    
    public PlayerExitHandler() {
        super(EntityType.PLAYER, EntityType.EXIT);
    }
    
    @Override
    protected void onCollision(final Entity theP, final Entity theE) {
        var text = FXGL.getUIFactoryService().newText("congrats bucko", Color.WHITE, 48.0);
        var vbox = new VBox(text);
        var root = new StackPane(vbox);
        root.setTranslateX(400.0);
        root.setTranslateY(300.0);
        FXGL.getGameScene().getContentRoot().getChildren().addAll(root);
    }
}