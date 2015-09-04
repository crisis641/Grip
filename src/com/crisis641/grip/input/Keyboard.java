package com.crisis641.grip.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right;
	
	public void update() {
		//System.out.println("in keyboard update");
		up =    keys[KeyEvent.VK_UP]    || keys[KeyEvent.VK_W];
		down =  keys[KeyEvent.VK_DOWN]  || keys[KeyEvent.VK_S];
		left =  keys[KeyEvent.VK_LEFT]  || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		//System.out.println("up: " + up + " down: " + down + " left: " + left + " right: " + right);
	
	}
	
	
	public void keyPressed(KeyEvent e) {
		//System.out.println("KeyPressed " + e.getKeyChar());
		keys[e.getKeyCode()] = true;
		
	}


	public void keyReleased(KeyEvent e) {
		//System.out.println("KeyReleased " + e.getKeyChar());
		
		keys[e.getKeyCode()] = false;
		
	}


	public void keyTyped(KeyEvent e) {
		//System.out.println("keyTyped " + e.getKeyChar());
		
	}

}
