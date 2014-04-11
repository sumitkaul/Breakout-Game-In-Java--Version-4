package behavior;

import model.GameObjectModel;

public class ToggleXDirectionBehavior implements Behavior {

private GameObjectModel model;
	
	public ToggleXDirectionBehavior(GameObjectModel model){
		this.model = model;
	}

	@Override
	public void enforceBehavior() {
		model.setSpeedX(model.getSpeedX() * -1);

	}

}
