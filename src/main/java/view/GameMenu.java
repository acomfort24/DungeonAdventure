package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import controller.DungeonApp;
import controller.InventoryController;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * The GameMenu class represents the in-game menu screen of a dungeon adventure game.
 * It extends the FXGLMenu class,
 * which provides a base implementation for creating game menus in FXGL.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class GameMenu extends FXGLMenu {
    /** The scroll pane for displaying flexible content. */
    private final ScrollPane myFlexBox = new ScrollPane();
    /** Determines whether the inventory is currently shown or not. */
    private Boolean myShowInventory = false;
    /**
     * Constructs a new GameMenu.
     */
    public GameMenu() {
        super(MenuType.GAME_MENU);
        final DungeonAdventureButton btnResume = new
                DungeonAdventureButton("Resume Game", this::fireResume);
        final DungeonAdventureButton btnInventory =
                new DungeonAdventureButton("Toggle Inventory", this::toggleInventory);
        final DungeonAdventureButton btnSaveGame =
                new DungeonAdventureButton("Save Game", this::fireSave);
        final DungeonAdventureButton btnQuit =
                new DungeonAdventureButton("Quit to Menu", () -> {
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
    /**
     * Called when the menu is created.
     * Sets the content of the flex box to the progress box.
     */
    @Override
    public void onCreate() {
        myFlexBox.setContent(createProgressBox());
    }
    /**
     * Called when the menu is destroyed.
     * Resets the inventory visibility flag.
     */
    @Override
    public void onDestroy() {
        myShowInventory = false;
    }
    /**
     * Toggles between showing the inventory and progress box in the flexible box.
     */
    private void toggleInventory() {
        myShowInventory = !myShowInventory;
        if (myShowInventory) {
            myFlexBox.setContent(createInventoryBox());
        } else {
            myFlexBox.setContent(createProgressBox());
        }
    }
    /**
     * Creates a vertical box that represents the inventory.
     *
     * @return The created inventory VBox.
     */
    private VBox createInventoryBox() {
        final VBox box = new VBox();
        final Map<String, Integer> inventory = InventoryController.getInventory();
        for (final String item : inventory.keySet()) {
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
    /**
     * Creates a vertical box that represents the progress.
     *
     * @return The created progress VBox.
     */
    private VBox createProgressBox() {
        final ImageView heroImage =
                new ImageView(FXGL.image(DungeonApp.getCharacterType() + ".png"));
        final VBox box = new VBox();
        final Text pillarText =
                new Text("Pillars Collected: " + FXGL.getWorldProperties().getInt("pillars"));
        box.getChildren().add(heroImage);
        box.getChildren().add(pillarText);
        return box;
    }
    /**
     * Uses the specified item from the inventory.
     *
     * @param theItem The item to use.
     */
    private void useItem(final String theItem) {
        InventoryController.useItem(theItem);
    }
}
