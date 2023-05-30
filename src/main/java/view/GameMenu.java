package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import controller.InventoryController;
import javafx.scene.text.Text;

public class GameMenu extends FXGLMenu {
    private ScrollPane myFlexBox = new ScrollPane();
    private Boolean showInventory = false;
    public GameMenu() {
        super(MenuType.GAME_MENU);
        final DungeonAdventureButton btnResume = new DungeonAdventureButton("Resume Game",
                "Resume Game", () -> fireResume());
        final DungeonAdventureButton btnInventory = new DungeonAdventureButton("Inventory",
                "Toggle Inventory", () -> toggleInventory());
        final DungeonAdventureButton btnSaveGame = new DungeonAdventureButton("Save Game",
                "Save Game", () -> fireSave());
        final DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit to Menu",
                "Quit to Menu", () -> fireExitToMainMenu());


        final VBox buttonBox = new VBox(15,
                btnResume,
                btnInventory,
                btnSaveGame,
                btnQuit);

        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setTranslateX(100);
        buttonBox.setTranslateY(590);
        getContentRoot().getChildren().add(buttonBox);
        getContentRoot().getChildren().add(myFlexBox);
        myFlexBox.setContent(createProgressBox());
    }
    private void toggleInventory() {
        showInventory = !showInventory;
        if (showInventory) {
            myFlexBox.setContent(createInventoryBox());
        } else {
            myFlexBox.setContent(createProgressBox());
        }
    }
    private VBox createInventoryBox() {
        VBox box = new VBox();
        HashMap<String, Integer> inventory = InventoryController.getInventory();
        for(String item : inventory.keySet()) {
            FlowPane buttonPane = new FlowPane();
            Button itemButton = new Button(item);
            itemButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent actionEvent) {
                    useItem(item);
                }
            });
            buttonPane.getChildren().add(itemButton);
            Text itemAmount = new Text("" + inventory.get(item));
            buttonPane.getChildren().add(itemAmount);
            box.getChildren().add(buttonPane);
        }
        return box;
    }
    private VBox createProgressBox() {
//        Image heroImage = new Image(new FileInputStream(""));
        Text testText = new Text("Progress!");
        VBox box = new VBox(testText);
        return box;
    }
    private void useItem(final String theItem) {
        InventoryController.useItem(theItem);
    }
}
