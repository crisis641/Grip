package com.crisis641.grip.entity;

import java.util.Random;

import com.crisis641.grip.graphics.Screen;
import com.crisis641.grip.level.Level;

public abstract class Entity {

	public int x,y; //where the entity is located on the map
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
	public void remove(){
		//remove from level
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
}
