package controller;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import eventlistener.EventListener;
import events.CollisionEvent;
import events.GameEvent;

import model.GameObjectModel;

public class CollisionController {
	
	private List<GameObjectModel> gameObjects;
	private List<EventListener> collisionListeners;
	
	public CollisionController(List<GameObjectModel> gameObjects){
		this.gameObjects = gameObjects;
		collisionListeners = new ArrayList<>();
	}
	
	public void registerListener(EventListener eventListener){
		collisionListeners.add(eventListener);
	}
	
	public void unregisterListener(EventListener eventListener){
		collisionListeners.remove(eventListener);
	}
	
	
	public void checkCollision(){
		for(int i = 0; i < gameObjects.size(); i++ ){
			for (int j = 0; j < gameObjects.size(); j++){
				if(j == i){
					continue;
				}
				Rectangle gameObj1 = gameObjects.get(i).getBoundingBox();
				Rectangle gameObj2 = gameObjects.get(j).getBoundingBox();
				if(gameObj1.intersects(gameObj2)){
					List<GameObjectModel> collisionObjects = new ArrayList<GameObjectModel>();
					collisionObjects.add(gameObjects.get(i));
					collisionObjects.add(gameObjects.get(j));
					notifyListeners(collisionObjects);
				}
			}
		}
	}
	
	public void notifyListeners(List<GameObjectModel> collisionObjects){
		for(EventListener listener: collisionListeners){
			listener.eventHappened(collisionObjects);
		}
	}
	
	public void clearAllCollisionListeners(){
		this.collisionListeners.clear();
	}

}
