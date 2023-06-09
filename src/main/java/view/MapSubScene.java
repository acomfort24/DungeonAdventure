package view;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.dungeonmap.Dungeon;
import model.dungeonmap.DungeonRoom;

public class MapSubScene extends SubScene {
    private final int myNumRows;
    private final int myNumColumns;
    private final Dungeon myDungeon;
    
    public MapSubScene(final Dungeon theDungeon) {

        this.myDungeon = theDungeon;
        this.myNumRows = theDungeon.getHeight();
        this.myNumColumns = theDungeon.getWidth();

        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Populate the GridPane with rectangles representing rooms
        for (int row = 0; row < myNumRows; row++) {
            for (int col = 0; col < myNumColumns; col++) {
                final Rectangle roomRect = new Rectangle(50, 50, Color.WHITE);
                gridPane.add(roomRect, row, col);
            }
        }

        updateRoomAppearance(gridPane);

        this.getInput().addAction(new UserAction("Close") {
            @Override
            protected void onActionBegin() {
                FXGL.getSceneService().popSubScene();
            }
        }, KeyCode.M);

        getContentRoot().getChildren().addAll(gridPane);
        getContentRoot().setTranslateX(100);
        getContentRoot().setTranslateY(50);

    }
    private void updateRoomAppearance(final GridPane theGridPane) {
        // Update the appearance of each room based on its visited status
        for (int row = 0; row < myNumRows; row++) {
            for (int col = 0; col < myNumColumns; col++) {
                final DungeonRoom room = myDungeon.get(col, row);
                final Rectangle roomRect = (Rectangle) theGridPane.getChildren().get(col * myNumRows + row);

                if(FXGL.getWorldProperties().getValue("playerY").equals(row) &&
                    FXGL.getWorldProperties().getValue("playerX").equals(col)) {
                    roomRect.setFill(Color.BLUE);
                } else if (room.hasBeenVisited()) {
                    roomRect.setFill(Color.GREEN);
                } else {
                    roomRect.setFill(Color.WHITE);
                }
            }
        }
    }
    private void addLetterToRectangle(final Rectangle theRectangle, final String theLetter) {
        // Create a text node with the letter and add it to the rectangle
        final Text text = new Text(theLetter);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        final StackPane stackPane = new StackPane(theRectangle, text);
        stackPane.setAlignment(Pos.CENTER);

        // Replace the rectangle with the stack pane in the grid pane
        GridPane.setHgrow(stackPane, Priority.ALWAYS);
        GridPane.setVgrow(stackPane, Priority.ALWAYS);
        GridPane.setMargin(stackPane, new Insets(5));

        final GridPane gridPane = (GridPane) theRectangle.getParent();
        final int rowIndex = GridPane.getRowIndex(theRectangle);
        final int columnIndex = GridPane.getColumnIndex(theRectangle);
        gridPane.add(stackPane, columnIndex, rowIndex);
    }


}
