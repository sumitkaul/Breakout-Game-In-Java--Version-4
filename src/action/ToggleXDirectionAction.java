package action;

import java.util.List;

import behavior.Behavior;
import behavior.BlowUpBehavior;
import behavior.ToggleXDirectionBehavior;

import model.GameObjectModel;
import events.GameEvent;

public class ToggleXDirectionAction implements GameAction {

	@Override
	public void doAction(GameEvent event) {
		List<GameObjectModel> gameObjects = event.getGameObjects();
		callBehavior(gameObjects.get(0));

	}
	
	public void callBehavior(GameObjectModel gameObject){
		List<Behavior> behaviorList = gameObject.getBehaviorList();
		for (Behavior b:behaviorList){
			if(b instanceof ToggleXDirectionBehavior){
				b.enforceBehavior();
			}
		}
	}

}
