package events;

import java.util.List;

import model.GameObjectModel;

public class CollisionEvent implements GameEvent {
	
	private List<GameObjectModel> gameObjectModels;
	
	public CollisionEvent(List<GameObjectModel> gameObjectModels){
		this.gameObjectModels = gameObjectModels;
	}

	@Override
	public List<GameObjectModel> getGameObjects() {
		return gameObjectModels;
	}
	

}
