package controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.inventory.Inventory;
import java.util.HashMap;
import model.EntityType;
import model.components.PlayerComponent;

/**
 * The InventoryController class provides methods for manipulating the player's inventory.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class InventoryController {
    /**
     * Decreases the quantity of a specified item in the player's inventory and applies the item's effect.
     *
     * @param theItem the item to be used
     */
    public static void useItem(final String theItem) {
        final Inventory inventory = PlayerComponent.getMyInventory();
        if (inventory.getItemQuantity(theItem) > 1) {
            inventory.incrementQuantity(theItem, -1);
        } else {
            inventory.remove(theItem);
        }
        if ("HEALTH_POTION".equals(theItem)) {
            FXGL.getGameWorld().getSingleton(EntityType.PLAYER)
                    .getComponent(HealthDoubleComponent.class).restore(25);
        }
        if ("VISION_POTION".equals(theItem)) {
           DungeonApp.getMyDungeonMap().setRevealedRooms();
        }
    }
    /**
     * Adds a specified item to the player's inventory.
     *
     * @param theItem the item to be added
     */
    public static void addItem(final String theItem) {
        final Inventory inventory = PlayerComponent.getMyInventory();
        inventory.add(theItem);
    }

    /**
     * Retrieves the player's inventory as a map of item names to their quantities.
     *
     * @return a HashMap containing the items and their quantities
     */
    public static HashMap<String, Integer> getInventory() {
        final HashMap<String, Integer> returnedMap = new HashMap<>();
        final Inventory inventory = PlayerComponent.getMyInventory();
        if (!inventory.getAllData().isEmpty()) {
            inventory.getAllData().forEach((key, value) ->
                    returnedMap.put(key.toString(), inventory.getItemQuantity(key)));
        }
        return returnedMap;
    }
}
