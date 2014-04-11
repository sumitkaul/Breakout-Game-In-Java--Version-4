package behavior;

import model.GameObjectModel;

public class MoveDownBehavior implements Behavior {

private GameObjectModel model;
	
	public MoveDownBehavior(GameObjectModel model){
		this.model = model;
	}

	@Override
	public void enforceBehavior() {
		model.setPosY(model.getPosY()+model.getSpeedY());

	}

}
