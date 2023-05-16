package model.components;

import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.image.Image;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.awt.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

    public class PlayerComponent extends Component {


        protected PhysicsComponent physics;


        public PlayerComponent (){
            Image image = image("player.png");
        }


        public void down(){
            getEntity().setScaleY(1);
            physics.setVelocityY(170);
        }

        public void up(){
            getEntity().setScaleY(-1);
            physics.setVelocityY(-170);
        }
        public void left(){
            getEntity().setScaleX(-1);
            physics.setVelocityX(-170);
        }
        public void right(){
            getEntity().setScaleX(1);
            physics.setVelocityX(170);
        }

        public void stop() {
            physics.setLinearVelocity(0, 0);
        }
        
    }

