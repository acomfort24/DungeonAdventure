package controller;

import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.inventory.view.InventoryListView;
import model.components.PlayerComponent;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.*;

public class InventoryController<I,D> extends SceneSwapController {
    public static void useItem(final String theItem) {
        System.out.println("You used a " + theItem);
        Inventory inventory = PlayerComponent.getMyInventory();
        inventory.remove(theItem);
    }
    public static void addItem(final String theItem) {
        Inventory inventory = PlayerComponent.getMyInventory();
        inventory.add(theItem);
    }
    public static HashMap<String, Integer> getInventory() {
        HashMap<String, Integer> returnedMap = new HashMap<String, Integer>();
        Inventory inventory = PlayerComponent.getMyInventory();
        if (!inventory.getAllData().isEmpty()) {
            System.out.println(inventory);
//            for (I item: inventory.getAllData()) {
//                returnedMap.put(I.getName(), inventory.get(I));
//            }
        }
        return returnedMap;
    }
}
