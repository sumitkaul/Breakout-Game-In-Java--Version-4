package behavior;

import model.GameObjectModel;

public class MoveUpBehavior implements Behavior {
	
	private GameObjectModel model;
	
	public MoveUpBehavior(GameObjectModel model){
		this.model = model;
	}

	@Override
	public void enforceBehavior() {
		model.setPosY(model.getPosY()-model.getSpeedY());

	}

}
