package events;

import java.util.List;

import model.GameObjectModel;

public class KeyRightEvent implements GameEvent {

private List<GameObjectModel> gameObjects;
	
	public KeyRightEvent(List<GameObjectModel> gameObjects){
		this.gameObjects = gameObjects;
	}

	@Override
	public List<GameObjectModel> getGameObjects() {
		return this.gameObjects;
	}

}
