package eventlistener;

import java.awt.Rectangle;
import java.util.List;

import model.GameObjectModel;
import action.GameAction;
import events.CollisionEvent;
import events.GameEvent;

public class CollisionEventListener extends EventListener {
	
	private List<GameObjectModel> collisionObjects;
	
	public CollisionEventListener(List<GameObjectModel> collisionObjects){
		this.collisionObjects = collisionObjects;
		
	}
	

	@Override
	public void hasEventHappened() {
		if (collisionObjects != null && collisionObjects.size() > 1) {
			Rectangle ballBB = collisionObjects.get(0).getBoundingBox();
			Rectangle brickBB = collisionObjects.get(1).getBoundingBox();
			if(ballBB.intersects(brickBB)){
				collisionObjects.get(0).collisionOccured(collisionObjects.get(1));
				CollisionEvent event = new CollisionEvent(collisionObjects);
				notifyAction(event);
			}
		}

	}

	@Override
	public void notifyAction(GameEvent event) {
		for(GameAction gameAction: gameActionList ){
			gameAction.doAction(event);
		}

	}


	@Override
	public void eventHappened(Object obj) {
		GameObjectModel collisionObjA = collisionObjects.get(0);
		GameObjectModel collisionObjB = collisionObjects.get(1);
		List<GameObjectModel> receivedCollisionObjects = (List<GameObjectModel>)obj;
		GameObjectModel receivedCollisionObjA = receivedCollisionObjects.get(0);
		GameObjectModel receivedCollisionObjB = receivedCollisionObjects.get(1);
		if(collisionObjA.equals(receivedCollisionObjA)&&collisionObjB.equals(receivedCollisionObjB)){
			collisionObjects.get(0).collisionOccured(collisionObjects.get(1));
			CollisionEvent event = new CollisionEvent(collisionObjects);
			notifyAction(event);
		}
		
	}

}
