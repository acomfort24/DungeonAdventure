package model;

import com.almasb.fxgl.entity.Entity;
import controller.InventoryController;
import model.components.PlayerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests adding/removing/getting Inventory items
 */
public class InventoryModelTest {
    private final Entity player = new Entity();

    private InventoryModelTest() {
        player.addComponent(new PlayerComponent(2,
                4, 2, .4, 50, "warrior", .1));
    }

    /**
     * Clears the inventory before every test because InventoryController is static
     */
    @BeforeEach
    public void clearInventory() {
        player.getComponent(PlayerComponent.class).getMyInventory().remove("HEALTH_POTION");
        player.getComponent(PlayerComponent.class).getMyInventory().remove("VISION_POTION");
    }
    @Test
    public void addItemEmptyTest() {
        InventoryController.addItem("HEALTH_POTION");
        InventoryController.addItem("HEALTH_POTION");
        assertEquals(2, player.getComponent(PlayerComponent.class).getMyInventory().getItemQuantity("HEALTH_POTION"));
    }
    @Test
    public void addItemExistsTest() {
        InventoryController.addItem("HEALTH_POTION");
        InventoryController.addItem("HEALTH_POTION");
        assertEquals(2, player.getComponent(PlayerComponent.class).getMyInventory().getItemQuantity("HEALTH_POTION"));
    }
    @Test
    public void useItemTest() {
        InventoryController.addItem("HEALTH_POTION");
        InventoryController.addItem("HEALTH_POTION");
        player.getComponent(PlayerComponent.class).getMyInventory().incrementQuantity("HEALTH_POTION", -1);
        assertEquals(1, player.getComponent(PlayerComponent.class).getMyInventory().getItemQuantity("HEALTH_POTION"));
    }
    @Test
    public void useAllItemTest() {
        InventoryController.addItem("HEALTH_POTION");
        player.getComponent(PlayerComponent.class).getMyInventory().incrementQuantity("HEALTH_POTION", -1);
        assertEquals(0, player.getComponent(PlayerComponent.class).getMyInventory().getItemQuantity("HEALTH_POTION"));
    }
    @Test
    public void addVisionPotionTest() {
        InventoryController.addItem("VISION_POTION");
        assertEquals(1, player.getComponent(PlayerComponent.class).getMyInventory().getItemQuantity("VISION_POTION"));
    }
    @Test
    public void add100VisionPotionTest() {
        for (int i = 0; i < 100; i++) {
            InventoryController.addItem("VISION_POTION");
        }

        assertEquals(100, player.getComponent(PlayerComponent.class).getMyInventory().getItemQuantity("VISION_POTION"));
    }
    @Test
    public void getInventoryTest() {
        for (int i = 0; i < 100; i++) {
            InventoryController.addItem("VISION_POTION");
        }
        InventoryController.addItem("HEALTH_POTION");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("HEALTH_POTION", 1);
        map.put("VISION_POTION", 100);
        assertEquals(map, InventoryController.getInventory());

    }
}
