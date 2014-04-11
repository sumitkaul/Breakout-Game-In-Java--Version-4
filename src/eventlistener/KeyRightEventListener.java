package eventlistener;

import java.awt.event.KeyEvent;
import java.util.List;

import model.GameObjectModel;
import events.KeyRightEvent;

public class KeyRightEventListener extends EventListener {

	List<GameObjectModel> gameObjects;
	
	public KeyRightEventListener(List<GameObjectModel> gameObjects) {
		this.gameObjects = gameObjects;
	}
	
	@Override
	public void hasEventHappened() {
		// TODO Auto-generated method stub

	}

	/*@Override
	public void notifyAction(GameEvent event) {
		// TODO Auto-generated method stub

	}*/

	@Override
	public void eventHappened(Object obj) {
		int keyId = (int)obj;
		if (keyId == KeyEvent.VK_RIGHT) {
			KeyRightEvent event = new KeyRightEvent(gameObjects);
			notifyAction(event);
		}


	}

}
