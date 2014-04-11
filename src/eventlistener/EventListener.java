package eventlistener;

import java.util.ArrayList;
import java.util.List;

import action.GameAction;
import events.GameEvent;

public abstract class EventListener {
	
	public List<GameAction> gameActionList = new ArrayList<GameAction>();
	
	public abstract void hasEventHappened();
	
	public void registerAction(GameAction action){
		this.gameActionList.add(action);
	}
	
	public void unregisterAction(GameAction action){
		this.gameActionList.remove(action);
	}
	
	public void registerAction(List<GameAction> gameActionList){
		this.gameActionList.addAll(gameActionList);
	}
	public void notifyAction(GameEvent event){
		for(GameAction gameAction: gameActionList ){
			gameAction.doAction(event);
		}
	}
	
	public abstract void eventHappened(Object obj);

}
