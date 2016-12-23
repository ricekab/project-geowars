package be.howest.twentytwo.parametergame.model.ai;

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class NullAIShootBehaviour implements IAIShootBehaviour {

    @Override
    public void shoot(Body ai,WeaponComponent weapon, Vector2 target) {
    }

    @Override
    public float getCooldown() {
        return 0.0f;
    }

    @Override
    public float getRange() {
        return 0.0f;
    }

    @Override
    public void setCanShoot(boolean canShoot) {
    }

    @Override
    public boolean getCanShoot() {
        return false;
    }

}
