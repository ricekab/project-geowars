package be.howest.twentytwo.parametergame.model.event.collision;

import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.gamedata.HealthData;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Fixture;

public class EnemyHitEvent extends BaseCollisionEvent {

	private final HealthData enemyHealth;
	private final float damage;

	public EnemyHitEvent(Fixture subject, Fixture collider, float damage) {
		super(subject, collider);
		this.enemyHealth = HealthComponent.MAPPER.get(getEnemyEntity()).getHealthData();
		this.damage = damage;
	}

	/**
	 * Shorthand constructor to deal 1 damage.
	 */
	public EnemyHitEvent(Fixture subject, Fixture collider) {
		this(subject, collider, 1f);
	}

	public HealthData getPlayerHealth() {
		return enemyHealth;
	}

	public float getDamage() {
		return damage;
	}

	/** Alias to retrieve collision subject. */
	public Entity getEnemyEntity() {
		return super.getSubject();
	}

	@Override
	public EventEnum getType() {
		return EventEnum.ENEMY_HIT;
	}

}
