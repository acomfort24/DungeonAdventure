package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import controller.DungeonApp;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DungeonMainMenu extends FXGLMenu {
    static HeroSelectScene mySelectScreen = new HeroSelectScene(DungeonApp.myDBData);
    static LoadSelectScene myLoadSelectScreen = new LoadSelectScene();

    public DungeonMainMenu() {
        super(MenuType.MAIN_MENU);
        mySelectScreen.setVisible(false);
        myLoadSelectScreen.setVisible(false);
        final Node node = new ImageView("assets/textures/background/MainMenu.png");
        node.resize(FXGL.getAppWidth(), FXGL.getAppHeight());
        getContentRoot().getChildren().add(node);
        getContentRoot().getChildren().add(mySelectScreen);
        getContentRoot().getChildren().add(myLoadSelectScreen);
        final DungeonAdventureButton btnPlayGame = new DungeonAdventureButton("Play Game",
                "Start a new game", () -> {
            mySelectScreen.setVisible(true);
            myLoadSelectScreen.setVisible(false);
        });
        final DungeonAdventureButton btnOptions = new DungeonAdventureButton("Options",
                "Change in-game settings", () -> { });
        final DungeonAdventureButton btnLoad = new DungeonAdventureButton("Load",
                "Load a previous game", () -> {
            myLoadSelectScreen.setVisible(true);
            mySelectScreen.setVisible(false);
        });

        final DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit Game",
                "Quit to desktop", () -> fireExit());
        final boolean showClasses = false;
        final var box = new VBox(15,
                btnPlayGame,
                btnOptions,
                btnLoad,
                btnQuit,
                FXGL.getUIFactoryService().newText("Welcome to Dungeon Adventure"));

        box.setAlignment(Pos.CENTER_LEFT);
        box.setTranslateX(100);
        box.setTranslateY(590);
        getContentRoot().getChildren().add(box);

    }


}
