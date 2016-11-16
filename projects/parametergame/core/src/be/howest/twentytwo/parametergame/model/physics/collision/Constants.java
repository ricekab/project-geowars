package be.howest.twentytwo.parametergame.model.physics.collision;

/**
 * Purely static class to define category and mask bits for physics interactions.
 */
public class Constants {
	// CATEGORY BITS
	public static final short PLAYER_CATEGORY = 0x0001;
	public static final short ENEMY_CATEGORY = 0x0002;
	public static final short PLANET_CATEGORY = 0x0004;
	public static final short GRAVITY_CATEGORY = 0x0008;
	public static final short BULLET_PLAYER_CATEGORY = 0x0010;
	public static final short BULLET_ENEMY_CATEGORY = 0x0020;
	public static final short PLAYER_PICKUPS = 0x0040;

	// MASKS
	// TODO: Do players collide with their own bullets?
	public static final short PLAYER_MASK = ENEMY_CATEGORY | PLANET_CATEGORY | GRAVITY_CATEGORY | BULLET_ENEMY_CATEGORY
			| PLAYER_PICKUPS;
	public static final short ENEMY_MASK = PLAYER_CATEGORY | PLANET_CATEGORY | GRAVITY_CATEGORY | BULLET_PLAYER_CATEGORY;
	public static final short PLANET_MASK = PLAYER_CATEGORY | ENEMY_CATEGORY | BULLET_PLAYER_CATEGORY | BULLET_ENEMY_CATEGORY;
	public static final short PICKUP_MASK = PLAYER_CATEGORY | PLAYER_PICKUPS;

}
