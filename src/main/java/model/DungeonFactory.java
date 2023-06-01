package model;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.core.math.FXGLMath;
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
import javafx.geometry.Point2D;
import model.components.*;


public class DungeonFactory implements EntityFactory {

    public DungeonFactory() { }
    
    @Spawns("wall")
    public Entity newDungeonWall(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EntityType.DUNGEON_WALL)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("north door")
    public Entity newNorthDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EntityType.NORTH_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("south door")
    public Entity newSouthDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EntityType.SOUTH_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("west door")
    public Entity newWestDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EntityType.WEST_DOOR)
                .bbox(new HitBox(BoundingShape.box(theData.<Integer>get("width"),
                        theData.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("east door")
    public Entity newEastDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EntityType.EAST_DOOR)
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

        return entityBuilder()
                .type(EntityType.PLAYER)
                //.viewWithBBox("player.png")
                .bbox(new HitBox(BoundingShape.box(96, 96)))
                .at(theData.getX(), theData.getY())
                .with(physics)
                .with(new PlayerAnimationComponent())
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .build();
    }


    @Spawns("health potion")
    public Entity newHP(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(EntityType.HEALTH_POTION)
                .viewWithBBox("healthpotion.png")
                .with(physics)
                .at(FXGLMath.random(300, 800),FXGLMath.random(400, 600))
                .with(new CollidableComponent(true))
                .with(new PotionComponent())
                .build();
    }

    @Spawns("vision potion")
    public Entity newVP(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(EntityType.VISION_POTION)
                .viewWithBBox("visionpotion.png")
                .at(FXGLMath.random(400, 800),FXGLMath.random(500, 600))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PotionComponent())
                .build();
    }

    @Spawns("pit")
    public Entity newPit(final SpawnData theData) {
            return entityBuilder()
                    .type(EntityType.PIT)
                    .bbox(new HitBox(BoundingShape.box(960, 680)))
                    .with(new PitAnimationComponent())
                    .with(new CollidableComponent())
                    .at(new Point2D(96,144))
                    .build();
    }
    @Spawns("skeleton")
    public Entity newSkeleton(final SpawnData theData) {
        return entityBuilder()
                .type(EntityType.SKELETON)
                .bbox(new HitBox(BoundingShape.box(96, 96)))
                .with(new MonsterAnimationComponent("SkeletonIdleSheet.png"))
                .with(new CollidableComponent())
                .at(FXGLMath.random(400, 800),FXGLMath.random(500, 600))
                .build();
    }

    @Spawns("orc")
    public Entity newOrc(final SpawnData theData) {
        return entityBuilder()
                .type(EntityType.ORC)
                .bbox(new HitBox(BoundingShape.box(96, 96)))
                .with(new MonsterAnimationComponent("OrcIdleSheet.png"))
                .with(new CollidableComponent())
                .at(FXGLMath.random(400, 800),FXGLMath.random(500, 600))
                .build();
    }

}
