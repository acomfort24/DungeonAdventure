package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.dungeonmap.Dungeon;

public class GameMenu extends FXGLMenu {
    public GameMenu() {
        super(MenuType.GAME_MENU);
        final DungeonAdventureButton btnResume = new DungeonAdventureButton("Resume Game",
                "Resume Game", () -> fireResume());
        final DungeonAdventureButton btnInventory = new DungeonAdventureButton("Inventory",
                "Open Inventory", () -> {});
        final DungeonAdventureButton btnSaveGame = new DungeonAdventureButton("Save Game",
                "Save Game", () -> fireSave());
        final DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit to Menu",
                "Quit to Menu", () -> fireExitToMainMenu());


        final var box = new VBox(15,
                btnResume,
//                btnInventory,
                btnSaveGame,
                btnQuit);

        box.setAlignment(Pos.CENTER_LEFT);
        box.setTranslateX(100);
        box.setTranslateY(590);
        //getContentRoot().getChildren().addAll(FXGL.getUIFactoryService().newButton("hello"));
        getContentRoot().getChildren().add(box);
    }
}
