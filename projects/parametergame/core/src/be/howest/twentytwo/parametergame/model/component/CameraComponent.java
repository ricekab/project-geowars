package be.howest.twentytwo.parametergame.model.component;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Tracking information for a camera. Can track a position between multiple points as defined by the
 * given transform components.
 */
public class CameraComponent implements Component, Poolable {

	public static final ComponentMapper<CameraComponent> MAPPER = ComponentMapper
			.getFor(CameraComponent.class);

	private Camera camera;
	private Map<TransformComponent, Integer> trackingPoints;
	private int totalWeight;

	public CameraComponent() {
		setTrackingPointMap(new HashMap<TransformComponent, Integer>());
		setTotalWeight(0);
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Map<TransformComponent, Integer> getTrackingPoints() {
		return trackingPoints;
	}

	/**
	 * Adds a tracking point for the camera with given weight. Non-positive weights are ignored. If
	 * the transform component is already tracked, it is ignored. If you want to change the weight
	 * of an existing point, use {@link #setTrackPointWeight(TransformComponent, int)}.
	 */
	public void addTrackPoint(TransformComponent transform, int weight) {
		if(weight > 0 && !getTrackingPoints().containsKey(transform)) {
			getTrackingPoints().put(transform, weight);
			setTotalWeight(getTotalWeight() + weight);
		}
	}

	/**
	 * Adds a tracking point for the given entity, using its transform component. If the given
	 * entity does not have a tracking point, an (TODO: What error) error is thrown.
	 */
	public void addTrackPoint(Entity entity, int weight) {
		TransformComponent tc = TransformComponent.MAPPER.get(entity);
		if(tc != null) {
			addTrackPoint(tc, weight);
		}
		// TODO: Throw some error here.
	}

	/**
	 * Return the current weight for the given transform component. If this component isn't tracked,
	 * returns -1.
	 */
	public int getTrackPointWeight(TransformComponent transform) {
		if(getTrackingPoints().containsKey(transform)) {
			return getTrackingPoints().get(transform);
		}
		return -1;
	}

	/**
	 * Changes the track point weight for the given component. If this component isn't tracked, this
	 * does nothing.
	 */
	public void setTrackPointWeight(TransformComponent transform, int newWeight) {
		if(getTrackingPoints().containsKey(transform)) {
			int diff = newWeight - getTrackPointWeight(transform);
			setTotalWeight(getTotalWeight() + diff);
			getTrackingPoints().put(transform, newWeight);
		}

	}

	public void removeTrackPoint(TransformComponent transform, int weight) {
		if(getTrackingPoints().containsKey(transform)) {
			getTrackingPoints().remove(transform);
			setTotalWeight(getTotalWeight() - weight);
		}
	}

	public void removeTrackPoint(Entity entity, int weight) {
		TransformComponent tc = TransformComponent.MAPPER.get(entity);
		if(tc != null) {
			removeTrackPoint(tc, weight);
		}
		// TODO: Throw some error here.
	}

	private void setTrackingPointMap(Map<TransformComponent, Integer> trackingPoint) {
		this.trackingPoints = trackingPoint;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	private void setTotalWeight(int weight) {
		this.totalWeight = weight;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
