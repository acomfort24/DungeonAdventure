package view;
import com.almasb.fxgl.dsl.FXGL;
import controller.DungeonApp;
import java.util.Collections;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * The HeroSelectScene class represents a scene for selecting a hero character in the dungeon adventure game.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class HeroSelectScene extends HBox {
    /** */
    private final Map<String, Map<String, String>> myDBData;
    public HeroSelectScene(final Map<String, Map<String, String>> theDBData) {
        super();
        myDBData = theDBData;
        createPanelButton("Warrior");
        createPanelButton("Thief");
        createPanelButton("Wizard");
        this.setSpacing(50);
        this.setLayoutX(150);
        this.setLayoutY(250);
    }
    /**
     * Creates a panel button for the specified hero.
     *
     * @param theName The name of the hero as a string.
     */
    private void createPanelButton(final String theName) {
        final Button button = new Button();
        button.setText(
                myDBData.get(theName).get("name")
                        + "\nMax Health: " + myDBData.get(theName).get("hitPoints")
                        + "\nMin Dmg: " + myDBData.get(theName).get("minDmg")
                        + "\nMax Dmg: " + myDBData.get(theName).get("maxDmg")
                        + "\nAtk Spd: " + myDBData.get(theName).get("atkSpd")
                        + "\nChnc Hit: " + myDBData.get(theName).get("chncHit")
                        + "\nChnc Blk: " + myDBData.get(theName).get("chncBlock")
        );
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefSize(250, 300);
        button.setFont(new Font(26));
        button.setTextFill(Color.WHITE);
        button.setOnAction(e ->
                FXGL.getDialogService().showInputBox("Enter your player name.", name -> {
                    DungeonApp.setPlayerName(name);
                    DungeonApp.setCharacterName(theName);
                    FXGL.getGameController().startNewGame();
                    this.setVisible(false);
                }));
        button.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.BLACK,
                                new CornerRadii(0),
                                new Insets(0))),
                        Collections.singletonList(new BackgroundImage(
                                FXGL.image(theName + ".png"),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT)
                        )));
        button.setBorder(new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button.setFont(new Font(26));
        this.getChildren().add(button);
    }
}
