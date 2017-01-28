package be.howest.twentytwo.parametergame.model.ai;

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author Ward
 */
public interface IAIShootBehaviour {
    
    
    public float getCooldown();
    public float getRange();
    public void setCanShoot(boolean canShoot);
    public boolean getCanShoot();
    
    /** Modifies the given move component to achieve it's objective. */
	public void shoot(Body ai,WeaponComponent weapon, Vector2 target);

    
}
