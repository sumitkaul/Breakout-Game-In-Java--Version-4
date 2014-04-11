package eventlistener;

import java.awt.event.KeyEvent;
import java.util.List;

import model.GameObjectModel;
import events.KeyLeftEvent;

public class KeyLeftEventListener extends EventListener {

	List<GameObjectModel> gameObjects;
	
	public KeyLeftEventListener(List<GameObjectModel> gameObjects) {
		this.gameObjects = gameObjects;
	}

	@Override
	public void hasEventHappened() {

	}

	/*@Override
	public void notifyAction(GameEvent event) {
		for(GameAction gameAction: gameActionList ){
			gameAction.doAction(event);
		}

	}*/

	@Override
	public void eventHappened(Object obj) {
		int keyId = (int)obj;
		if (keyId == KeyEvent.VK_LEFT) {
			KeyLeftEvent event = new KeyLeftEvent(gameObjects);
			notifyAction(event);
		}

	}

}
