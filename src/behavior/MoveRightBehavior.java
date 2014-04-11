package behavior;

import model.GameObjectModel;

public class MoveRightBehavior implements Behavior {

private GameObjectModel model;
	
	public MoveRightBehavior(GameObjectModel model){
		this.model = model;
	}

	@Override
	public void enforceBehavior() {
		model.setPosX(model.getPosX()+model.getSpeedX());

	}

}
