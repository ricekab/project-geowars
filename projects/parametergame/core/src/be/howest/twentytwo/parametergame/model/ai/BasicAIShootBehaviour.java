package be.howest.twentytwo.parametergame.model.ai;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class BasicAIShootBehaviour implements IAIShootBehaviour {

    private float waitTillFire = 10;
    private float cooldown;
    private float range;
    private boolean canShoot;

    @Override
    public boolean getCanShoot() {
        return canShoot;
    }

    @Override
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
    
    public BasicAIShootBehaviour(float cooldown, float range)
    {
        this.cooldown = cooldown;
        this.range = range;
        this.canShoot = true;
    }
    
    @Override
    public void shoot(Body ai,WeaponComponent weapon, Vector2 target) {
        if(canShoot){
            --waitTillFire;
            Vector2 directionToTarget = target.cpy();
            directionToTarget.sub(ai.getPosition());
            float distance = directionToTarget.len2();

            if(waitTillFire < 1 && distance < getRange() * getRange())
            {
                weapon.setFirePrimary(true);
                waitTillFire = getCooldown();
            }
            else
            {
                weapon.setFirePrimary(false);            
            }
        }
    }

    @Override
    public float getCooldown() {
        return cooldown;
    }

    @Override
    public float getRange() {
        return range;
    } 
}
