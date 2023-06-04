package controller.collisionhandlers;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppHeight;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthDoubleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;

public class PlayerDoorHandler extends CollisionHandler {
    private static final String PLAYER_Y_PROPERTY = "playerY";
    private static final String PLAYER_X_PROPERTY = "playerX";

    public PlayerDoorHandler(final EntityType theEntityType) {
        super(EntityType.PLAYER, theEntityType);
    }

    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theD) {
        final String type = theD.getType().toString();
        switch (type) {
            case "NORTH_DOOR" -> {
                System.out.println(theP.getComponent(HealthDoubleComponent.class).getValue());
                theP.getComponent(HealthDoubleComponent.class).damage(10);
                System.out.println(theP.getComponent(HealthDoubleComponent.class).getValue());
                FXGL.getWorldProperties().setValue("spawnX", (double) getAppWidth() / 2 - 48);
                FXGL.getWorldProperties().setValue("spawnY", (double) getAppHeight() - 160);
                FXGL.getWorldProperties().increment(PLAYER_Y_PROPERTY, -1);
            }
            case "SOUTH_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) getAppWidth() / 2 - 48);
                FXGL.getWorldProperties().setValue("spawnY", (double) 150);
                FXGL.getWorldProperties().increment(PLAYER_Y_PROPERTY, 1);
            }
            case "WEST_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) getAppWidth() - 170);
                FXGL.getWorldProperties().setValue("spawnY", (double) getAppHeight() / 2 - 48);
                FXGL.getWorldProperties().increment(PLAYER_X_PROPERTY, -1);
            }
            case "EAST_DOOR" -> {
                FXGL.getWorldProperties().setValue("spawnX", (double) 70);
                FXGL.getWorldProperties().setValue("spawnY", (double) getAppHeight() / 2 - 48);
                FXGL.getWorldProperties().increment(PLAYER_X_PROPERTY, 1);
            }
            default -> {
                // handle unexpected door types here
            }
        }

    }
}