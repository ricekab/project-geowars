package be.howest.twentytwo.parametergame.model.event.collision;

import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.gamedata.HealthData;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PlayerHitEvent extends BaseCollisionEvent {

	private final HealthData playerHealth;
	private final float damage;

	public PlayerHitEvent(Fixture subject, Fixture collider, float healthDamage) {
		super(subject, collider);
		this.playerHealth = HealthComponent.MAPPER.get(getPlayerEntity()).getHealthData();
		this.damage = healthDamage;
	}

	/**
	 * Shorthand constructor to deal 1 damage.
	 */
	public PlayerHitEvent(Fixture subject, Fixture collider) {
		this(subject, collider, 1f);
	}

	public HealthData getPlayerHealth() {
		return playerHealth;
	}

	public float getDamage() {
		return damage;
	}

	/** Alias to retrieve collision subject. */
	public Entity getPlayerEntity() {
		return super.getSubject();
	}

	@Override
	public EventEnum getType() {
		return EventEnum.PLAYER_HIT;
	}

}
