package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import view.GamePanel;
import action.GameAction;
import eventlistener.EventListener;
import model.GameObjectModel;


public class GameController implements ActionListener {

	
	private GamePanel gamePanel;
	private KeyListenerController keyListenerController;
	private CollisionController collisionController;

	private List<EventListener> eventHappenedList;

	private List<GameObjectModel> gameObjects;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//checkAssociations();
		collisionController.checkCollision();
		for(GameObjectModel gameObject: this.gameObjects){
			gameObject.update(gamePanel.getSize());
		}
		gamePanel.repaint();
		gamePanel.requestFocusInWindow();
		keyListenerController.clear();

	}

	public GameController(List<GameObjectModel> gameObjects, GamePanel gamePanel, KeyListenerController keyListener, 
			CollisionController collisionController){
		/*this.ballModel = ballModel;
		this.brickModel = brickModel;
		this.paddleModel = paddleModel;*/
		this.eventHappenedList = new ArrayList<EventListener>();
		this.gameObjects = gameObjects;
		this.gamePanel = gamePanel;
		this.keyListenerController = keyListener;
		this.collisionController = collisionController;
		//this.gamePanel.addKeyListener(keyListener);
	}

	public void addAssociation(EventListener eventHappened, List<GameAction> gameActionList){
		eventHappened.registerAction(gameActionList);
		this.eventHappenedList.add(eventHappened);
	}

	public void removeAssociation(EventListener eventHappened){
		this.removeAssociation(eventHappened);
	}

	public void checkAssociations(){
		for(EventListener eventHappened: eventHappenedList){
			eventHappened.hasEventHappened();
		}
	}
	
	public void addGameObject(GameObjectModel object){
		this.gameObjects.add(object);
	}
	
	public void removeGameObject(GameObjectModel object){
		this.gameObjects.remove(object);
	}

}
