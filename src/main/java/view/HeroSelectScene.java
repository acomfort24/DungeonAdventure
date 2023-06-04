package view;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.scene.SceneService;
import com.almasb.fxgl.scene.SubScene;
import controller.DungeonApp;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.DungeonFactory;

import java.util.Map;

public class HeroSelectScene extends HBox {
    Map<String, Map<String, String>> myDBData;
    public HeroSelectScene(Map<String, Map<String, String>> theDBData) {
        super();
        myDBData = theDBData;
        createPanelButton("Warrior");
        createPanelButton("Thief");
        createPanelButton("Priestess");
    }
    private void createPanelButton(String theName) {
        Button button = new Button();
        button.setGraphic(new ImageView("file:resources/assets/textures/exit0.png"));
        button.setText(
                "Class: " + myDBData.get(theName).get("name")
                        + "\nMax Health: " + myDBData.get(theName).get("hitPoints")
                        + "\nMin Dmg: " + myDBData.get(theName).get("minDmg")
                        + "\nMax Dmg: " + myDBData.get(theName).get("maxDmg")
                        + "\nAtk Spd: " + myDBData.get(theName).get("atkSpd")
                        + "\nChnc Hit: " + myDBData.get(theName).get("chncHit")
                        + "\nChnc Blk: " + myDBData.get(theName).get("chncBlock")
        );
        button.setOnAction(e -> {
            DungeonApp.setMyPlayerName(theName);
            FXGL.getGameController().startNewGame();
            DungeonMainMenu.mySelectScreen.setVisible(false);
        });
       this.getChildren().add(button);
    }
}
