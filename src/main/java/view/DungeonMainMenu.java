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

        final Node node = new ImageView("assets/textures/background/DungeonMainMenu.png");
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
        box.setTranslateY(660);
        
        //getContentRoot().getChildren().addAll(FXGL.getUIFactoryService().newButton("hello"));

        getContentRoot().getChildren().add(box);
    }
    
    private static class DungeonAdventureButton extends StackPane {
        /** */
        private static final Color SELECTED_COLOR = Color.WHITE;
        /** */
        private static final Color NOT_SELECTED_COLOR = Color.GRAY;
        /** */
        private final String myName;
        /** */
        private final Runnable myAction;
        /** */
        private final Text myText;
        
        /* default */
        DungeonAdventureButton(final String theName, final String theDescription,
                                      final Runnable theAction) {
            super();
            myName = theName;
            myAction = theAction;

            myText = FXGL.getUIFactoryService().newText(theName, Color.WHITE, 18.0);
            myText.fillProperty().bind(Bindings.when(focusedProperty())
                    .then(SELECTED_COLOR)
                    .otherwise(NOT_SELECTED_COLOR));
            myText.strokeProperty().bind(Bindings.when(focusedProperty())
                    .then(SELECTED_COLOR)
                    .otherwise(NOT_SELECTED_COLOR));

            myText.setStrokeWidth(.5);
            
            final Rectangle currentSelection = new Rectangle(5, 17, Color.WHITE);
            currentSelection.setTranslateX(-15);
            currentSelection.setTranslateY(-3);
            currentSelection.visibleProperty().bind(focusedProperty());

            setAlignment(Pos.CENTER_LEFT);
            setFocusTraversable(true);
            setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    theAction.run();
                }
            });

            getChildren().addAll(myText, currentSelection);
        }

    }
}
