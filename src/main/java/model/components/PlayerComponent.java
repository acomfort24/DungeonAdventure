package model.components;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.image.Image;

public class PlayerComponent extends Component {
    
    /** */
    private static final int PLAYER_VELOCITY = 170;
    /** */
    protected PhysicsComponent myPhysics;
    
    public PlayerComponent() {
        super();
        final Image image = image("player.png");
    }
    
    public void down() {
        getEntity().setScaleY(1);
        myPhysics.setVelocityY(PLAYER_VELOCITY);
    }
    
    public void up() {
        getEntity().setScaleY(-1);
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
    
}

