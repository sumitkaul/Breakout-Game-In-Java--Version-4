package events;

import java.util.List;

import model.GameObjectModel;

public interface GameEvent {
	
	public List<GameObjectModel> getGameObjects();

}
