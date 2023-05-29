package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

public class InventoryController extends SceneSwapController {
    public static void useItem(final String theItem) {
        System.out.println("You used a " + theItem);
        //decrement quantity of item in model
    }
    public static void addItem(final String theItem) {
        //increment quantity of item in model
    }
    public static HashMap<String, Integer> getInventory() {
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Health Potion", 4);
        inventory.put("Vision Potion", 1);
        return inventory;
    }
}
