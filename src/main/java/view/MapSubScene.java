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

/**
 * The MapSubScene class represents a subscene that displays a map of the dungeon.
 * It visualizes the rooms of the dungeon and provides methods to update the map and reveal rooms.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class MapSubScene extends SubScene implements Serializable {
    private final int myNumRows;
    private final int myNumColumns;
    private final Dungeon myDungeon;
    private ArrayList<Point> myRevealedRooms;

    /**
     * Constructs a new MapSubScene with the specified dungeon.
     *
     * @param theDungeon The dungeon to display.
     */
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
    /**
     * Constructs a new MapSubScene with the specified dungeon and revealed rooms.
     *
     * @param theDungeon       The dungeon to display.
     * @param theRevealedRooms The list of revealed rooms.
     */
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
    /**
     * Updates the map based on the current state of the dungeon and the player's position.
     */
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
    /**
     * Returns a string representation of the objects in a room.
     *
     * @param theCol The column index of the room.
     * @param theRow The row index of the room.
     * @return A string representation of the objects in the room.
     */
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
    /**
     * Sets the revealed rooms based on the player's current position.
     */
    public void setRevealedRooms() {
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
    }
    /**
     * Checks if the specified position is a valid position in the dungeon.
     *
     * @param theY The y-coordinate of the position.
     * @param theX The x-coordinate of the position.
     * @return true if the position is valid, false otherwise.
     */
    private boolean isValidPosition(final int theY, final int theX) {
        return theY >= 0 && theY < myNumColumns && theX >= 0 && theX < myNumRows;
    }
    /**
     * Returns the list of revealed rooms.
     *
     * @return The list of revealed rooms.
     */
    public ArrayList<Point> getRevealedRooms() {
        return this.myRevealedRooms;
    }
    /**
     * Sets the list of revealed rooms.
     *
     * @param thePoints The list of revealed rooms.
     */
    public void setRevealedRoom(final ArrayList<Point> thePoints) {
        this.myRevealedRooms = thePoints;
    }


}
