package action;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import events.GameEvent;

public class PlaySoundAction implements GameAction {
	
	private String soundFile;
	
	public PlaySoundAction(String soundFile){
		this.soundFile = soundFile;
	}

	@Override
	public void doAction(GameEvent event) {
		
		URL url = PlaySoundAction.class.getResource("/sounds/"+soundFile);
		AudioClip sound = Applet.newAudioClip(url);
		sound.play();

	}

}
