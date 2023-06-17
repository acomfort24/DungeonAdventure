package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import controller.DungeonApp;

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
    /**
     * Constructs a new GameMenu.
     */
    public GameMenu() {
        super(MenuType.GAME_MENU);
        final DungeonAdventureButton btnResume = new
                DungeonAdventureButton("Resume Game", this::fireResume);
        final DungeonAdventureButton btnSaveGame =
                new DungeonAdventureButton("Save Game", this::fireSave);
        final DungeonAdventureButton btnQuit =
                new DungeonAdventureButton("Quit to Menu", () -> {
                    fireExitToMainMenu();
                    FXGL.getWorldProperties().clear();
                });


        final VBox buttonBox = new VBox(15,
                btnResume,
                btnSaveGame,
                btnQuit);

        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setTranslateX(100);
        buttonBox.setTranslateY(590);
        getContentRoot().getChildren().add(buttonBox);
    }
}
