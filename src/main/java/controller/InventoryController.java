package controller;

import com.almasb.fxgl.inventory.Inventory;
import java.util.HashMap;
import model.components.PlayerComponent;




public class InventoryController extends SceneSwapController {
    public static void useItem(final String theItem) {
        final Inventory inventory = PlayerComponent.getMyInventory();
        if (inventory.getItemQuantity(theItem) > 1) {
            inventory.incrementQuantity(theItem, -1);
        } else {
            inventory.remove(theItem);
        }
    }
    public static void addItem(final String theItem) {
        final Inventory inventory = PlayerComponent.getMyInventory();
        inventory.add(theItem);
    }
    public static HashMap<String, Integer> getInventory() {
        final HashMap<String, Integer> returnedMap = new HashMap<>();
        final Inventory inventory = PlayerComponent.getMyInventory();
        if (!inventory.getAllData().isEmpty()) {
            inventory.getAllData().forEach((key, value) -> returnedMap.put(key.toString(), inventory.getItemQuantity(key)));
        }
        return returnedMap;
    }
}
