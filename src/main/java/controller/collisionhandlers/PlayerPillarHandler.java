package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import model.EntityType;
import model.dungeonmap.Dungeon;


public class PlayerPillarHandler extends CollisionHandler {
    /** */
    private final Dungeon myDungeon;
    public PlayerPillarHandler(final Dungeon theDungeon) {
        super(EntityType.PLAYER, EntityType.PILLAR);
        myDungeon = theDungeon;
    }
    
    @Override
    protected void onCollisionBegin(final Entity theP, final Entity thePill) {
        FXGL.getWorldProperties().increment("pillars", 1);
        myDungeon.get(FXGL.geti("playerX"), FXGL.geti("playerY")).setPillar(false);
        thePill.removeFromWorld();
    }
}
