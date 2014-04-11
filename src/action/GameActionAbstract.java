package action;

import java.util.ArrayList;
import java.util.List;

import behavior.Behavior;
import behavior.MoveDownBehavior;

import model.GameObjectModel;
import events.GameEvent;

public abstract class GameActionAbstract implements GameAction {

	@Override
	public void doAction(GameEvent event) {
		// TODO Auto-generated method stub

	}
	
	/*public void callBehavior(Class className, GameObjectModel gameObject){
		List<Behavior> behaviorList = gameObject.getBehaviorList();
		className.getT
		for (Behavior b:behaviorList){
			if(b instanceof className.){
				
			}
		}
	}*/

}
