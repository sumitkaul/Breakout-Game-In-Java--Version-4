package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import eventlistener.EventListener;

public class KeyListenerController implements KeyListener {
	
	public int keyId;
	private List<EventListener> keyEventListeners;
	
	public KeyListenerController(){
		keyId = 0;
		keyEventListeners = new ArrayList<>();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		for(EventListener listener:keyEventListeners){
			listener.eventHappened(e.getKeyCode());
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void registerListener(EventListener eventListener){
		keyEventListeners.add(eventListener);
	}
	
	public void unregisterListener(EventListener eventListener){
		keyEventListeners.remove(eventListener);
	}
	
	public void clear(){
		keyId = 0;
	}
	
	public void removeAllKeyListeners(){
		this.keyEventListeners.clear();
	}

}
