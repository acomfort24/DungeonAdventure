package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.concurrent.IOTask;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveFile;
import controller.DungeonApp;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class DungeonMainMenu extends FXGLMenu {
    static HeroSelectScene mySelectScreen = new HeroSelectScene(DungeonApp.myDBData);
    public DungeonMainMenu() {
        super(MenuType.MAIN_MENU);


        mySelectScreen.setVisible(false);
        final Node node = new ImageView("assets/textures/background/MainMenu.png");
        node.resize(FXGL.getAppWidth(), FXGL.getAppHeight());
        getContentRoot().getChildren().add(node);
        getContentRoot().getChildren().add(mySelectScreen);
        final DungeonAdventureButton btnPlayGame = new DungeonAdventureButton("Play Game",
                "Start a new game", () -> {
            mySelectScreen.setVisible(true);
        });
        final DungeonAdventureButton btnOptions = new DungeonAdventureButton("Options",
                "Change in-game settings", () -> { });
        final DungeonAdventureButton btnLoad = new DungeonAdventureButton("Load",
                "Load a previous game", () -> {

            getSaveLoadService().readAndLoadTask("asdf.sav").run();
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
    private static List<String> findFiles(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    // this is a path, not string,
                    // this only test if path end with a certain path
                    //.filter(p -> p.endsWith(fileExtension))
                    // convert path to string first
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }

        return result;
    }

}
