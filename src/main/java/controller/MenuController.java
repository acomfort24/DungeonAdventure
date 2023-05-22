package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MenuController extends SceneSwapController {
    private void closeMenu(ActionEvent theEvent) throws IOException {
        switchToPreviousScene(theEvent);
    }
    private void openInventory(ActionEvent theEvent) throws IOException {
        switchToScene("../view/Inventory.fxml", theEvent);
    }
    private void saveGame(ActionEvent theEvent) {
        System.out.println("Game Saved!");
    }
    private void closeGame(ActionEvent theEvent) throws IOException {
        switchToScene("../view/MainMenu.fxml", theEvent);
    }
}
