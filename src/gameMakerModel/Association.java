package gameMakerModel;

import gameMaker.gameMaker;

import java.util.List;

public class Association {
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(gameMaker.class);
	private String spriteName;
	private String eventName;
	private String collidingSpriteName;
	private List<String> actionName;
	private String soundPath;
	
	public Association(String spriteName,String eventName,List<String> actionName,String soundPath,String collidingSpriteName){
		this.spriteName=spriteName;
		this.eventName=eventName;
		this.actionName=actionName;
		this.soundPath=soundPath;
		this.collidingSpriteName=collidingSpriteName;
	}
	
	public String getSoundPath() {
		return soundPath;
	}

	public void setSoundPath(String soundPath) {
		this.soundPath = soundPath;
	}

	public Association(String spriteName,String eventName,List<String> actionName,String soundPath){
		this.spriteName=spriteName;
		this.eventName=eventName;
		this.actionName=actionName;
		this.soundPath=soundPath;
	}
	
	public String getCollidingSpriteName() {
		return collidingSpriteName;
	}
	public void setCollidingSpriteName(String collidingSpriteName) {
		this.collidingSpriteName = collidingSpriteName;
	}
	public String getSpriteName() {
		return spriteName;
	}
	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public List<String> getActionName() {
		return actionName;
	}
	public void setActionName(List<String> actionName) {
		LOG.debug( "ASssociation" );
		this.actionName = actionName;
		/*for (int i=0; i< actionName.size(); i++)
        {
                LOG.debug(actionName.get(i) );
        }*/
		
	}

}
