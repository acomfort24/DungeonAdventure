package view;
import com.almasb.fxgl.dsl.FXGL;
import controller.DungeonApp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Map;

public class HeroSelectScene extends HBox {
    Map<String, Map<String, String>> myDBData;
    public HeroSelectScene(Map<String, Map<String, String>> theDBData) {
        super();
        myDBData = theDBData;
        createPanelButton("Warrior");
        createPanelButton("Thief");
        createPanelButton("Priestess");
        this.setSpacing(50);
        this.setLayoutX(150);
        this.setLayoutY(250);
    }
    private void createPanelButton(String theName) {
        Button button = new Button();
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
        button.setFont(new Font(30));
        button.setOnAction(e -> {
            DungeonApp.setMyPlayerName(theName);
            FXGL.getGameController().startNewGame();
            DungeonMainMenu.mySelectScreen.setVisible(false);
        });
       this.getChildren().add(button);
    }
}
