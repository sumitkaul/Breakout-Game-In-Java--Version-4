package behavior;

import model.GameObjectModel;

public class MoveLeftBehavior implements Behavior {

private GameObjectModel model;
	
	public MoveLeftBehavior(GameObjectModel model){
		this.model = model;
	}

	@Override
	public void enforceBehavior() {
		model.setPosX(model.getPosX()-model.getSpeedX());

	}

}
