package model.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.physics.PhysicsComponent;


public class PlayerComponent extends Component {
    /** */
    private static final int PLAYER_VELOCITY = 350;
    /** */
    private static final Inventory myInventory = new Inventory<>(100);
    /** */
    private static CharacterComponent myCharacterComponent;
    /** */
    private double myChncBlock;
    /** */
    protected PhysicsComponent myPhysics;
    
    public PlayerComponent(final int theMinDmg, final int theMaxDmg,
                           final int theAtkSpd, final Double theChncHit,
                           final int theHealth, final String theName, Double theChncBlock) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg, theMaxDmg, theAtkSpd,
                theChncHit, theHealth, theName);
        myChncBlock = theChncBlock;
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
    public static CharacterComponent getMyCharacterComponent() {
        return myCharacterComponent;
    }
    /*
    Leaving this code here even though we're not allowed to override the
    to string method in FXGL to show that we know how to do it.
     */
//    public static String toString() {
//        String string = String.format("Name: %s\nCurrent Health: %f\nHealth Potions:
//        %i\nVision Potions: %i\nPillars Found: ",
//                DungeonApp.getMyPlayerName(),
//                PlayerComponent.getMyInventory().getItemQuantity("HEALTH_POTION"),
//                PlayerComponent.getMyInventory().getItemQuantity("VISION_POTION"),
//                gets("pillars"));
//        return string;
//    }
}

