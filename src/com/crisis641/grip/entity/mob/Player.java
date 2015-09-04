package com.crisis641.grip.entity.mob;

import com.crisis641.grip.graphics.Screen;
import com.crisis641.grip.graphics.Sprite;
import com.crisis641.grip.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward_0;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward_0;
	}

	public void update() {
		if (anim < 40)
			anim++;
		else
			anim = 0;

		int xa = 0, ya = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_forward_0;
			if (walking) {
				if (anim < 10) {
					sprite = Sprite.player_forward_1;
				} else if (anim > 10 && anim <= 20) {
					sprite = Sprite.player_forward_0;
				} else if (anim > 20 && anim <= 30) {
					sprite = Sprite.player_forward_2;
				} else {
					sprite = Sprite.player_forward_0;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_side_0;
			if (walking) {
				if (anim < 10) {
					sprite = Sprite.player_side_1;
				} else if (anim > 10 && anim <= 20) {
					sprite = Sprite.player_side_0;
				} else if (anim > 20 && anim <= 30) {
					sprite = Sprite.player_side_2;
				} else {
					sprite = Sprite.player_side_0;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_backward_0;
			if (walking) {
				if (anim < 10) {
					sprite = Sprite.player_backward_1;
				} else if (anim > 10 && anim <= 20) {
					sprite = Sprite.player_backward_0;
				} else if (anim > 20 && anim <= 30) {
					sprite = Sprite.player_backward_2;
				} else {
					sprite = Sprite.player_backward_0;
				}
			}
		}
		if (dir == 3) {
			flip = 1;
			sprite = Sprite.player_side_0;
			if (walking) {
				if (anim < 10) {
					sprite = Sprite.player_side_1;
				} else if (anim > 10 && anim <= 20) {
					sprite = Sprite.player_side_0;
				} else if (anim > 10 && anim <= 30) {
					sprite = Sprite.player_side_2;
				} else {
					sprite = Sprite.player_side_0;
				}
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);

	}

}
