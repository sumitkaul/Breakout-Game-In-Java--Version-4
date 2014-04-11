package behavior;

import model.GameObjectModel;

public class ToggleYDirectionBehavior implements Behavior {

	private GameObjectModel model;

	public ToggleYDirectionBehavior(GameObjectModel model){
		this.model = model;
	}


	@Override
	public void enforceBehavior() {
		model.setSpeedY(model.getSpeedY() * -1);

	}

}

