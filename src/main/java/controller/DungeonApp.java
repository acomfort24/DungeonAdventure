package controller;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.DungeonFactory;
import model.EntityType;
import model.PlayerItemHandler;
import model.components.PlayerComponent;
import org.jetbrains.annotations.NotNull;
import view.DungeonMainMenu;


public final class DungeonApp extends GameApplication {
    /** */
    private Entity myPlayer;

    @Override
    protected void initSettings(final GameSettings theGameSettings) {
        theGameSettings.setWidth(1024);
        theGameSettings.setHeight(768);
        theGameSettings.setTitle("Dungeon Adventure");
        theGameSettings.setVersion("0.1");
        theGameSettings.setDeveloperMenuEnabled(true);
        theGameSettings.setMainMenuEnabled(true);
        theGameSettings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public FXGLMenu newMainMenu() {
                return new DungeonMainMenu();
            }
        });

    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        FXGL.getGameWorld().addEntityFactory(new DungeonFactory());
        FXGL.setLevelFromMap("entrance.tmx");
        myPlayer = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }
    
    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
        getPhysicsWorld().addCollisionHandler(new PlayerItemHandler());
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                myPlayer.getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                myPlayer.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                myPlayer.getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                myPlayer.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                myPlayer.getComponent(PlayerComponent.class).up();
            }

            @Override
            protected void onActionEnd() {
                myPlayer.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                myPlayer.getComponent(PlayerComponent.class).down();
            }

            @Override
            protected void onActionEnd() {
                myPlayer.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.S);
    }

    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

}
