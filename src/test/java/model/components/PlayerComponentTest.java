package model.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.inventory.Inventory;
import model.EntityType;
import org.junit.jupiter.api.Test;

import static model.EntityType.HEALTH_POTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerComponentTest {
    public playerComponentTest() {

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
//        final PlayerComponent player = new PlayerComponent();
//    }
    @Test
    public void testHealthPotionPickup() {
      Entity player = new Entity();
      player.setType(EntityType.PLAYER);
//      player.addComponent(new PlayerComponent());

      Entity item = new Entity();
      item.setType(HEALTH_POTION);


//      assertEquals(, 1);
    }
}
