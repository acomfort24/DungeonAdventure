package model.components;


import com.almasb.fxgl.entity.Entity;
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
     * The inventory of the player.
     */
    private static final Inventory<Entity> myInventory = new Inventory<>(100);
    /**
     * The physics component of the player.
     */
    protected PhysicsComponent myPhysics;
    /**
     * The velocity of the player.
     */
    private int myVelocity = 325;
    /**
     * The chance to block of the player.
     */
    private final double myChncBlock;
    
    /**
     * Constructs a PlayerComponent with the specified attributes.
     *
     * @param theChncBlock the chance to block of the player
     */
    public PlayerComponent(final double theChncBlock) {
        super();
//        Entity vpot = new Entity();
//        ItemConfig vpic = new ItemConfig("Vision Potion", "Allows you to see surrounding rooms",
//                10, new ImageView("assets/textures/visionpotion.png"));
//        myInventory.add(vpot, vpic, 1);
//
//        Entity hpot = new Entity();
//        ItemConfig hpic = new ItemConfig("Health Potion", "Restores 25HP upon use",
//                10, new ImageView("assets/textures/healthpotion.png"));
//        myInventory.add(hpot, hpic, 1);
//
//        Entity pillar = new Entity();
//        ItemConfig pillic = new ItemConfig("Pillar", "Collect all four pillars to exit the dungeon",
//                4, new ImageView("assets/textures/pillar.png"));
//        myInventory.add(pillar, pillic, 1);

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
        myVelocity = 325;
    }

    public Inventory<Entity> getInventory() {
        return myInventory;
    }
}