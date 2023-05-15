package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
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
import org.jetbrains.annotations.NotNull;

public class DungeonMainMenu extends FXGLMenu {

    private ObjectProperty<DungeonAdventureButton> selectedButton;

    public DungeonMainMenu() {
        super(MenuType.MAIN_MENU);

        Node node = new ImageView("assets/textures/background/Dungeon2.png");
        node.resize(FXGL.getAppWidth(), FXGL.getAppHeight());
        getContentRoot().getChildren().add(node);

        DungeonAdventureButton btnPlayGame = new DungeonAdventureButton("Play Game","Start a new game", () -> fireNewGame());
        DungeonAdventureButton btnOptions = new DungeonAdventureButton("Options","Change in-game settings", () -> {});
        DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit Game", "Quit to desktop",() -> fireExit());





        var box = new VBox(15,
                btnPlayGame,
                btnOptions,
                btnQuit,
                new Separator(Orientation.HORIZONTAL),
                FXGL.getUIFactoryService().newText("Welcome to Dungeon Adventure"));

        box.setAlignment(Pos.CENTER_LEFT);
        box.setTranslateX(100);
        box.setTranslateY(590);


        // getContentRoot().getChildren().addAll(FXGL.getUIFactoryService().newButton("hello"));

        getContentRoot().getChildren().add(box);
    }

    private static final Color SELECTED_COLOR = Color.WHITE;
    private static final Color NOT_SELECTED_COLOR = Color.GRAY;
    private static class DungeonAdventureButton extends StackPane {
        private String name;
        private Runnable action;
        private Text text;
        private Rectangle currentSelection;

        public DungeonAdventureButton(String name, String description, Runnable action) {
            this.name = name;
            this.action = action;

            text = FXGL.getUIFactoryService().newText(name, Color.WHITE, 18.0);
            text.fillProperty().bind(Bindings.when(focusedProperty()).then(SELECTED_COLOR).otherwise(NOT_SELECTED_COLOR)
            );
            text.strokeProperty().bind(Bindings.when(focusedProperty()).then(SELECTED_COLOR).otherwise(NOT_SELECTED_COLOR)
            );

            text.setStrokeWidth(.5);

            currentSelection = new Rectangle(5,17,Color.WHITE);
            currentSelection.setTranslateX(-15);
            currentSelection.setTranslateY(-3);
            currentSelection.visibleProperty().bind(focusedProperty());

            setAlignment(Pos.CENTER_LEFT);
            setFocusTraversable(true);
            setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER){
                    action.run();
                }
            });

            getChildren().addAll(text,currentSelection);
        }

    }
}
