package view;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import controller.DungeonApp;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
/**
 * The DungeonMainMenu class represents the main menu screen of a dungeon adventure game.
 * It extends the FXGLMenu class, which provides a base implementation for creating game menus in FXGL.
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */

public class DungeonMainMenu extends FXGLMenu {
    /** The scene for selecting a hero. */
    private final HeroSelectScene mySelectScreen = new HeroSelectScene(DungeonApp.getDatabase());
    /** The scene for selecting a save file to load. */
    private final LoadSelectScene myLoadSelectScreen = new LoadSelectScene();

    /**
     * Constructs a new DungeonMainMenu.
     */
    public DungeonMainMenu() {
        super(MenuType.MAIN_MENU);
        mySelectScreen.setVisible(false);
        myLoadSelectScreen.setVisible(false);
        final Node node = new ImageView("assets/textures/background/MainMenu.png");
        node.resize(FXGL.getAppWidth(), FXGL.getAppHeight());
        getContentRoot().getChildren().add(node);
        getContentRoot().getChildren().add(mySelectScreen);
        getContentRoot().getChildren().add(myLoadSelectScreen);
        final DungeonAdventureButton btnPlayGame =
                new DungeonAdventureButton("Play Game", () -> {
                    mySelectScreen.setVisible(true);
                    myLoadSelectScreen.setVisible(false);
                });
        final DungeonAdventureButton btnOptions =
                new DungeonAdventureButton("Options", () -> {

                });
        final DungeonAdventureButton btnLoad = new DungeonAdventureButton("Load", () -> {
            myLoadSelectScreen.setVisible(true);
            mySelectScreen.setVisible(false);
        });

        final DungeonAdventureButton btnAbout = new DungeonAdventureButton("About", () -> {
            FXGL.getDialogService().showMessageBox("""
                                                                Welcome to Dungeon Adventure!
                    
                    In this game you will select from three classes (Warrior, Thief, Wizard) and
                    attempt to traverse the dungeon. Along the way you'll encounter four pillars, 
                    where each pillar represents one of the four main principles of object-oriented 
                    programming. In order to escape the dungeon you must collect all four pillars and 
                    bring them to the exit door. 
                    
                    Good luck!
                    """);
        });

        final DungeonAdventureButton btnQuit =
                new DungeonAdventureButton("Quit Game", this::fireExit);
        final var box = new VBox(15,
                btnPlayGame,
                btnOptions,
                btnLoad,
                btnAbout,
                btnQuit,
                new Separator(),
                FXGL.getUIFactoryService().newText("Welcome to Dungeon Adventure"));

        box.setAlignment(Pos.CENTER_LEFT);
        box.setTranslateX(100);
        box.setTranslateY(590);
        getContentRoot().getChildren().add(box);
    }
}