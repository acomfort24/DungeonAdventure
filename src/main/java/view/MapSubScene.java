package view;

import java.awt.*;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.dungeonmap.Dungeon;

import java.io.Serializable;
import java.util.ArrayList;

public class MapSubScene extends SubScene implements Serializable {
    private final int myNumRows;
    private final int myNumColumns;
    private final Dungeon myDungeon;
    private final ArrayList<Point> myRevealedRooms;

    public MapSubScene(final Dungeon theDungeon) {

        this.myDungeon = theDungeon;
        this.myNumRows = theDungeon.getHeight();
        this.myNumColumns = theDungeon.getWidth();
        this.myRevealedRooms = new ArrayList<>();

        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Populate the GridPane with rectangles representing rooms
        for (int row = 0; row < myNumRows; row++) {
            for (int col = 0; col < myNumColumns; col++) {
                final Rectangle roomRect = new Rectangle(50, 50, Color.WHITE);

                // Check if the current room has been visited
                if (myDungeon.get(col, row).hasBeenVisited()) {
                    roomRect.setFill(Color.GREEN);
                }

                // Check if the current room is the player's current position
                if (row == FXGL.geti("playerY") && col == FXGL.geti("playerX")) {
                    roomRect.setFill(Color.BLUE);
                }

                gridPane.add(roomRect, col, row);
            }
        }

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

    public MapSubScene(final Dungeon theDungeon, final ArrayList<Point> theRevealedRooms) {

        this.myDungeon = theDungeon;
        this.myNumRows = theDungeon.getHeight();
        this.myNumColumns = theDungeon.getWidth();
        this.myRevealedRooms = new ArrayList<>();

        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Populate the GridPane with rectangles representing rooms
        for (int row = 0; row < myNumRows; row++) {
            for (int col = 0; col < myNumColumns; col++) {
                final Rectangle roomRect = new Rectangle(50, 50, Color.WHITE);

                // Check if the current room has been visited
                if (myDungeon.get(col, row).hasBeenVisited()) {
                    roomRect.setFill(Color.GREEN);
                }

                // Check if the current room is the player's current position
                if (row == FXGL.geti("playerY") && col == FXGL.geti("playerX")) {
                    roomRect.setFill(Color.BLUE);
                }

                gridPane.add(roomRect, col, row);
            }
        }

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

    public void updateMap() {
        final GridPane gridPane = (GridPane) getContentRoot().getChildren().get(0);
        gridPane.getChildren().clear();

        // Populate the GridPane with rectangles representing rooms
        for (int row = 0; row < myNumRows; row++) {
            for (int col = 0; col < myNumColumns; col++) {
                final StackPane stackPane = new StackPane();

                // Create a rectangle for the room
                final Rectangle roomRect = new Rectangle(50, 50, Color.WHITE);

                // Check if the current room is the player's current position
                if (row == FXGL.geti("playerY") && col == FXGL.geti("playerX")) {
                    roomRect.setFill(Color.BLUE);
                } else if (myDungeon.get(col, row).hasBeenVisited()) {
                    roomRect.setFill(Color.GREEN);
                }

                // Check if the room is in the revealed room list
                if (myRevealedRooms.contains(new Point(col, row))) {
                    // Add text to the rectangle
                    System.out.println("reached");
                    final Text text = new Text(roomObjects(col, row));
                    text.setFill(Color.BLACK);
                    stackPane.getChildren().addAll(roomRect, text);
                } else {
                    stackPane.getChildren().add(roomRect);
                }

                gridPane.add(stackPane, col, row);
            }
        }
    }

    public String roomObjects(final int theCol, final int theRow) {
        final StringBuilder sb = new StringBuilder();

        if (myDungeon.get(theCol, theRow).hasPillar()) {
            sb.append(" P");
        }
        if (myDungeon.get(theCol, theRow).hasHealPot()) {
            sb.append(" H");
        }
        if (myDungeon.get(theCol, theRow).hasVisPot()) {
            sb.append(" V");
        }
        if (myDungeon.get(theCol, theRow).hasPit()) {
            sb.append(" X");
        }
        return sb.toString();
    }

    public void setRevealedRooms() {
        System.out.println("trying to set rooms to reveal");
        final int playerX = FXGL.geti("playerY");
        final int playerY = FXGL.geti("playerX");

        for (int i = playerY - 1; i <= playerY + 1; i++) {
            for (int j = playerX - 1; j <= playerX + 1; j++) {
                // Add valid adjacent positions to the revealedRooms list
                if (isValidPosition(i, j)) {
                    myRevealedRooms.add(new Point(i, j));
                }
            }
        }
        System.out.println(myRevealedRooms);
    }

    private boolean isValidPosition(final int theY, final int theX) {
        return theY >= 0 && theY < myNumColumns && theX >= 0 && theX < myNumRows;
    }

    public ArrayList<Point> getRevealedRooms() {
        return this.myRevealedRooms;
    }

    public void setRevealedRoom(final Point thePoint) {
        myRevealedRooms.add(thePoint);
    }


}
