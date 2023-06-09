package model.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import model.EntityType;
import org.junit.jupiter.api.Test;

import static model.EntityType.HEALTH_POTION;
import static model.EntityType.VISION_POTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerComponentTest {
    Entity myPlayer = new Entity();
    public PlayerComponentTest() {
        myPlayer.setType(EntityType.PLAYER);
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0));
        myPlayer.addComponent(physics);
        myPlayer.addComponent(new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1));


    }
    @Test
    public void testConstructor() {
        final PlayerComponent player = new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1);
        assertEquals(player.getMyCharacterComponent().getMyName(), "warrior");
        assertEquals(.1, player.getMyChncBlock());
    }
//    @Test
//    public void testDown() {
//        myPlayer.setX(0);
//        myPlayer.setY(0);
//        myPlayer.getComponent(PlayerComponent.class).down();
//        assertEquals(0, myPlayer.getX());
//        assertEquals(1, myPlayer.getY());
//    }
    @Test
    public void testHealthPotionPickup() {
      Entity item = new Entity();
      item.setType(HEALTH_POTION);


//      assertEquals(, 1);
    }
    @Test
    public void testVisionPotionPickup() {
        Entity item = new Entity();
        item.setType(VISION_POTION);


//      assertEquals(, 1);
    }
    @Test
    public void testPillarPickup() {

    }
    @Test
    public void testExit0Collected() {

    }
    @Test
    public void testExit1Collected() {

    }
    @Test
    public void testExit4Collected() {

    }
    @Test
    public void testEnterPitRoom() {

    }
    @Test
    public void testEnterNorthDoor() {

    }
    @Test
    public void testEnterEastDoor() {

    }
    @Test
    public void testEnterSouthDoor() {

    }
    @Test
    public void testEnterWestDoor() {

    }
}
