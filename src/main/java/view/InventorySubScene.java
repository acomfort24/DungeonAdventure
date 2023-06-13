package view;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.inventory.view.InventoryView;
import com.almasb.fxgl.scene.SubScene;
import javafx.scene.control.Button;
import model.EntityType;
import model.components.PlayerComponent;
import model.components.PotionComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class InventorySubScene extends SubScene {

    public Inventory<Entity> myInventory;

    public InventoryView<Entity> myInvView;
    public InventorySubScene() {
        var player = FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
        myInventory = player.getComponent(PlayerComponent.class).getInventory();
        myInvView = new InventoryView<>(myInventory);

        getContentRoot().getChildren().addAll(myInvView);
        getContentRoot().setTranslateX(300);
        getContentRoot().setTranslateY(0);

        Button useItem = getUIFactoryService().newButton("Use");
        useItem.prefHeight(30.0);
        useItem.prefWidth(135.0);
        useItem.setTranslateX(35.0);
        useItem.setTranslateY(320.0);

        useItem.setOnAction(actionEvent -> {
            var selectedItem = (Entity) myInvView.getListView().getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                var item = this.myInventory.getData(selectedItem).get().getUserItem();
                item.call("use");
                myInventory.incrementQuantity(item, -1);
            }
            myInvView.getListView().refresh();
        });
    }
}
