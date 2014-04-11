package facade;

import eventlistener.CollisionEventListener;
import eventlistener.KeyLeftEventListener;
import eventlistener.KeyRightEventListener;
import gameMakerModel.Association;
import gameMakerModel.GameMakerSpriteModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import action.BlowUpAction;
import action.GameAction;
import action.MoveDownAction;
import action.MoveLeftAction;
import action.MoveRightAction;
import action.MoveUpAction;
import action.PlaySoundAction;
import action.ToggleXDirectionAction;
import action.ToggleYDirectionAction;
import behavior.Behavior;
import behavior.BlowUpBehavior;
import behavior.MoveDownBehavior;
import behavior.MoveLeftBehavior;
import behavior.MoveRightBehavior;
import behavior.MoveUpBehavior;
import behavior.ToggleXDirectionBehavior;
import behavior.ToggleYDirectionBehavior;

import model.GameObjectModel;
import model.SpriteModel;
import controller.CollisionController;
import controller.GameController;
import controller.KeyListenerController;
import utility.Constants;
import view.GamePanel;
import view.Sprite;

public class Facade {

	private GamePanel gamePanel;
	private KeyListenerController keyListenerController;
	private CollisionController collisionController;
	private GameController gameController;
	private List<GameObjectModel> gameObjects;
	private Map<String, GameObjectModel> gameObjModelMap;
	private Timer timer;

	public Facade(GamePanel gamePanel) {

		this.gamePanel = gamePanel;
		this.gameObjects = new ArrayList<>();
		this.gameObjModelMap = new HashMap<>();
		this.keyListenerController = new KeyListenerController();
		this.collisionController = new CollisionController(gameObjects);
		this.gamePanel.addKeyListener(keyListenerController);
		this.gameController = new GameController(gameObjects, gamePanel,
				keyListenerController, collisionController);
		this.timer = new Timer(20, gameController);
		this.gamePanel.repaint();
		this.gamePanel.requestFocusInWindow();

	}

	public void startGame() {
		this.timer.start();
		this.gamePanel.repaint();
		this.gamePanel.requestFocusInWindow();
	}

	public void stopGame() {
		this.timer.stop();
		this.gamePanel.repaint();
		this.gamePanel.requestFocusInWindow();

	}

	public void createSprite(GameMakerSpriteModel gmSprite) {
		SpriteModel spriteModel = new SpriteModel(gmSprite.getPosX(),
				gmSprite.getPosY(), gmSprite.getSpeedX(), gmSprite.getSpeedY(),
				gmSprite.getWidth(), gmSprite.getHeight(),
				gmSprite.getUpdateX(), gmSprite.getUpdateY(),
				gmSprite.getSpriteName());
		List<Behavior> behaviorList = getBehaviorList(gmSprite, spriteModel);
		spriteModel.addBehavior(behaviorList);
		gameObjects.add(spriteModel);
		gameObjModelMap.put(gmSprite.getSpriteName(), spriteModel);
		Sprite spriteView = new Sprite(gmSprite.getSpriteImage(), spriteModel);
		gamePanel.registerDrawable(spriteView);
		this.gamePanel.repaint();
		this.gamePanel.requestFocusInWindow();
	}

	public void createPair(Association association) {
		String eventName = association.getEventName();
		switch (eventName) {
		case Constants.COLLIDE:
			createCollisionAssociation(association);
			break;
		case Constants.KEY_PRESS_UP:
			createKeyUpEventAssociation(association);
			break;
		case Constants.KEY_PRESS_DOWN:
			createKeyDownEventAssociation(association);
			break;
		case Constants.KEY_PRESS_LEFT:
			createKeyLeftEventAssociation(association);
			break;
		case Constants.KEY_PRESS_RIGHT:
			createKeyRightEventAssociation(association);
			break;
		}
		this.gamePanel.repaint();
		this.gamePanel.requestFocusInWindow();
	}
	
	public void loadSprites(List<GameMakerSpriteModel> sprites){
		gameObjects.clear();
		gameObjModelMap.clear();
		this.gamePanel.removeAllDrawables();
		for(GameMakerSpriteModel gmSprite:sprites){
			createSprite(gmSprite);
		}
	}
	
	public void loadPairs(List<Association> associations){
		this.collisionController.clearAllCollisionListeners();
		this.keyListenerController.removeAllKeyListeners();
		for(Association a : associations){
			createPair(a);
		}
		
	}

	private void createKeyRightEventAssociation(Association association) {
		List<GameObjectModel> keyModels = new ArrayList<>();
		keyModels.add(gameObjModelMap.get(association.getSpriteName()));
		KeyRightEventListener keyRightListener = new KeyRightEventListener(
				keyModels);
		List<String> actionNames = association.getActionName();
		List<GameAction> actions = getActionList(actionNames, association);
		keyRightListener.registerAction(actions);
		keyListenerController.registerListener(keyRightListener);

	}

	private void createKeyLeftEventAssociation(Association association) {
		List<GameObjectModel> keyModels = new ArrayList<>();
		keyModels.add(gameObjModelMap.get(association.getSpriteName()));
		KeyLeftEventListener keyLeftListener = new KeyLeftEventListener(
				keyModels);
		List<String> actionNames = association.getActionName();
		List<GameAction> actions = getActionList(actionNames, association);
		keyLeftListener.registerAction(actions);
		keyListenerController.registerListener(keyLeftListener);

	}

	private void createKeyDownEventAssociation(Association association) {
		/*
		 * List<GameObjectModel> keyModels = new ArrayList<>();
		 * keyModels.add(gameObjModelMap.get(association.getSpriteName()));
		 * KeyDownEventListener keyDownListener = new
		 * KeyDownEventListener(keyModels); List<String> actionNames =
		 * association.getActionName(); List<GameAction> actions =
		 * getActionList(actionNames, association);
		 * keyDownListener.registerAction(actions);
		 */

	}

	private void createKeyUpEventAssociation(Association association) {
		// TODO Auto-generated method stub

	}

	private void createCollisionAssociation(Association association) {
		List<GameObjectModel> collisionObjects = new ArrayList<>();
		collisionObjects.add(gameObjModelMap.get(association.getSpriteName()));
		collisionObjects.add(gameObjModelMap.get(association
				.getCollidingSpriteName()));
		CollisionEventListener collision = new CollisionEventListener(
				collisionObjects);
		List<String> actionNames = association.getActionName();
		List<GameAction> actions = getActionList(actionNames, association);
		collision.registerAction(actions);
		collisionController.registerListener(collision);

	}

	private List<Behavior> getBehaviorList(GameMakerSpriteModel gmSprite,
			SpriteModel sprite) {
		List<Behavior> behaviorList = new ArrayList<>();
		List<String> behaviors = gmSprite.getBehaviourList();
		Behavior behavior;
		if(behaviors != null){
			for (String b : behaviors) {
				switch (b) {
				case Constants.DISAPPEAR:
					behavior = new BlowUpBehavior(sprite);
					behaviorList.add(behavior);
					break;
				case Constants.MOVE_DOWN:
					behavior = new MoveDownBehavior(sprite);
					behaviorList.add(behavior);
					break;
				case Constants.MOVE_LEFT:
					behavior = new MoveLeftBehavior(sprite);
					behaviorList.add(behavior);
					break;
				case Constants.MOVE_RIGHT:
					behavior = new MoveRightBehavior(sprite);
					behaviorList.add(behavior);
					break;
				case Constants.MOVE_UP:
					behavior = new MoveUpBehavior(sprite);
					behaviorList.add(behavior);
					break;
				case Constants.TOGGLE_X:
					behavior = new ToggleXDirectionBehavior(sprite);
					behaviorList.add(behavior);
					break;
				case Constants.TOGGLE_Y:
					behavior = new ToggleYDirectionBehavior(sprite);
					behaviorList.add(behavior);
					break;
				default:
					break;

				}
			}
		}
		
		return behaviorList;
	}

	private List<GameAction> getActionList(List<String> actionNames,
			Association a) {
		List<GameAction> actionList = new ArrayList<>();
		GameAction action;
		for (String name : actionNames) {
			switch (name) {
			case Constants.DISAPPEAR:
				action = new BlowUpAction();
				actionList.add(action);
				break;
			case Constants.MOVE_DOWN:
				action = new MoveDownAction();
				actionList.add(action);
				break;
			case Constants.MOVE_UP:
				action = new MoveUpAction();
				actionList.add(action);
				break;
			case Constants.MOVE_LEFT:
				action = new MoveLeftAction();
				actionList.add(action);
				break;
			case Constants.MOVE_RIGHT:
				action = new MoveRightAction();
				actionList.add(action);
				break;
			case Constants.SOUND:
				action = new PlaySoundAction(a.getSoundPath());
				actionList.add(action);
				break;
			case Constants.TOGGLE_X:
				action = new ToggleXDirectionAction();
				actionList.add(action);
				break;
			case Constants.TOGGLE_Y:
				action = new ToggleYDirectionAction();
				actionList.add(action);
				break;
			default:
				break;
			}
		}
		return actionList;
	}

}
