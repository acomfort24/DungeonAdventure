package model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

public class PlayerDoorHandler extends CollisionHandler {
    private static final String PLAYER_Y_PROPERTY = "playerY";
    private static final String PLAYER_X_PROPERTY = "playerX";

    public PlayerDoorHandler() {
        super(EntityType.PLAYER, EntityType.NORTH_DOOR);
    }

    @Override
    protected void onCollisionBegin(final Entity theP, final Entity theD) {
        final String type = theD.getType().toString();
        switch (type) {
            case "NORTH_DOOR" -> FXGL.getGameWorld().getProperties().increment(PLAYER_Y_PROPERTY, -1);
            case "SOUTH_DOOR" -> FXGL.getGameWorld().getProperties().increment(PLAYER_Y_PROPERTY, 1);
            case "WEST_DOOR" -> FXGL.getGameWorld().getProperties().increment(PLAYER_X_PROPERTY, -1);
            case "EAST_DOOR" -> FXGL.getGameWorld().getProperties().increment(PLAYER_X_PROPERTY, 1);
            default -> {
                // handle unexpected door types here
            }
        }

    }
}