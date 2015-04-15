
package com.perrigogames.binaryclock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.perrigogames.binaryclock.renderer.shape.ClockRenderer;

public class BinaryClockApp extends ApplicationAdapter {
	
	private static final long UPDATE_CYCLE_MS = 50;

	private ClockRenderer clock;
	private ClockState state;

	@Override
	public void create () {
		clock = new ClockRenderer();
		state = new ClockState();
	}
	
	@Override
	public void render () {
		long millis = ms();
		clock.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		state.setOrientation(Gdx.graphics.getHeight() > Gdx.graphics.getWidth());
		Gdx.gl.glClearColor(0.3f, 0, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		state.update();
		clock.render(state.getCells());
		long rTime = ms() - millis;
		if (rTime < UPDATE_CYCLE_MS)
			try {
				Thread.sleep(UPDATE_CYCLE_MS - rTime);
			} catch (InterruptedException e) {}
	}
	
	@Override
	public void resize (int width, int height) {
		clock.setSize(width, height);
	}
	
	private long ms () {
		return System.currentTimeMillis();
	}
}
