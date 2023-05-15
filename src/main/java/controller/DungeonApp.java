package controller;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.EntityType;
import model.DungeonFactory;
import model.components.PlayerComponent;
import model.PlayerItemHandler;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class DungeonApp extends GameApplication {
    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1024);
        gameSettings.setHeight(768);
        gameSettings.setTitle("Dungeon Adventure");
        gameSettings.setVersion("0.1");
        gameSettings.setDeveloperMenuEnabled(true);
//        gameSettings.setMainMenuEnabled(true);
//        gameSettings.setSceneFactory(new SceneFactory(){
//            @Override
//            public FXGLMenu newMainMenu(){
//                return new DungeonMainMenu();
//            }
//        });

    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        FXGL.getGameWorld().addEntityFactory(new DungeonFactory());
        FXGL.setLevelFromMap("entrance.tmx");
        player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        
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
                player.getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).up();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).down();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.S);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
