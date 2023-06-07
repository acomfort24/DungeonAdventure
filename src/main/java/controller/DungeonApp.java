package controller;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.dsl.components.view.GenericBarViewComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import controller.collisionhandlers.PlayerDoorHandler;
import controller.collisionhandlers.PlayerExitHandler;
import controller.collisionhandlers.PlayerItemHandler;
import controller.collisionhandlers.PlayerPillarHandler;

import java.util.*;

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
import view.MapSubScene;


public final class DungeonApp extends GameApplication {
    /** */
    private static Entity myPlayer;
    private static EntityFactory myDungeonFactory;
    public static String myPlayerName;
    public static Map<String, Map<String, String>> myDBData;

    /** */
    private static Dungeon myDungeon;
    private SceneSwapController mySceneSwapController = new SceneSwapController();
    private InventoryController myInventoryController = new InventoryController();

    @Override
    protected void initSettings(final GameSettings theGameSettings) {
//        FXGL.getWorldProperties().clear();
        myDBData = DatabaseController.getAllSqlData();
        theGameSettings.setWidth(1152);
        theGameSettings.setHeight(864);
        theGameSettings.setTitle("Dungeon Adventure");
        theGameSettings.setVersion("0.1");
        theGameSettings.setDeveloperMenuEnabled(true);
        theGameSettings.setTicksPerSecond(60);
        theGameSettings.setMainMenuEnabled(true);

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
    protected void onPreInit() {
        getSaveLoadService().addHandler(new SaveLoadHandler() {
            @Override
            public void onSave(DataFile data) {
                final Bundle bundlePlayer = new Bundle("player");
                final Bundle bundleRoomsBooleans = new Bundle("roomsBooleans");
                final Bundle bundleRoomsNumbers = new Bundle("roomsNumbers");
                final Bundle bundleRoomsTypes = new Bundle("roomsTypes");
                final Bundle bundleInventory = new Bundle("inventory");
                final Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);

                bundlePlayer.put("playerName", myPlayerName);
                bundlePlayer.put("curHealth", player.getComponent(HealthDoubleComponent.class).getValue());
                bundlePlayer.put("pillarsCollected", FXGL.getWorldProperties().getValue("pillars"));

                final String[][] roomsTypes = new String[myDungeon.getWidth()][myDungeon.getHeight()];
                //creates a 2d arraylist of maps of the different booleans of information in each room
                final ArrayList<ArrayList<Map<String, Boolean>>> roomArray = new ArrayList<ArrayList<Map<String, Boolean>>>();


                for (int i=0; i < myDungeon.getWidth(); i++) {
                    final ArrayList<Map<String, Boolean>> rowList = new ArrayList<>();
                    for (int j=0; j < myDungeon.getHeight(); j++) {
                        Map<String, Boolean> map = new HashMap<>();
                        map.put("hasVisPot", myDungeon.get(i, j).hasVisPot());
                        map.put("hasHealPot", myDungeon.get(i, j).hasHealPot());
                        map.put("hasPit", myDungeon.get(i, j).hasPit());
                        map.put("hasMonster", myDungeon.get(i, j).hasMonster());
                        map.put("hasBeenVisited", myDungeon.get(i, j).hasBeenVisited());
                        map.put("hasPillar", myDungeon.get(i, j).hasPillar());
                        rowList.add(map);
                        roomsTypes[i][j] = myDungeon.get(i,j).getType();
                    }
                    roomArray.add(rowList);
                }
                bundleRoomsTypes.put("roomsTypes", roomsTypes);
                bundleRoomsBooleans.put("roomsBooleans", roomArray);
                bundleRoomsNumbers.put("roomsNumbers", myDungeon.getMyDungeon());

                if (PlayerComponent.getMyInventory().hasItem("HEALTH_POTION")) {
                    bundleInventory.put("healthPots", PlayerComponent.getMyInventory().getItemQuantity("HEALTH_POTION"));
                }
                if (PlayerComponent.getMyInventory().hasItem("VISION_POTION")) {
                    bundleInventory.put("visionPots", PlayerComponent.getMyInventory().getItemQuantity("VISION_POTION"));
                }
                
                data.putBundle(bundlePlayer);
                data.putBundle(bundleInventory);
                data.putBundle(bundleRoomsBooleans);
                data.putBundle(bundleRoomsNumbers);
                data.putBundle(bundleRoomsTypes);
            }


            @Override
            public void onLoad(DataFile data) {
                Bundle bundlePlayer = data.getBundle("player");
                Bundle bundleInventory = data.getBundle("inventory");
                Bundle bundleRoomsBooleans = data.getBundle("roomsBooleans");
                Bundle bundleRoomsNumbers = data.getBundle("roomsNumbers");
                Bundle bundleRoomsTypes = data.getBundle("roomsTypes");
                set("loaded", true);


                set("loadedPlayerName", bundlePlayer.get("playerName"));
                set("loadedPlayerHealth", bundlePlayer.get("curHealth"));
                set("loadedPillarsCollected", bundlePlayer.get("pillarsCollected"));

                if (bundleInventory.exists("healthPots")) {
                    set("loadedHealthPots", bundleInventory.get("healthPots"));
                }
                if (bundleInventory.exists("visionPots")) {
                    set("loadedVisionPots", bundleInventory.get("visionPots"));
                }


                set("loadedRoomsBooleans", bundleRoomsBooleans.get("roomsBooleans"));
                set("loadedRoomsNumbers", bundleRoomsNumbers.get("roomsNumbers"));
                set("loadedRoomsTypes", bundleRoomsTypes.get("roomsTypes"));
                loadHelper();
                getGameController().gotoPlay();
            }
        });
    }
    private void loadHelper() {
        myDungeon = new Dungeon(geto("loadedRoomsNumbers"), geto("loadedRoomsTypes"), geto("loadedRoomsBooleans"));
        myPlayerName = gets("loadedPlayerName");
        set("spawnX", (double) getAppWidth() / 2 - 50);
        set("spawnY", (double) getAppHeight() / 2 - 50);
        set("pillars", geti("loadedPillarsCollected"));
        getGameWorld().removeEntityFactory(myDungeonFactory);
        myDungeonFactory = new DungeonFactory(myDBData);
        getGameWorld().addEntityFactory(myDungeonFactory);
        set("dungeon", myDungeon);
        FXGL.setLevelFromMap(myDungeon.getEntranceMap());

        playerSetUp();
        getGameWorld().getSingleton(EntityType.PLAYER).getComponent(HealthDoubleComponent.class).setValue(getd("loadedPlayerHealth"));
        initPhysics();


        if (getWorldProperties().exists("loadedHealthPots")) {
            for (int i = 0; i < geti("loadedHealthPots"); i++) {
                InventoryController.addItem("HEALTH_POTION");
            }
        }
        if (getWorldProperties().exists("loadedVisionPots")) {
            for (int i = 0; i < geti("loadedVisionPots"); i++) {
                InventoryController.addItem("VISION_POTION");
            }
        }


    }
    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.BLACK);
        try {
            myDungeon = new Dungeon(5,5);
            //will change this when we can select class
            myDungeonFactory = new DungeonFactory(myDBData);
            getGameWorld().addEntityFactory(myDungeonFactory);
            set("dungeon", myDungeon);
            FXGL.setLevelFromMap(myDungeon.getEntranceMap());
            playerSetUp();
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void playerSetUp() {
        Map<String, String> heroData = myDBData.get(DungeonApp.myPlayerName);
        set("playerX", myDungeon.getEntranceX());
        set("playerY", myDungeon.getEntranceY());
        var hp = new HealthDoubleComponent(Double.parseDouble(heroData.get("hitPoints")));
        set("playerHP", hp);
        set("playerHPView", new GenericBarViewComponent(0.0, -20.0, Color.RED, hp.valueProperty(), 100.0, 8.0));
        myPlayer = spawn("player");
        myPlayer.setReusable(true);
        set("player", myPlayer);
        getWorldProperties().addListener("playerX", (old, now) -> {
            setRoom((int) now, geti("playerY"));
        });
        getWorldProperties().addListener("playerY", (old, now) -> {
            setRoom(geti("playerX"), (int) now);
        });
    }
    private static void setRoom(final int theX, final int theY) {
        myPlayer.removeFromWorld();
        FXGL.setLevelFromMap(myDungeon.get(theX, theY).getRoom());
        myDungeon.get(theX, theY).setVisited(true);
        spawnRoomEntities(myDungeon.get(theX, theY));
        myPlayer = spawn("player");
    }
    
    private static void spawnRoomEntities(final DungeonRoom theRoom) {
        if (theRoom.hasHealPot()) {
            spawn("health potion");
        }
        if (theRoom.hasVisPot()) {
            spawn("vision potion");
        }
        if (theRoom.hasPit()) {
            spawn("pit");
        }
        if (theRoom.hasPillar()) {
            spawn("pillar");
        }
        if (theRoom.hasMonster()) {
            spawn(theRoom.getMonsterType());
        }
        if ("exit".equals(theRoom.getType())) {
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

    

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
        getPhysicsWorld().addCollisionHandler(new PlayerItemHandler(
                EntityType.HEALTH_POTION, myDungeon));
        getPhysicsWorld().addCollisionHandler(new PlayerItemHandler(
                EntityType.VISION_POTION, myDungeon));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.NORTH_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.SOUTH_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.EAST_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerDoorHandler(EntityType.WEST_DOOR));
        getPhysicsWorld().addCollisionHandler(new PlayerPillarHandler(myDungeon));
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

        onKeyDown(KeyCode.O, () -> {
            final Queue<Point2D> dungeonQueue = new LinkedList<>();
            myDungeon.display();
            System.out.println(geti("playerX") + " " + geti("playerY"));
            for (DungeonRoom[] d : myDungeon.getData()) {
                for (DungeonRoom dr : d) {
                    if ("entrance".equals(dr.getType())
                            || "exit".equals(dr.getType())
                            || "pillar".equals(dr.getType())) {
                        dungeonQueue.add(new Point2D(dr.getX(), dr.getY()));
                    }
                }
            }
            set("devQueue", dungeonQueue);
            return null;
        });

        onKeyDown(KeyCode.P, () -> {
            Queue devQueue = geto("devQueue");
            Point2D point = (Point2D) devQueue.poll();
            set("spawnX", (double) 200);
            set("spawnY", (double) 200);
            setRoom((int) point.getX(), (int) point.getY());
            devQueue.add(point);
            return null;
        });

        onKeyDown(KeyCode.M, () -> {
            FXGL.getSceneService().pushSubScene(new MapSubScene(myDungeon));
            return null;
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("loaded", false);
        vars.put("pillars", 0);
        vars.put("spawnX", (double) getAppWidth() / 2 - 50);
        vars.put("spawnY", (double) getAppHeight() / 2 - 50);
    }
  
    public static void setMyPlayerName(final String thePlayerName) {
        myPlayerName = thePlayerName;
    }

    @Override
    protected void onUpdate(double tpf) {
        if (myPlayer.getComponent(HealthDoubleComponent.class).isZero()) {
            getDialogService().showMessageBox("Game over man.", FXGL.getGameController()::gotoMainMenu);
        }
    }
    
    public static void main(final String[] theArgs) {
        launch(theArgs);
    }
}
