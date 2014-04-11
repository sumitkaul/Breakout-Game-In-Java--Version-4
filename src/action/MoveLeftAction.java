package action;

import java.util.List;

import behavior.Behavior;
import behavior.BlowUpBehavior;
import behavior.MoveLeftBehavior;

import model.GameObjectModel;

import events.GameEvent;

public class MoveLeftAction implements GameAction {

	@Override
	public void doAction(GameEvent event) {
		List<GameObjectModel> gameObjects = event.getGameObjects();
		for(GameObjectModel gameObj: gameObjects){
			callBehavior(gameObj);
		}

	}
	
	public void callBehavior(GameObjectModel gameObject){
		List<Behavior> behaviorList = gameObject.getBehaviorList();
		for (Behavior b:behaviorList){
			if(b instanceof MoveLeftBehavior){
				b.enforceBehavior();
			}
		}
	}

}
