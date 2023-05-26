package model;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

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
import model.components.PlayerComponent;
import model.components.PotionComponent;


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

    @Spawns("door")
    public Entity newDungeonDoor(final SpawnData theData) {
        return entityBuilder(theData)
                .type(EntityType.DOOR)
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
                .viewWithBBox("player.png")
                .at(theData.getX(), theData.getY())
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("item")
    public Entity newItem(final SpawnData theData) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        
        return entityBuilder()
                .type(EntityType.ITEM)
                .at(500, 500)
                .viewWithBBox("healthpotion.png")
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PotionComponent())
                .build();
    }
}
