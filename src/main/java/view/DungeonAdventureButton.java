package view;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The DungeonAdventureButton class represents a custom button used in a dungeon adventure game.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class DungeonAdventureButton extends StackPane {
    /** The color of the button when it is selected. */
    private static final Color SELECTED_COLOR = Color.WHITE;
    /** The color of the button when it is not selected. */
    private static final Color NOT_SELECTED_COLOR = Color.GRAY;
    /**
     * Constructs a new DungeonAdventureButton with the specified name and action.
     *
     * @param theName   the name of the button
     * @param theAction the action to be executed when the button is clicked or the Enter key is pressed
     */
    DungeonAdventureButton(final String theName,
                           final Runnable theAction) {
        super();
        final Text text = FXGL.getUIFactoryService().newText(theName, Color.WHITE, 18.0);
        text.fillProperty().bind(Bindings.when(focusedProperty())
                .then(SELECTED_COLOR)
                .otherwise(NOT_SELECTED_COLOR));
        text.strokeProperty().bind(Bindings.when(focusedProperty())
                .then(SELECTED_COLOR)
                .otherwise(NOT_SELECTED_COLOR));

        text.setStrokeWidth(.5);

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
        setOnMouseClicked(e -> theAction.run());

        getChildren().addAll(text, currentSelection);
    }

}