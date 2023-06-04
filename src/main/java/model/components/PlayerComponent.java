package model.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.physics.PhysicsComponent;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

import controller.DatabaseController;
import controller.DungeonApp;
import javafx.scene.image.Image;

import java.util.Map;


public class PlayerComponent extends Component {
    private static CharacterComponent myCharacterComponent;
    private double myChncBlock;
    /** */
    private static final int PLAYER_VELOCITY = 350;
    /** */
    private static final Inventory myInventory = new Inventory<>(100);
    /** */
    protected PhysicsComponent myPhysics;
    
    public PlayerComponent(int theMinDmg, int theMaxDmg, int theAtkSpd, Double theChncHit, int theHealth, String theName) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg, theMaxDmg, theAtkSpd, theChncHit, theHealth, theName);
    }
    
    public void down() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(PLAYER_VELOCITY);
    }
    
    public void up() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(-PLAYER_VELOCITY);
    }
    public void left() {
        getEntity().setScaleX(-1);
        myPhysics.setVelocityX(-PLAYER_VELOCITY);
    }
    public void right() {
        getEntity().setScaleX(1);
        myPhysics.setVelocityX(PLAYER_VELOCITY);
    }
    
    public void stop() {
        myPhysics.setLinearVelocity(0, 0);
    }

    public static Inventory getMyInventory() {
        return myInventory;
    }
    public static String getName() {
        return myCharacterComponent.getMyName();
    }
    public double getMyChncBlock() {
        return myChncBlock;
    }
}

