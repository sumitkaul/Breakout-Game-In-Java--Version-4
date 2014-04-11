package events;

import java.util.List;

import model.GameObjectModel;

public class KeyLeftEvent implements GameEvent {
	
	private List<GameObjectModel> gameObjects;
	
	public KeyLeftEvent(List<GameObjectModel> gameObjects){
		this.gameObjects = gameObjects;
	}

	@Override
	public List<GameObjectModel> getGameObjects() {
		return this.gameObjects;
	}

}
