package model;

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

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;


public class DungeonFactory implements EntityFactory {

    @Spawns("wall")
    public Entity newDungeonWall(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.DUNGEON_WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("door")
    public Entity newDungeonDoor(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.DUNGEON_DOOR)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }
    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder()
                .type(EntityType.PLAYER)
                .viewWithBBox("player.png")
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("item")
    public Entity newItem(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
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
