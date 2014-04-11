package behavior;

import model.GameObjectModel;

public class BlowUpBehavior implements Behavior {
	
	private GameObjectModel model;

	public BlowUpBehavior(GameObjectModel model){
		this.model = model;
	}


	@Override
	public void enforceBehavior() {
		model.setIsAlive(false);
		model.setWidth(0);
		model.setHeight(0);
	}

}
