package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import controller.DungeonApp;
import controller.InventoryController;
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.EntityType;
import model.components.PlayerComponent;
import model.dungeonmap.Dungeon;

import static com.almasb.fxgl.app.scene.FXGLMenu.*;

public class GameMenu extends FXGLMenu {
    private final ScrollPane myFlexBox = new ScrollPane();
    private Boolean myShowInventory = false;
    public GameMenu() {
        super(MenuType.GAME_MENU);
        final DungeonAdventureButton btnResume = new DungeonAdventureButton("Resume Game",
                "Resume Your Game", () -> {
            fireResume();
        });
        final DungeonAdventureButton btnInventory = new DungeonAdventureButton("Toggle Inventory",
                "Open/Close Inventory", () -> toggleInventory());
        final DungeonAdventureButton btnSaveGame = new DungeonAdventureButton("Save Game",
                "Save Game", () -> {
            fireSave();
        });
        final DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit to Menu",
                "Quit to Menu", () -> {
            fireExitToMainMenu();
            FXGL.getWorldProperties().clear();
        });


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
    }
    @Override
    public void onCreate() {
        myFlexBox.setContent(createProgressBox());
    }
    @Override
    public void onDestroy() {
        myShowInventory = false;
    }
    private void toggleInventory() {
        myShowInventory = !myShowInventory;
        if (myShowInventory) {
            myFlexBox.setContent(createInventoryBox());
        } else {
            myFlexBox.setContent(createProgressBox());
        }
    }
    private VBox createInventoryBox() {
        final VBox box = new VBox();
        final HashMap<String, Integer> inventory = InventoryController.getInventory();
        for (String item : inventory.keySet()) {
            final FlowPane buttonPane = new FlowPane();
            final Button itemButton = new Button(item);
            itemButton.setOnAction(actionEvent -> {
                useItem(item);
                myFlexBox.setContent(createInventoryBox());
            });
            buttonPane.getChildren().add(itemButton);
            final Text itemAmount = new Text(String.valueOf(inventory.get(item)));
            buttonPane.getChildren().add(itemAmount);
            box.getChildren().add(buttonPane);
        }
        return box;
    }
    private VBox createProgressBox() {
        ImageView heroImage = new ImageView(FXGL.image(DungeonApp.myCharacterName + ".png"));
        final VBox box = new VBox();
        final Text pillarText = new Text("Pillars Collected: " + FXGL.getWorldProperties().getInt("pillars"));
        box.getChildren().add(heroImage);
        box.getChildren().add(pillarText);
        return box;
    }
    private void useItem(final String theItem) {
        InventoryController.useItem(theItem);
    }
}
