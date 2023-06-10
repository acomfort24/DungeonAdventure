package model.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.physics.PhysicsComponent;


public class PlayerComponent extends Component {
    /** */
    private static final Inventory MY_INVENTORY = new Inventory<>(100);
    /** */
    protected PhysicsComponent myPhysics;
    /** */
    private int myVelocity = 325;
    /** */
    private CharacterComponent myCharacterComponent;
    /** */
    private double myChncBlock;
    
    public PlayerComponent(final int theMinDmg, final int theMaxDmg,
                           final int theAtkSpd, final double theChncHit,
                           final int theHealth, final String theName, final double theChncBlock) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg, theMaxDmg, theAtkSpd,
                theChncHit, theHealth, theName);
        myChncBlock = theChncBlock;
    }
    
    public void down() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(myVelocity);
    }
    
    public void up() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(-myVelocity);
    }
    public void left() {
        getEntity().setScaleX(-1);
        myPhysics.setVelocityX(-myVelocity);
    }
    public void right() {
        getEntity().setScaleX(1);
        myPhysics.setVelocityX(myVelocity);
    }
    
    public void stop() {
        myPhysics.setLinearVelocity(0, 0);
    }
    
    public void pausePlayer() {
        myVelocity = 0;
    }
    
    public void resumePlayer() {
        myVelocity = 300;
    }

    public static Inventory getMyInventory() {
        return MY_INVENTORY;
    }
    public String getName() {
        return myCharacterComponent.getMyName();
    }
    public double getMyChncBlock() {
        return myChncBlock;
    }
    
    public double getAtkSpeed() {
        return myCharacterComponent.getMyAtkSpd();
    }
    public CharacterComponent getMyCharacterComponent() {
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

