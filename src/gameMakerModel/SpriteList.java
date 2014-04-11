package gameMakerModel;

import gameMaker.gameMaker;

import java.util.ArrayList;
import java.util.List;

public class SpriteList {
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(gameMaker.class);
	private List<GameMakerSpriteModel> list;

    public SpriteList(){
        setList(new ArrayList<GameMakerSpriteModel>());
    }

    public void add(GameMakerSpriteModel sprite){
        getList().add(sprite);
        LOG.debug("add"+getList().get(0).getSpriteName());
    }

	public List<GameMakerSpriteModel> getList() {
		return list;
	}

	public void setList(List<GameMakerSpriteModel> list) {
		this.list = list;
	}
}


