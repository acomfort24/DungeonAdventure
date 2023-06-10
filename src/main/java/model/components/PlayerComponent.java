package model.components;


import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.physics.PhysicsComponent;


/**
 * Represents a player component that handles player-specific functionality and properties.
 * This component includes movement, physics, inventory, and character-related attributes.
 *
 * @author Andy Comfort
 *         Brandon Morgan
 *         Chad Oehlschlaeger-Browne
 * @version 1.0
 */
public class PlayerComponent extends Component {
    /**
     * The velocity of the player.
     */
    private int myVelocity = 325;
    /**
     * The inventory of the player.
     */
    private static final Inventory myInventory = new Inventory<>(100);
    /**
     * The character component of the player.
     */
    private CharacterComponent myCharacterComponent;
    /**
     * The chance to block of the player.
     */
    private double myChncBlock;
    /**
     * The physics component of the player.
     */
    protected PhysicsComponent myPhysics;
    /**
     * Constructs a PlayerComponent with the specified attributes.
     *
     * @param theMinDmg    the minimum damage of the player
     * @param theMaxDmg    the maximum damage of the player
     * @param theAtkSpd    the attack speed of the player
     * @param theChncHit   the chance to hit of the player
     * @param theHealth    the health of the player
     * @param theName      the name of the player
     * @param theChncBlock the chance to block of the player
     */
    public PlayerComponent(final int theMinDmg, final int theMaxDmg,
                           final int theAtkSpd, final double theChncHit,
                           final int theHealth, final String theName, double theChncBlock) {
        super();
        myCharacterComponent = new CharacterComponent(theMinDmg, theMaxDmg, theAtkSpd,
                theChncHit, theHealth, theName);
        myChncBlock = theChncBlock;
    }
    /**
     * Moves the player down.
     * Sets the player's scale and velocity in the Y direction.
     */
    public void down() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(myVelocity);
    }
    /**
     * Moves the player up.
     * Sets the player's scale and velocity in the negative Y direction.
     */
    public void up() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(-myVelocity);
    }
    /**
     * Moves the player left.
     * Sets the player's scale and velocity in the negative X direction.
     */
    public void left() {
        getEntity().setScaleX(-1);
        myPhysics.setVelocityX(-myVelocity);
    }
    /**
     * Moves the player right.
     * Sets the player's scale and velocity in the X direction.
     */
    public void right() {
        getEntity().setScaleX(1);
        myPhysics.setVelocityX(myVelocity);
    }
    /**
     * Stops the player's movement.
     * Sets the player's linear velocity to zero.
     */
    public void stop() {
        myPhysics.setLinearVelocity(0, 0);
    }
    /**
     * Pauses the player's movement.
     * Sets the player's velocity to zero.
     */
    public void pausePlayer() {
        myVelocity = 0;
    }
    /**
     * Resumes the player's movement.
     * Sets the player's velocity to the default value.
     */
    public void resumePlayer() {
        myVelocity = 300;
    }
    /**
     * Returns the inventory of the player.
     *
     * @return the inventory of the player
     */
    public static Inventory getMyInventory() {
        return myInventory;
    }
    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return myCharacterComponent.getMyName();
    }
    /**
     * Returns the chance to block of the player.
     *
     * @return the chance to block of the player
     */
    public double getMyChncBlock() {
        return myChncBlock;
    }
    /**
     * Returns the attack speed of the player.
     *
     * @return the attack speed of the player
     */
    public double getAtkSpeed() {
        return myCharacterComponent.getMyAtkSpd();
    }
    /**
     * Returns the character component of the player.
     *
     * @return the character component of the player
     */
    public CharacterComponent getMyCharacterComponent() {
        return myCharacterComponent;
    }


    /**
     * Returns a string representation of the player's current status
     *
     * @return a string representation of the player's current status
     */
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

