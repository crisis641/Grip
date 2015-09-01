package com.crisis641.grip.level.tile;

import com.crisis641.grip.graphics.Screen;
import com.crisis641.grip.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x,  y,  this );
	}
}
