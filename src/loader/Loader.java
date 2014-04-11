package loader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.Timer;

import behavior.BlowUpBehavior;
import behavior.MoveLeftBehavior;
import behavior.MoveRightBehavior;
import behavior.ToggleXDirectionBehavior;
import behavior.ToggleYDirectionBehavior;

import model.BallModel;
import model.BrickModel;
import model.GameObjectModel;
import model.PaddleModel;
import model.SpriteModel;
import utility.ResizeHelper;
import view.BallView;
import view.BrickView;
import view.Drawable;
import view.GamePanel;
import view.PaddleView;
import view.Sprite;
import view.SpriteView;
import action.BlowUpAction;
import action.GameAction;
import action.MoveLeftAction;
import action.MoveRightAction;
import action.PlaySoundAction;
import action.ToggleYDirectionAction;
import controller.CollisionController;
import controller.GameController;
import controller.KeyListenerController;
import eventlistener.CollisionEventListener;
import eventlistener.KeyLeftEventListener;
import eventlistener.KeyRightEventListener;

public class Loader {

	private JFrame mainScreen;
	private GamePanel gamePanel;
	private GameController gameController;
	public Timer timer;
	private List<GameObjectModel> gameObjects;
	private List<Drawable> drawables;
	private KeyListenerController keyController;
	private CollisionController collisionController;
	private Map<String, GameObjectModel> gameObjModelMap;

	public Loader() {
		mainScreen = new JFrame();
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.setTitle("Game Maker");
		gameObjects = new ArrayList<>();
		gameObjModelMap = new HashMap<>();
		mainScreen.setSize(500, 500);
		gamePanel = new GamePanel(500, 500);
		ResizeHelper resizeHelper = new ResizeHelper(gamePanel);
		mainScreen.getContentPane().add(gamePanel);
		// initViews();
		initSprites();
		keyController = new KeyListenerController();
		collisionController = new CollisionController(gameObjects);
		gamePanel.addKeyListener(keyController);
		gameController = new GameController(gameObjects, gamePanel,
				keyController, collisionController);
		// initAssociation();
		initListeners();
		timer = new Timer(20, gameController);
		mainScreen.setVisible(true);
		gamePanel.repaint();
		gamePanel.requestFocusInWindow();
	}

	/*
	 * private void initAssociation() { List<GameObjectModel> baBrCol = new
	 * ArrayList<>(); baBrCol.add(ballModel); baBrCol.add(brickModel);
	 * CollisionEventListener collision = new CollisionEventListener(baBrCol);
	 * ToggleYDirectionAction toggleY = new ToggleYDirectionAction();
	 * PlaySoundAction soundAction = new PlaySoundAction("Brick.au");
	 * List<GameAction> gameActions = new ArrayList<>();
	 * gameActions.add(toggleY); gameActions.add(soundAction);
	 * collision.registerAction(gameActions);
	 * //gameController.addAssociation(collision, gameActions);
	 * collisionController.registerListener(collision); List<GameObjectModel>
	 * brBaCol = new ArrayList<>(); brBaCol.add(brickModel);
	 * brBaCol.add(ballModel); CollisionEventListener brickCollision = new
	 * CollisionEventListener(brBaCol); BlowUpAction blowUp = new
	 * BlowUpAction(); List<GameAction> brickGameActions = new ArrayList<>();
	 * brickGameActions.add(blowUp);
	 * brickCollision.registerAction(brickGameActions);
	 * //gameController.addAssociation(brickCollision, brickGameActions);
	 * collisionController.registerListener(brickCollision);
	 * List<GameObjectModel> keyModels = new ArrayList<>();
	 * keyModels.add(paddleModel); KeyLeftEventListener keyLeftListener = new
	 * KeyLeftEventListener(keyModels); MoveLeftAction moveLeft = new
	 * MoveLeftAction(); List<GameAction> leftAction = new ArrayList<>();
	 * leftAction.add(moveLeft); keyLeftListener.registerAction(moveLeft);
	 * keyController.registerListener(keyLeftListener); KeyRightEventListener
	 * keyRightListener = new KeyRightEventListener(keyModels); MoveRightAction
	 * moveRight = new MoveRightAction(); List<GameAction> RightAction = new
	 * ArrayList<>(); RightAction.add(moveRight);
	 * keyRightListener.registerAction(moveRight);
	 * keyController.registerListener(keyRightListener); List<GameObjectModel>
	 * paddleCollisionObjects = new ArrayList<>();
	 * paddleCollisionObjects.add(ballModel);
	 * paddleCollisionObjects.add(paddleModel); CollisionEventListener
	 * paddleCollision = new CollisionEventListener(paddleCollisionObjects);
	 * ToggleYDirectionAction toggleYWithPadddle = new ToggleYDirectionAction();
	 * PlaySoundAction paddleSoundAction = new PlaySoundAction("bounce.au");
	 * List<GameAction> paddleCollisionActionList = new ArrayList<>();
	 * paddleCollisionActionList.add(toggleYWithPadddle);
	 * paddleCollisionActionList.add(paddleSoundAction);
	 * paddleCollision.registerAction(paddleCollisionActionList);
	 * collisionController.registerListener(paddleCollision);
	 * 
	 * 
	 * }
	 */

	private void initSprites() {
		SpriteModel ballModelS = new SpriteModel(100, 100, 5, 5, 30, 30, true,
				true, "Ball");
		ToggleXDirectionBehavior toggleXBehavior = new ToggleXDirectionBehavior(
				ballModelS);
		ballModelS.addBehavior(toggleXBehavior);
		ToggleYDirectionBehavior toggleYDirectionBehavior = new ToggleYDirectionBehavior(
				ballModelS);
		ballModelS.addBehavior(toggleYDirectionBehavior);
		gameObjects.add(ballModelS);
		gameObjModelMap.put("Ball", ballModelS);
		// SpriteView ballViewS = new SpriteView(ballModelS);
		Sprite ballViewS = new Sprite("ball.jpg", ballModelS);
		gamePanel.registerDrawable(ballViewS);

		SpriteModel paddleModelS = new SpriteModel(10, 400, 5, 0, 70, 10,
				false, false, "Paddle");
		MoveLeftBehavior moveLeftBehavior = new MoveLeftBehavior(paddleModelS);
		MoveRightBehavior moveRightBehavior = new MoveRightBehavior(
				paddleModelS);
		paddleModelS.addBehavior(moveLeftBehavior);
		paddleModelS.addBehavior(moveRightBehavior);
		gameObjects.add(paddleModelS);
		gameObjModelMap.put("Paddle", paddleModelS);
		// SpriteView paddleViewS = new SpriteView(paddleModelS);
		Sprite paddleViewS = new Sprite("paddle.png", paddleModelS);
		gamePanel.registerDrawable(paddleViewS);

		SpriteModel brickModelS = new SpriteModel(50, 50, 0, 0, 60, 20, false,
				false, "Brick");
		BlowUpBehavior blowUpBehavior = new BlowUpBehavior(brickModelS);
		brickModelS.addBehavior(blowUpBehavior);
		brickModelS.addBehavior(blowUpBehavior);
		gameObjects.add(brickModelS);
		gameObjModelMap.put("Brick", brickModelS);
		// SpriteView brickViewS = new SpriteView(brickModelS);
		Sprite brickViewS = new Sprite("brick.jpg", brickModelS);
		gamePanel.registerDrawable(brickViewS);

	}

	private void initListeners() {
		List<GameObjectModel> baBrCol = new ArrayList<>();
		baBrCol.add(gameObjModelMap.get("Ball"));
		baBrCol.add(gameObjModelMap.get("Brick"));
		CollisionEventListener collision = new CollisionEventListener(baBrCol);
		ToggleYDirectionAction toggleY = new ToggleYDirectionAction();
		PlaySoundAction soundAction = new PlaySoundAction("Brick.au");
		List<GameAction> gameActions = new ArrayList<>();
		gameActions.add(toggleY);
		gameActions.add(soundAction);
		collision.registerAction(gameActions);
		// gameController.addAssociation(collision, gameActions);
		collisionController.registerListener(collision);
		List<GameObjectModel> brBaCol = new ArrayList<>();
		brBaCol.add(gameObjModelMap.get("Brick"));
		brBaCol.add(gameObjModelMap.get("Ball"));
		CollisionEventListener brickCollision = new CollisionEventListener(
				brBaCol);
		BlowUpAction blowUp = new BlowUpAction();
		List<GameAction> brickGameActions = new ArrayList<>();
		brickGameActions.add(blowUp);
		brickCollision.registerAction(brickGameActions);
		// gameController.addAssociation(brickCollision, brickGameActions);
		collisionController.registerListener(brickCollision);
		List<GameObjectModel> keyModels = new ArrayList<>();
		keyModels.add(gameObjModelMap.get("Paddle"));
		KeyLeftEventListener keyLeftListener = new KeyLeftEventListener(
				keyModels);
		MoveLeftAction moveLeft = new MoveLeftAction();
		List<GameAction> leftAction = new ArrayList<>();
		leftAction.add(moveLeft);
		keyLeftListener.registerAction(moveLeft);
		keyController.registerListener(keyLeftListener);
		KeyRightEventListener keyRightListener = new KeyRightEventListener(
				keyModels);
		MoveRightAction moveRight = new MoveRightAction();
		List<GameAction> RightAction = new ArrayList<>();
		RightAction.add(moveRight);
		keyRightListener.registerAction(moveRight);
		keyController.registerListener(keyRightListener);
		List<GameObjectModel> paddleCollisionObjects = new ArrayList<>();
		paddleCollisionObjects.add(gameObjModelMap.get("Ball"));
		paddleCollisionObjects.add(gameObjModelMap.get("Paddle"));
		CollisionEventListener paddleCollision = new CollisionEventListener(
				paddleCollisionObjects);
		ToggleYDirectionAction toggleYWithPadddle = new ToggleYDirectionAction();
		PlaySoundAction paddleSoundAction = new PlaySoundAction("bounce.au");
		List<GameAction> paddleCollisionActionList = new ArrayList<>();
		paddleCollisionActionList.add(toggleYWithPadddle);
		paddleCollisionActionList.add(paddleSoundAction);
		paddleCollision.registerAction(paddleCollisionActionList);
		collisionController.registerListener(paddleCollision);
	}

	/*
	 * private void initViews() { ballModel = new BallModel(100, 100, 5, 5, 30,
	 * 30, 10); ToggleXDirectionBehavior toggleXBehavior = new
	 * ToggleXDirectionBehavior(ballModel);
	 * ballModel.addBehavior(toggleXBehavior); ToggleYDirectionBehavior
	 * toggleYDirectionBehavior = new ToggleYDirectionBehavior(ballModel);
	 * ballModel.addBehavior(toggleYDirectionBehavior); BallView ballView = new
	 * BallView(ballModel); //gameController.addGameObject(ballModel);
	 * gamePanel.registerDrawable(ballView); gameObjects.add(ballModel);
	 * paddleModel = new PaddleModel(10, 400, 50, 10, 5, 5); MoveLeftBehavior
	 * moveLeftBehavior = new MoveLeftBehavior(paddleModel); MoveRightBehavior
	 * moveRightBehavior = new MoveRightBehavior(paddleModel);
	 * paddleModel.addBehavior(moveLeftBehavior);
	 * paddleModel.addBehavior(moveRightBehavior); PaddleView paddleView = new
	 * PaddleView(paddleModel); //gameController.addGameObject(paddleModel);
	 * gamePanel.registerDrawable(paddleView); gameObjects.add(paddleModel);
	 * brickModel = new BrickModel(50, 50, 40, 20, 0, 0); BlowUpBehavior
	 * blowUpBehavior = new BlowUpBehavior(brickModel);
	 * brickModel.addBehavior(blowUpBehavior); BrickView brickView = new
	 * BrickView(brickModel); //gameController.addGameObject(brickModel);
	 * gamePanel.registerDrawable(brickView); gameObjects.add(brickModel);
	 * 
	 * 
	 * }
	 */

	public static void main(String[] args) {
		Loader loader = new Loader();
		loader.timer.start();

	}

}
