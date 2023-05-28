package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DungeonMainMenu extends FXGLMenu {

    public DungeonMainMenu() {
        super(MenuType.MAIN_MENU);

        final Node node = new ImageView("assets/textures/background/MainMenu.png");
        node.resize(FXGL.getAppWidth(), FXGL.getAppHeight());
        getContentRoot().getChildren().add(node);

        final DungeonAdventureButton btnPlayGame = new DungeonAdventureButton("Play Game",
                "Start a new game", () -> fireNewGame());
        final DungeonAdventureButton btnOptions = new DungeonAdventureButton("Options",
                "Change in-game settings", () -> { });
        final DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit Game",
                "Quit to desktop", () -> fireExit());
        
        final var box = new VBox(15,
                btnPlayGame,
                btnOptions,
                btnQuit,
                new Separator(Orientation.HORIZONTAL),
                FXGL.getUIFactoryService().newText("Welcome to Dungeon Adventure"));

        box.setAlignment(Pos.CENTER_LEFT);
        box.setTranslateX(100);
        box.setTranslateY(590);
        //getContentRoot().getChildren().addAll(FXGL.getUIFactoryService().newButton("hello"));
        getContentRoot().getChildren().add(box);
    }
}
