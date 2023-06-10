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
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import com.almasb.fxgl.ui.Position;
import controller.collisionhandlers.*;
import java.awt.*;
import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.DungeonFactory;
import model.EntityType;
import model.components.PlayerComponent;
import model.dungeonmap.Dungeon;
import model.dungeonmap.DungeonRoom;
import org.jetbrains.annotations.NotNull;
import view.DungeonMainMenu;
import view.GameMenu;
import view.MapSubScene;

/**
 * This class represents the main application for the Dungeon Adventure game.
 * It extends the GameApplication class provided by FXGL.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */

public final class DungeonApp extends GameApplication {
    /** The name of the character.*/
    private static String myCharacterName;
    /** The player in the dungeon. */
    private static Entity myPlayer;
    /** The factory used to spawn entities. */
    private static EntityFactory myDungeonFactory;
    /** The name of the player. */
    private static String myPlayerName;
    /** The database storing character data. */
    private static Map<String, Map<String, String>> myDBData;
    /** The dungeon to be explored.  */
    private static Dungeon myDungeon;
    /** The map of the dungeon. */
    private static MapSubScene myDungeonMap;

    /**
     * Initializes the game settings for the Dungeon Adventure game.
     *
     *@param theGameSettings The game settings to be initialized.
     */
    @Override
    protected void initSettings(final GameSettings theGameSettings) {
        try {
            myDBData = DatabaseController.getAllSqlData();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
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
            @Override
            @NotNull
            public FXGLMenu newGameMenu() {
                return new GameMenu();
            }
        });

    }
    /**
     * Performs pre-initialization tasks for the Dungeon Adventure game.
     * Adds a save/load handler to the SaveLoadService.
     */
    @Override
    protected void onPreInit() {
        getSaveLoadService().addHandler(new SaveLoadHandler() {
            /**
             * This method is called to save the game data to a DataFile.
             *
             * @param theData The DataFile object to which the game data will be saved.
             */
            @Override
            public void onSave(@NotNull final DataFile theData) {
                final Bundle bundlePlayer = new Bundle("player");
                final Bundle bundleRoomsBooleans = new Bundle("roomsBooleans");
                final Bundle bundleRoomsNumbers = new Bundle("roomsNumbers");
                final Bundle bundleRoomsTypes = new Bundle("roomsTypes");
                final Bundle bundleRoomsMonsters = new Bundle("roomsMonsters");
                final Bundle bundleInventory = new Bundle("inventory");
                final Bundle bundleMap = new Bundle("map");
                final Entity player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);

                bundlePlayer.put("characterName", myCharacterName);
                bundlePlayer.put("playerName", myPlayerName);
                bundlePlayer.put("curHealth",
                        player.getComponent(HealthDoubleComponent.class).getValue());
                bundlePlayer.put("pillarsCollected",
                        FXGL.getWorldProperties().getValue("pillars"));
                bundlePlayer.put("playerX", geti("playerX"));
                bundlePlayer.put("playerY", geti("playerY"));

                final String[][] roomsMonsters =
                        new String[myDungeon.getWidth()][myDungeon.getHeight()];
                final String[][] roomsTypes =
                        new String[myDungeon.getWidth()][myDungeon.getHeight()];
                //creates a 2d arraylist of maps of the different booleans in each room
                final ArrayList<ArrayList<Map<String, Boolean>>> roomArray = new ArrayList<>();

                for (int i = 0; i < myDungeon.getWidth(); i++) {
                    final ArrayList<Map<String, Boolean>> rowList = new ArrayList<>();
                    for (int j = 0; j < myDungeon.getHeight(); j++) {
                        final Map<String, Boolean> map = new HashMap<>();
                        map.put("hasVisPot", myDungeon.get(i, j).hasVisPot());
                        map.put("hasHealPot", myDungeon.get(i, j).hasHealPot());
                        map.put("hasPit", myDungeon.get(i, j).hasPit());
                        map.put("hasMonster", myDungeon.get(i, j).hasMonster());
                        map.put("hasBeenVisited", myDungeon.get(i, j).hasBeenVisited());
                        map.put("hasPillar", myDungeon.get(i, j).hasPillar());
                        rowList.add(map);
                        roomsTypes[i][j] = myDungeon.get(i, j).getType();
                        if (map.get("hasMonster")) {
                            roomsMonsters[i][j] = myDungeon.get(i, j).getMonsterType();
                        }
                    }
                    roomArray.add(rowList);
                }

                bundleRoomsTypes.put("roomsTypes", roomsTypes);
                bundleRoomsMonsters.put("roomsMonsters", roomsMonsters);
                bundleRoomsBooleans.put("roomsBooleans", roomArray);
                bundleRoomsNumbers.put("roomsNumbers", myDungeon.getMyDungeon()); //


                ArrayList<Point> revealedRooms = new ArrayList<>(myDungeonMap.getRevealedRooms());

                bundleMap.put("revealedRooms", revealedRooms);


                if (PlayerComponent.getMyInventory().hasItem("HEALTH_POTION")) {
                    bundleInventory.put("healthPots",
                            PlayerComponent.getMyInventory().getItemQuantity("HEALTH_POTION"));
                }
                if (PlayerComponent.getMyInventory().hasItem("VISION_POTION")) {
                    bundleInventory.put("visionPots",
                            PlayerComponent.getMyInventory().getItemQuantity("VISION_POTION"));
                }
                theData.putBundle(bundlePlayer);
                theData.putBundle(bundleInventory);
                theData.putBundle(bundleRoomsBooleans);
                theData.putBundle(bundleRoomsNumbers);
                theData.putBundle(bundleRoomsTypes);
                theData.putBundle(bundleRoomsMonsters);
                theData.putBundle(bundleMap);

            }

            /**
             * This method is called to load game data from a DataFile.
             *
             * @param theData The DataFile object from which the game data will be loaded.
             */
            @Override
            public void onLoad(@NotNull final DataFile theData) {
                final Bundle bundlePlayer = theData.getBundle("player");
                final Bundle bundleInventory = theData.getBundle("inventory");
                final Bundle bundleRoomsBooleans = theData.getBundle("roomsBooleans");
                final Bundle bundleRoomsNumbers = theData.getBundle("roomsNumbers");
                final Bundle bundleRoomsTypes = theData.getBundle("roomsTypes");
                final Bundle bundleRoomsMonsters = theData.getBundle("roomsMonsters");
                final Bundle bundleMap = theData.getBundle("map");

                set("loaded", true);

                set("loadedPlayerX", bundlePlayer.get("playerX"));
                set("loadedPlayerY", bundlePlayer.get("playerY"));
                set("loadedCharacterName", bundlePlayer.get("characterName"));
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
                set("loadedRoomsMonsters", bundleRoomsMonsters.get("roomsMonsters"));
                set("loadedMap", bundleMap.get("revealedRooms"));
                loadHelper();
                getGameController().gotoPlay();
            }
        });
    }

    private void loadHelper() {
        myDungeon = new Dungeon(geto("loadedRoomsNumbers"),
                geto("loadedRoomsTypes"),
                geto("loadedRoomsBooleans"),
                geto("loadedRoomsMonsters"));
        myCharacterName = gets("loadedCharacterName");
        myPlayerName = gets("loadedPlayerName");
        set("spawnX", (double) getAppWidth() / 2 - 50);
        set("spawnY", (double) getAppHeight() / 2 - 50);
        set("pillars", geti("loadedPillarsCollected"));
        
        if (myDungeonFactory != null) {
            getGameWorld().removeEntityFactory(myDungeonFactory);
        }

        myDungeonFactory = new DungeonFactory(myDBData);
        getGameWorld().addEntityFactory(myDungeonFactory);
        set("dungeon", myDungeon);
        FXGL.setLevelFromMap(myDungeon.getEntranceMap());

        playerSetUp();
        set("playerX", geti("loadedPlayerX"));
        set("playerY", geti("loadedPlayerY"));
        setRoom(geti("playerX"), geti("playerY"));

        myDungeonMap = new MapSubScene(myDungeon);
        myDungeonMap.setRevealedRoom(geto("loadedMap"));


        getGameWorld().getSingleton(EntityType.PLAYER).
                getComponent(HealthDoubleComponent.class).setValue(getd("loadedPlayerHealth"));
        initPhysics();
        clearInventory();
        
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
    /**
     Initializes the game for the Dungeon Adventure.
     Sets the background color of the game scene to black.
     Creates a new dungeon, dungeon factory, and map subscene.
     Sets up the player and clears the inventory.
     */
    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.BLACK);
        try {
            myDungeon = new Dungeon(5, 5);
            //will change this when we can select class
            myDungeonFactory = new DungeonFactory(myDBData);
            getGameWorld().addEntityFactory(myDungeonFactory);
            set("dungeon", myDungeon);
            FXGL.setLevelFromMap(myDungeon.getEntranceMap());
            playerSetUp();
            clearInventory();
            myDungeonMap = new MapSubScene(myDungeon);
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    /**
     Clears the inventory of the player.
     Removes all items from the inventory.
     */
    private void clearInventory() {
        final Inventory inventory = PlayerComponent.getMyInventory();
        final Map inventoryMap = PlayerComponent.getMyInventory().getAllData();
        for (Object item : inventoryMap.keySet()) {
            inventory.remove(item);
        }
    }
    /**
     Sets up the player character.
     Retrieves the hero data from the database based on the character name.
     Sets the initial position of the player to the entrance coordinates of the dungeon.
     Creates a health component for the player based on the hit points from the hero data.
     Sets up the health bar view component for the player.
     Spawns the player entity and sets it as reusable.
     Registers listeners for the player's position changes.
     */
    private static void playerSetUp() {
        final Map<String, String> heroData = myDBData.get(myCharacterName);
        set("playerX", myDungeon.getEntranceX());
        set("playerY", myDungeon.getEntranceY());
        
        final HealthDoubleComponent hp = new HealthDoubleComponent(
                Double.parseDouble(heroData.get("hitPoints")));
        set("playerHP", hp);
        
        final var hpBar = new GenericBarViewComponent(0.0, -40.0, Color.RED,
                hp.valueProperty(), 100.0, 8.0);
        hpBar.getBar().setLabelVisible(true);
        hpBar.getBar().setLabelFill(Color.WHITE);
        hpBar.getBar().setLabelPosition(Position.TOP);
        set("playerHPView", hpBar);
        
        myPlayer = spawn("player");
        myPlayer.setReusable(true);
        set("player", myPlayer);
        getWorldProperties().addListener("playerX", (old, now) ->
                setRoom((int) now, geti("playerY")));
        getWorldProperties().addListener("playerY", (old, now) ->
                setRoom(geti("playerX"), (int) now));
    }

    /**
     Sets the current room based on the given coordinates.
     Removes the player entity from the world.
     Sets the current level based on the room's map.
     Marks the current room as visited.
     Spawns the entities in the current room.
     Spawns the player entity in the new room.
     @param theX The x-coordinate of the room.
     @param theY The y-coordinate of the room.
     */
    private static void setRoom(final int theX, final int theY) {
        myPlayer.removeFromWorld();
        FXGL.setLevelFromMap(myDungeon.get(theX, theY).getRoom());
        myDungeon.get(theX, theY).setVisited(true);
        spawnRoomEntities(myDungeon.get(theX, theY));
        myPlayer = spawn("player");
    }
    /**
     * Spawns entities in the given dungeon room based on its properties.
     *
     * @param theRoom The dungeon room to spawn entities in.
     */
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
            SpawnData monsterType = new SpawnData();
            monsterType.put("type", theRoom.getMonsterType());
            spawn("monster", monsterType);
        }
        if ("exit".equals(theRoom.getType())) {
            if (geti("pillars") == 4) {
                final var exit = getGameWorld().create("exit", new SpawnData());
                exit.getComponent(CollidableComponent.class).setValue(true);
                exit.getComponent(BoundingBoxComponent.class).addHitBox(
                        new HitBox(new Point2D(150.0, 119.0), BoundingShape.box(84.0, 61.0)));
                getGameWorld().addEntity(exit);
            } else {
                spawn("exit");
            }
        }
    }
    /**
     * Initializes the physics settings and collision handlers for the game.
     * This method is called during the initialization phase.
     * Overrides the method from the superclass.
     */
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
        getPhysicsWorld().addCollisionHandler(new PlayerPitHandler());
        getPhysicsWorld().addCollisionHandler(new PlayerPillarHandler(myDungeon));
        getPhysicsWorld().addCollisionHandler(new PlayerExitHandler());
        getPhysicsWorld().addCollisionHandler(new PlayerMonsterHandler());
        getPhysicsWorld().addCollisionHandler(new WeaponEnemyHandler());
    }
    /**
     * Initializes the input actions and event handlers for player movement and interactions.
     * Overrides the method from the superclass.
     */
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
        
        final var wrapper = new Object() {
            long myActionTime = 0L;
        };
        
        onBtnDown(MouseButton.PRIMARY, () -> {
            final long currentTime = System.currentTimeMillis();
            if (currentTime - wrapper.myActionTime
                    > myPlayer.getComponent(PlayerComponent.class).getAtkSpeed() * 1000) {
                if (myPlayer.getScaleX() < 0) {
                    myPlayer.getComponent(PlayerComponent.class).pausePlayer();
                    spawn("weapon", myPlayer.getPosition().add(-55, 15));
                } else {
                    myPlayer.getComponent(PlayerComponent.class).pausePlayer();
                    spawn("weapon", myPlayer.getPosition().add(40, 15));
                }
                wrapper.myActionTime = currentTime;
                runOnce(() -> {
                    myPlayer.getComponent(PlayerComponent.class).resumePlayer();
                    return null;
                }, Duration.seconds(myPlayer.getComponent(
                        PlayerComponent.class).getAtkSpeed()));
            }
            return null;
        });

        onKeyDown(KeyCode.O, () -> {
            final Queue<Point2D> dungeonQueue = new LinkedList<>();
            System.out.println(myDungeon.toString());
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
            final Queue devQueue = geto("devQueue");
            final Point2D point = (Point2D) devQueue.poll();
            set("spawnX", (double) 200);
            set("spawnY", (double) 200);
            setRoom((int) point.getX(), (int) point.getY());
            devQueue.add(point);
            return null;
        });

        onKeyDown(KeyCode.M, () -> {
            myDungeonMap.updateMap();
            FXGL.getSceneService().pushSubScene(myDungeonMap);
            return null;
        });
    }
    /**
     * Initializes the game variables with their initial values.
     * Overrides the method from the superclass.
     *
     * @param vars The map to store the game variables.
     */
    @Override
    protected void initGameVars(final Map<String, Object> vars) {
        vars.put("loaded", false);
        vars.put("pillars", 0);
        vars.put("spawnX", (double) getAppWidth() / 2 - 50);
        vars.put("spawnY", (double) getAppHeight() / 2 - 50);
    }
    /**
     * Handles the game update logic.
     * Overrides the method from the superclass.
     *
     * @param theTpf The time per frame.
     */
    @Override
    protected void onUpdate(final double theTpf) {
        if (myPlayer.getComponent(HealthDoubleComponent.class).isZero()) {
            getDialogService().showMessageBox("Game over man.", () -> {
                FXGL.getWorldProperties().clear();
                FXGL.getGameController().gotoMainMenu();
            });
        }
    }
    /**
     *
     Sets the character name.
     @param theCharacterName the name of the character
     */
    public static void setCharacterName(final String theCharacterName) {
        myCharacterName = theCharacterName;
    }
    /**
     *
     Retrieves the character name.
     @return the character name
     */
    public static String getCharacterName() {
        return myCharacterName;
    }
    /**
     *
     Sets the player name.
     @param thePlayerName the name of the player
     */
    public static void setPlayerName(final String thePlayerName) {
        myPlayerName = thePlayerName;
    }
    /**
     *
     Retrieves the player name.
     @return the player name
     */
    public static String getPlayerName() {
        return myPlayerName;
    }
    /**
     *
     Retrieves the database.
     @return the database containing game data
     */
    public static Map<String, Map<String, String>> getDatabase() {
        return myDBData;
    }
    /**
     *
     Retrieves the dungeon map.
     @return the dungeon map
     */
    public static MapSubScene getMyDungeonMap() {
        return myDungeonMap;
    }
    
    public static void main(final String[] theArgs) {
        launch(theArgs);
    }
}
