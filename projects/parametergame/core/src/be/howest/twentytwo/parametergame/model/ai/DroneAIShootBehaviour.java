package be.howest.twentytwo.parametergame.model.ai;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class DroneAIShootBehaviour implements IAIShootBehaviour{

    @Override
    public void titsAndSugar(Body ai,WeaponComponent weapon, Vector2 target) {
        //Target an enemy how ????
        //For shooting possible to use Basic AIShootbehav
    }

    @Override
    public float getCooldown() {
        return 0.0f;
    }

    @Override
    public float getRange() {
        return 0.0f;
    }

}
