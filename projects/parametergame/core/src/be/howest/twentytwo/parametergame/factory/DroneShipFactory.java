package be.howest.twentytwo.parametergame.factory;

//@author Ward Van den Berghe
import be.howest.twentytwo.parametergame.dataTypes.DroneData;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.FixtureDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.model.ai.AITargetBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.component.AIComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import java.util.Collection;

public class DroneShipFactory implements ISpawnFactory {
    private static final String SHIP_SPRITE_PACK = "sprites/game.pack";
    private ShipFactory shipFactory;
    private PooledEngine engine;
    private World world;
    private AssetManager assets;

    private DroneDataI droneData;
    private Body target;
    private IAIMoveBehaviour moveBehaviour;
    private IAIShootBehaviour shootBehaviour;
    private AITargetBehaviour targetBehaviour;
    private PhysicsDataI physicsData;
    private BodyDef bodyDef;
    private float gravityResistance;
    private String texture;
    private Collection<FixtureDef> fixtureDefs;
    private TextureRegion sprite;

    public DroneShipFactory(PooledEngine engine, World world, AssetManager assets, DroneDataI droneData,
            Body target, IAIMoveBehaviour moveBehaviour, IAIShootBehaviour shootBehaviour,
            AITargetBehaviour targetBehaviour) {
        //Look at shipfactory and copy what's needed
        this.physicsData = makeDronePhysiscsData();
        this.engine = engine;
        this.world = world;
        this.assets = assets;
        this.droneData = droneData;
        this.target = target;
        this.moveBehaviour = moveBehaviour;
        this.shootBehaviour = shootBehaviour;
        this.targetBehaviour = targetBehaviour;
        collectSharedDefinitions(physicsData);
    }

    private PhysicsDataI makeDronePhysiscsData() {
        //@Kevin physics data for drone here
        //gravityResistance = ?f;
        //texture=?;
        return null;
    }

    private void collectSharedDefinitions(PhysicsDataI physicsData) {
        Collection<FixtureDataI> fixturesData = physicsData.getFixtures();

        // BODY DEF
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // bodyDef.fixedRotation = true;

        // FIXTURE DEFS
        FixtureDef fixtureDef;
        FixtureFactory fixtureFactory = new FixtureFactory();
        for (FixtureDataI fd : fixturesData) {
            fixtureDef = fixtureFactory.createFixtureDef(fd.getShape(), fd.getWidth(),
                    fd.getHeight(), fd.getOffsetX(), fd.getOffsetY(),
                    fd.getDensity() / gravityResistance, fd.getFriction(),
                    fd.getRestitution());
            fixtureDefs.add(fixtureDef);
        }

        // TODO: Calculate world size based on fixtures (bounding box for all
        // fixtures, maybe aabb all fixtures)
        // TEXTURE/SPRITE
        TextureAtlas spritesheet = assets.get(SHIP_SPRITE_PACK, TextureAtlas.class);
        TextureRegion region = spritesheet.findRegion(texture);
        this.sprite = region;
    }

    @Override
    public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory, short physicsMask) {
        Entity drone = shipFactory.createShip(pos, rotation, Collision.BULLET_PLAYER_CATEGORY,
                Collision.BULLET_PLAYER_MASK);
        AIComponent ai = engine.createComponent(AIComponent.class);
        ai.setMoveBehaviour(moveBehaviour);
        ai.setShootBehaviour(shootBehaviour);
        ai.setTargetBehaviour(targetBehaviour);
        drone.add(ai);
        engine.addEntity(drone);
        return drone;
    }

    @Override
    public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
        return spawnEntity(pos, rotation, initialVelocity, Collision.BULLET_PLAYER_CATEGORY,
                Collision.BULLET_PLAYER_MASK);
    }

    @Override
    public String getType() {
        return shipFactory.getType();
    }
}
