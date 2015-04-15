
package com.perrigogames.binaryclock.renderer.shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ClockRenderer {
	
	private final OrthographicCamera camera;
	private int screenWidth, screenHeight;
	private ClockRendererPart seconds, minutes, hours;
	private final ShapeRenderer shapes;
	
	public ClockRenderer () {
		camera = new OrthographicCamera();
		shapes = new ShapeRenderer();
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	/** @param cells the groups to check the height of (groups are laid out
	 *           horizontally and this will return the highest height of all of
	 *           the groups' columns)
	 * @return the height of the clock, in cells */
	public int heightCells (int[][][] cells) {
		int height = 0;
		for (int[][] g : cells)
			for (int[] x : g)
				height = Math.max(height, x.length);
		return height;
	}
	
	public void render (int[][][] cells) {
		
	}
	
	public void setSize (int width, int height) {
		if (width != screenWidth || height != screenHeight) {
			screenWidth = width;
			screenHeight = height;
			camera.setToOrtho(false);
			shapes.setProjectionMatrix(camera.combined);
		}
	}
	
	/** @param cells the groups to check the width of (groups are laid out
	 *           horizontally and this will return the combined length of all of
	 *           the groups' rows)
	 * @return the width of the clock, in cells */
	public int widthCells (int[][][] cells) {
		int width = 0;
		for (int[][] g : cells)
			width += g.length;
		return width;
	}
}
