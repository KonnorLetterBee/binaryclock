
package com.perrigogames.binaryclock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ClockRenderer {
	
	private final OrthographicCamera camera;
	private int screenWidth, screenHeight;
	private final ShapeRenderer shapes;
	private int spacing;
	
	public ClockRenderer () {
		camera = new OrthographicCamera();
		shapes = new ShapeRenderer();
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void render (int[][] cells) {
		int spacesX = cells.length, spacesY = cells[0].length;
		float fitWidth = ((float)screenWidth - ((spacesX + 1) * spacing)) / spacesX;
		float fitHeight = ((float)screenHeight - ((spacesY + 1) * spacing)) / spacesY;
		float size = Math.min(fitHeight, fitWidth);
		shapes.setColor(Color.WHITE);
		shapes.begin(ShapeType.Line);
		for (int x = 0; x < cells.length; x++)
			for (int y = 0; y < cells[x].length; y++)
			if (cells[x][y] == 1)
				drawBox(x, y, size);
		shapes.end();
		shapes.begin(ShapeType.Filled);
		for (int x = 0; x < cells.length; x++)
			for (int y = 0; y < cells[x].length; y++)
			if (cells[x][y] == 2)
				drawBox(x, y, size);
		shapes.end();
	}
	
	public void setSize (int width, int height) {
		if (width != screenWidth || height != screenHeight) {
			screenWidth = width;
			screenHeight = height;
			camera.setToOrtho(false);
			shapes.setProjectionMatrix(camera.combined);
		}
	}
	
	public void setSpacing (int spacing) {
		this.spacing = spacing;
	}
	
	private void drawBox (int x, int y, float size) {
		float xSc = spacing + ((size + spacing) * x);
		float ySc = screenHeight - ((size + spacing) * (y + 1));
		shapes.rect(xSc, ySc, size, size);
	}
}
