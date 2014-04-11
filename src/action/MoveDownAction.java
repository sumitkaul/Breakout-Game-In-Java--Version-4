package action;

import java.util.List;

import behavior.Behavior;
import behavior.MoveDownBehavior;
import behavior.MoveLeftBehavior;

import model.GameObjectModel;
import events.GameEvent;

public class MoveDownAction implements GameAction {

	@Override
	public void doAction(GameEvent event) {
		List<GameObjectModel> gameObjects = event.getGameObjects();
		callBehavior(gameObjects.get(0));
	}
	
	public void callBehavior(GameObjectModel gameObject){
		List<Behavior> behaviorList = gameObject.getBehaviorList();
		for (Behavior b:behaviorList){
			if(b instanceof MoveDownBehavior){
				b.enforceBehavior();
			}
		}
	}


}
