package controller.collisionhandlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import controller.InventoryController;
import model.EntityType;
import model.dungeonmap.Dungeon;

public class PlayerMonsterHandler extends CollisionHandler {
    private final Dungeon myDungeon;

    public PlayerMonsterHandler(final EntityType theEntityType, final Dungeon theDungeon) {
        super(EntityType.PLAYER, theEntityType);
        myDungeon = theDungeon;
    }

    @Override
    protected void onCollisionBegin(final Entity thePlayer, final Entity theMonster) {
        String monsterType = theMonster.getType().toString();

        if ("SKELETON".equals(monsterType) || "GOBLIN".equals(monsterType) || "OGRE".equals(monsterType)) {
            //
        }

    }

    @Override
    protected void onCollision(final Entity theP, final Entity theI) { }
    @Override
    protected void onCollisionEnd(final Entity theP, final Entity theI) {

    }

}
