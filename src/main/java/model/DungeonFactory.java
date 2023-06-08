package model;

import static com.almasb.fxgl.dsl.FXGL.*;
import static model.EntityType.*;

import com.almasb.fxgl.core.math.FXGLMath;
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
import controller.DungeonApp;
import javafx.geometry.Point2D;
import model.components.*;
import model.components.PillarComponent;
import model.components.PlayerComponent;
import model.components.PotionComponent;

import java.util.Map;


public class DungeonFactory implements EntityFactory {
    Map<String, Map<String, String>> myDBData;

    public DungeonFactory(Map<String, Map<String, String>> theDBData) {
        myDBData = theDBData;
    }
    
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
    @Spawns("player")
    public Entity newPlayer(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0));
        Map<String, String> heroData = myDBData.get(DungeonApp.myCharacterName);

        return entityBuilder()
                .type(PLAYER)
                //.viewWithBBox("player.png")
                .bbox(new HitBox(BoundingShape.box(96, 96)))
                .at(getd("spawnX"), getd("spawnY"))
                .with(physics)
                .with(new PlayerAnimationComponent(DungeonApp.myCharacterName))
                .with(new CollidableComponent(true))
                .with(new PlayerComponent(
                        Integer.parseInt(heroData.get("minDmg")),
                        Integer.parseInt(heroData.get("maxDmg")),
                        Integer.parseInt(heroData.get("atkSpd")),
                        Double.parseDouble(heroData.get("chncHit")),
                        Integer.parseInt(heroData.get("hitPoints")),
                        heroData.get("name")))
                .with((HealthDoubleComponent) geto("playerHP"))
                .with((GenericBarViewComponent) geto("playerHPView"))
                .build();
    }
    
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
    
    @Spawns("pillar")
    public Entity newPillar(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        
        return entityBuilder()
                .type(PILLAR)
                .viewWithBBox("pillar.png")
                .at(getAppWidth() / 2 - 35, getAppHeight() / 2 - 43)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PillarComponent())
                .build();
    }
    
    @Spawns("exit")
    public Entity newExit(final SpawnData theData) {
        
        return entityBuilder()
                .type(EXIT)
                .view("exit" + geti("pillars") + ".png")
                .at(getAppWidth() / 2 - 190, getAppHeight() / 2 - 130)
                .with(new CollidableComponent(false))
                .build();
    }

    @Spawns("pit")
    public Entity newPit(final SpawnData theData) {
            return entityBuilder()
                    .type(EntityType.PIT)
                    .bbox(new HitBox(BoundingShape.box(1052, 693)))
                    .with(new PitComponent())
                    .with(new CollidableComponent())
                    .at(new Point2D(48,123))
                    .build();
    }
    
    @Spawns("skeleton")
    public Entity newSkeleton(final SpawnData theData) {
        Map<String, String> monsterData = myDBData.get("Skeleton");
        return entityBuilder()
                .type(EntityType.SKELETON)
                .bbox(new HitBox(BoundingShape.box(96, 96)))
                .with(new MonsterAnimationComponent("SkeletonIdleSheet.png"))
                .with(new CollidableComponent())
                .at(FXGLMath.random(400, 800),FXGLMath.random(500, 600))
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

    @Spawns("orc")
    public Entity newOrc(final SpawnData theData) {
        Map<String, String> monsterData = myDBData.get("Orc");
        return entityBuilder()
                .type(EntityType.ORC)
                .bbox(new HitBox(BoundingShape.box(96, 96)))
                .with(new MonsterAnimationComponent("OrcIdleSheet.png"))
                .with(new CollidableComponent())
                .with(new MonsterComponent(
                        Integer.parseInt(monsterData.get("minHeal")),
                        Integer.parseInt(monsterData.get("maxHeal")),
                        Integer.parseInt(monsterData.get("minDmg")),
                        Integer.parseInt(monsterData.get("maxDmg")),
                        Integer.parseInt(monsterData.get("atkSpd")),
                        Double.parseDouble(monsterData.get("chncHit")),
                        Integer.parseInt(monsterData.get("hitPoints")),
                        monsterData.get("name")))
                .at(FXGLMath.random(400, 800),FXGLMath.random(500, 600))
                .build();
    }

}
