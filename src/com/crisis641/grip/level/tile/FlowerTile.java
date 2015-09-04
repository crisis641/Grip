package com.crisis641.grip.level.tile;

import com.crisis641.grip.graphics.Screen;
import com.crisis641.grip.graphics.Sprite;

public class FlowerTile extends Tile {

	public FlowerTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4,  y << 4,  this );
	}

}
