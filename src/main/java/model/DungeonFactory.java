package model;

import static com.almasb.fxgl.dsl.FXGL.*;
import static model.EntityType.*;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.dsl.components.view.GenericBarViewComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.ui.Position;
import controller.DungeonApp;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.components.*;
import model.components.PillarComponent;
import model.components.PlayerComponent;
import model.components.PotionComponent;

/**
 * The DungeonFactory class is responsible for creating entities in the dungeon game.
 * Each entity is created with specific properties based on the provided SpawnData.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class DungeonFactory implements EntityFactory {
    /** */
    final Map<String, Map<String, String>> myDBData;

    /**
     * Constructs a new DungeonFactory with the specified database data.
     *
     * @param theDBData the database data used for entity creation
     */
    public DungeonFactory(Map<String, Map<String, String>> theDBData) {
        myDBData = theDBData;
    }

    /**
     * Creates a new dungeon wall entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created dungeon wall entity
     */
    @Spawns("wall")
    public Entity newDungeonWall(final SpawnData theData) {
        return entityBuilder(theData)
                .type(DUNGEON_WALL)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * Creates a new north door entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created north door entity
     */
    @Spawns("north door")
    public Entity newNorthDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(NORTH_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    /**
     * Creates a new south door entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created south door entity
     */
    @Spawns("south door")
    public Entity newSouthDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(SOUTH_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    /**
     * Creates a new west door entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created west door entity
     */
    @Spawns("west door")
    public Entity newWestDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(WEST_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    /**
     * Creates a new east door entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created east door entity
     */
    @Spawns("east door")
    public Entity newEastDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EAST_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    /**
     * Creates a new player entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created player entity
     */
    @Spawns("player")
    public Entity newPlayer(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0));
        final Map<String, String> heroData = myDBData.get(DungeonApp.getCharacterName());

        return entityBuilder()
                .type(PLAYER)
                .bbox(new HitBox(BoundingShape.box(80, 96)))
                .at(getd("spawnX"), getd("spawnY"))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerAnimationComponent(DungeonApp.getCharacterName()))
                .with(new PlayerComponent(
                        Integer.parseInt(heroData.get("minDmg")),
                        Integer.parseInt(heroData.get("maxDmg")),
                        Integer.parseInt(heroData.get("atkSpd")),
                        Double.parseDouble(heroData.get("chncHit")),
                        Integer.parseInt(heroData.get("hitPoints")),
                        heroData.get("name"),
                        Double.parseDouble(heroData.get("chncBlock"))))
                .with((HealthDoubleComponent) geto("playerHP"))
                .with((GenericBarViewComponent) geto("playerHPView"))
                .build();
    }
    /**
     * Creates a new health potion entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created health potion entity
     */
    @Spawns("health potion")
    public Entity newHP(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(HEALTH_POTION)
                .viewWithBBox("healthpotion.png")
                .with(physics)
                .at(430, 400)
                .with(new CollidableComponent(true))
                .with(new PotionComponent("healthpotion.png"))
                .build();
    }
    /**
     * Creates a new vision potion entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created vision potion entity
     */
    @Spawns("vision potion")
    public Entity newVP(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(VISION_POTION)
                .viewWithBBox("visionpotion.png")
                .at(670, 400)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PotionComponent("visionpotion.png"))
                .build();
    }
    /**
     * Creates a new pillar entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created pillar entity
     */
    @Spawns("pillar")
    public Entity newPillar(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        
        return entityBuilder()
                .type(PILLAR)
                .viewWithBBox("pillar.png")
                .at((double) getAppWidth() / 2 - 35, (double) getAppHeight() / 2 - 43)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PillarComponent())
                .build();
    }
    /**
     * Creates a new exit entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created exit entity
     */
    @Spawns("exit")
    public Entity newExit(final SpawnData theData) {
        
        return entityBuilder()
                .type(EXIT)
                .view("exit" + geti("pillars") + ".png")
                .at((double) getAppWidth() / 2 - 190, (double) getAppHeight() / 2 - 130)
                .with(new CollidableComponent(false))
                .build();
    }
    /**
     * Creates a new pit entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created pit entity
     */
    @Spawns("pit")
    public Entity newPit(final SpawnData theData) {
        
            return entityBuilder()
                    .type(PIT)
                    .bbox(new HitBox(BoundingShape.box(1152, 864)))
                    .with(new PitAnimationComponent())
                    .with(new CollidableComponent(true))
                    .at(new Point2D(48,123))
                    .build();
    }
    /**
     * Creates a new monster entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created monster entity
     */
    @Spawns("monster")
    public Entity newMonster(final SpawnData theData) {
        final Map<String, String> monsterData = myDBData.get(theData.get("type"));

        final HealthDoubleComponent hp = new HealthDoubleComponent(
                Double.parseDouble(monsterData.get("hitPoints")));
        final var hpBar = new GenericBarViewComponent(0.0, -40.0, Color.RED,
                hp.valueProperty(), 100.0, 8.0);
        hpBar.getBar().setLabelVisible(true);
        hpBar.getBar().setLabelFill(Color.WHITE);
        hpBar.getBar().setLabelPosition(Position.TOP);

        return entityBuilder()
                .type(MONSTER)
                .bbox(new HitBox(BoundingShape.box(76, 96)))
                .with(new MonsterAnimationComponent(theData.get("type")))
                .with(new CollidableComponent(true))
                .with(hp)
                .with(hpBar)
                .at(FXGLMath.random(350, 800), FXGLMath.random(350, 550))
                .with(new MonsterComponent(
                        Integer.parseInt(monsterData.get("minHeal")),
                        Integer.parseInt(monsterData.get("maxHeal")),
                        Integer.parseInt(monsterData.get("minDmg")),
                        Integer.parseInt(monsterData.get("maxDmg")),
                        Integer.parseInt(monsterData.get("atkSpd")),
                        Double.parseDouble(monsterData.get("chncHit")),
                        Integer.parseInt(monsterData.get("hitPoints")),
                        monsterData.get("name")))
                .build();
    }
    /**
     * Creates a new weapon entity.
     *
     * @param theData the SpawnData containing the entity's properties
     * @return the created weapon entity
     */
    @Spawns("weapon")
    public Entity newWeapon(SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        
        return entityBuilder(theData)
                .type(WEAPON)
                .bbox(new HitBox(BoundingShape.box(100, 70)))
                .with(new CollidableComponent(true))
                .with(new AttackComponent())
                .with(new ExpireCleanComponent(Duration.seconds(0.5)))
                .build();
    }
}
