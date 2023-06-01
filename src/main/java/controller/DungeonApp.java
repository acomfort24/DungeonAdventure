package controller;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.AnimationBuilder;
import com.almasb.fxgl.app.FXGLApplication;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.view.GenericBarViewComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import controller.collisionhandlers.PlayerDoorHandler;
import controller.collisionhandlers.PlayerExitHandler;
import controller.collisionhandlers.PlayerItemHandler;
import controller.collisionhandlers.PlayerPillarHandler;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.DungeonFactory;
import model.EntityType;
import model.components.PlayerComponent;
import model.dungeonmap.Dungeon;
import model.dungeonmap.DungeonRoom;
import org.jetbrains.annotations.NotNull;
import view.DungeonMainMenu;
import view.GameMenu;


public final class DungeonApp extends GameApplication {
    /** */
    private Entity myPlayer;
    /** */
    private Dungeon myDungeon;
    private SceneSwapController mySceneSwapController = new SceneSwapController();
    private InventoryController myInventoryController = new InventoryController();

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
            public FXGLMenu newGameMenu() {
                return new GameMenu();
            }
        });

    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.BLACK);
        getGameWorld().addEntityFactory(new DungeonFactory());
        myDungeon = new Dungeon(5,5);
        set("dungeon", myDungeon);
        FXGL.setLevelFromMap(myDungeon.getEntranceMap());
        myPlayer = spawn("player");
        myPlayer.setReusable(true);
        set("playerX", myDungeon.getEntranceX());
        set("playerY", myDungeon.getEntranceY());
        myDungeon.display();
        System.out.println(geti("playerX") + " " + geti("playerY"));
        getWorldProperties().addListener("playerX", (old, now) -> {
            setRoom((int) now, geti("playerY"));
        });
        getWorldProperties().addListener("playerY", (old, now) -> {
            setRoom(geti("playerX"), (int) now);
        });
    }

    private void setRoom(final int theX, final int theY) {
        myPlayer.removeFromWorld();
        final DungeonRoom newRoom = myDungeon.get(theX, theY);
        FXGL.setLevelFromMap(newRoom.getRoom());
        spawnRoomEntities(newRoom);
        myPlayer = spawn("player");
    }
    
    private void spawnRoomEntities(final DungeonRoom theRoom) {
        if (theRoom.hasHealPot()) {
            spawn("health potion");
        }
        if (theRoom.hasVisPot()) {
            spawn("vision potion");
        }
        if (theRoom.hasPit()) {
            spawn("pit");
        }
        if ("pillar".equals(theRoom.getType())) {
            spawn("pillar");
        }
        if (theRoom.hasMonster()){
            spawn(randomMonster());
        }
        if ("exit".equals(theRoom.getType())) {
            System.out.println(geti("pillars"));
            if (geti("pillars") == 4) {
                var exit = getGameWorld().create("exit", new SpawnData());
                exit.getComponent(CollidableComponent.class).setValue(true);
                exit.getComponent(BoundingBoxComponent.class).addHitBox(
                        new HitBox(new Point2D(150.0, 119.0), BoundingShape.box(84.0, 61.0)));
                getGameWorld().addEntity(exit);
            } else {
                spawn("exit");
            }
        }
    }

    private String randomMonster() {
        final int num = FXGL.random(1, 2);
        final String monsterType;

        switch (num) {
            case 1 -> monsterType = "skeleton";
            case 2 -> monsterType = "orc";
            default -> throw new IllegalStateException("Unexpected value: " + num);
        }

        return monsterType;
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
        
        getPhysicsWorld().addCollisionHandler(new PlayerItemHandler(EntityType.HEALTH_POTION));
        getPhysicsWorld().addCollisionHandler(new PlayerItemHandler(EntityType.VISION_POTION));

        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.NORTH_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.SOUTH_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.EAST_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.WEST_DOOR));
        
        getPhysicsWorld().addCollisionHandler(new PlayerPillarHandler());
        
        getPhysicsWorld().addCollisionHandler(new PlayerExitHandler());
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
