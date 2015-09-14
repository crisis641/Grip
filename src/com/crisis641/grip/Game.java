package com.crisis641.grip;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.crisis641.grip.entity.mob.Player;
import com.crisis641.grip.graphics.Screen;
import com.crisis641.grip.input.Keyboard;
import com.crisis641.grip.level.Level;
import com.crisis641.grip.level.RandomLevel;
import com.crisis641.grip.level.SpawnLevel;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	public static String title = "Grip";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);

		frame = new JFrame();

		key = new Keyboard();
		//level = new RandomLevel(64, 64);
		level = new SpawnLevel("/textures/levels/spawnlevel.png");

		player = new Player(300,300,key);
		frame.addKeyListener(key);

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0.0;
		int frames = 0;
		int updates = 0;
		// requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + ", UPS: " + updates);
				title = "Grip " + "FPS: " + frames + ", UPS: " + updates;
				frame.setTitle(title);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	public void update() {
		// System.out.println("A");
		key.update();
		// System.out.println("B");
		player.update();
		// System.out.println("C");

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		// g.setColor(Color.WHITE);
		// g.setFont(new Font("Verdana",0,50));
		// g.drawString("X: " + x + " Y: " + y, 450, 400);
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);

		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
}
