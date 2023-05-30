package view;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DungeonAdventureButton extends StackPane {
    /** */
    private static final Color SELECTED_COLOR = Color.WHITE;
    /** */
    private static final Color NOT_SELECTED_COLOR = Color.GRAY;
    /** */
    private final Runnable myAction;
    /** */
    private final Text myText;

    /* default */
    DungeonAdventureButton(final String theName, final String theDescription,
                           final Runnable theAction) {
        super();
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
        setOnMouseClicked(e -> {
            theAction.run();
        });

        getChildren().addAll(myText, currentSelection);
    }

}