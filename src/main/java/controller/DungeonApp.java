package controller;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

import com.almasb.fxgl.app.FXGLApplication;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.LevelLoader;
import com.almasb.fxgl.entity.level.tiled.TiledMap;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.DungeonFactory;
import model.EntityType;
import model.PlayerDoorHandler;
import model.PlayerItemHandler;
import model.components.PlayerComponent;
import model.dungeonmap.Dungeon;
import org.jetbrains.annotations.NotNull;
import view.DungeonMainMenu;
import java.util.Map;
import java.util.NoSuchElementException;


public final class DungeonApp extends GameApplication {
    /** */
    private Entity myPlayer;
    /** */
    private Dungeon myDungeon;

    @Override
    protected void initSettings(final GameSettings theGameSettings) {
        theGameSettings.setWidth(1152);
        theGameSettings.setHeight(864);
        theGameSettings.setTitle("Dungeon Adventure");
        theGameSettings.setVersion("0.1");
        theGameSettings.setDeveloperMenuEnabled(true);
        theGameSettings.setTicksPerSecond(60);
        //theGameSettings.setMainMenuEnabled(true);
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
        getGameScene().setBackgroundColor(Color.BLACK);
        getGameWorld().addEntityFactory(new DungeonFactory());
        myDungeon = new Dungeon(5,5);
        FXGL.setLevelFromMap(myDungeon.getEntranceMap());
        myPlayer = spawn("player", new Point2D((double) getAppWidth() / 2 - 50, (double) getAppHeight() / 2 - 50));
        myPlayer.setReusable(true);
        set("playerX", myDungeon.getEntranceX());
        set("playerY", myDungeon.getEntranceY());
        myDungeon.display();
        System.out.println("Entrance coords: " + myDungeon.getEntranceX() + " " + myDungeon.getEntranceY()); //these might be flipped?? -brandon
        getWorldProperties().addListener("playerX", (old, now) -> {
            setRoom((int) now, geti("playerY"));
        });
        getWorldProperties().addListener("playerY", (old, now) -> {
            setRoom(geti("playerX"), (int) now);
        });

    }

    private void setRoom(final int num1, final int num2) {
        myPlayer.removeFromWorld();
        String newRoom = myDungeon.get(num1, num2).getRoom();
        FXGL.setLevelFromMap(newRoom);
        myPlayer = spawn("player", getd("spawnX"), getd("spawnY"));
    }
    
    @Override
    protected void initPhysics() {
        final CollisionHandler playerDoor = new PlayerDoorHandler();

        getPhysicsWorld().setGravity(0, 0);
        getPhysicsWorld().addCollisionHandler(new PlayerItemHandler());

        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler());
        getPhysicsWorld().addCollisionHandler(playerDoor.copyFor(EntityType.PLAYER, EntityType.SOUTH_DOOR));
        getPhysicsWorld().addCollisionHandler(playerDoor.copyFor(EntityType.PLAYER, EntityType.WEST_DOOR));
        getPhysicsWorld().addCollisionHandler(playerDoor.copyFor(EntityType.PLAYER, EntityType.EAST_DOOR));
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

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pillars", 0);
        vars.put("spawnX", (double) getAppWidth() / 2 - 50);
        vars.put("spawnY", (double) getAppHeight() / 2 - 50);
    }


    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

}
