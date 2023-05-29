package view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

//public class InventoryMenu extends FXGLMenu {
//    public InventoryMenu {
//        super(MenuType.MAIN_MENU);
//        switchcontentroot
//        final Node node = new ImageView("assets/textures/background/MainMenu.png");
//        node.resize(FXGL.getAppWidth(), FXGL.getAppHeight());
//        getContentRoot().getChildren().add(node);
//
//        for (int i = 0; i < )
//        final DungeonAdventureButton btnPlayGame = new DungeonAdventureButton("Play Game",
//                "Start a new game", () -> fireNewGame());
//        final DungeonAdventureButton btnOptions = new DungeonAdventureButton("Options",
//                "Change in-game settings", () -> { });
//        final DungeonAdventureButton btnQuit = new DungeonAdventureButton("Quit Game",
//                "Quit to desktop", () -> fireExit());
//
//        final var box = new VBox(15,
//                btnPlayGame,
//                btnOptions,
//                btnQuit,
//                FXGL.getUIFactoryService().newText("Welcome to Dungeon Adventure"));
//
//        box.setAlignment(Pos.CENTER_LEFT);
//        box.setTranslateX(100);
//        box.setTranslateY(590);
//        //getContentRoot().getChildren().addAll(FXGL.getUIFactoryService().newButton("hello"));
//        getContentRoot().getChildren().add(box);
//    }
//
//    protected MenuBox createExtraMenu() {
//        log.debug("createExtraMenu()");
//
//        MenuButton itemAchievements = new MenuButton("TROPHIES");
//        itemAchievements.setMenuContent(this::createContentAchievements);
//
//        MenuButton itemCredits = new MenuButton("CREDITS");
//        itemCredits.setMenuContent(this::createContentCredits);
//
//        MenuButton itemFeedback = new MenuButton("FEEDBACK");
//        itemFeedback.setMenuContent(this::createContentFeedback);
//
//        return new MenuBox(200, itemAchievements, itemCredits, itemFeedback);
//    }
//}
