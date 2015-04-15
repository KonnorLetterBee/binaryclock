
package com.perrigogames.binaryclock.renderer.scene2d;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.perrigogames.binaryclock.config.ClockRendererConfig;

public class ClockWidgetPart {

	private final int[][] cells;
	private ClockRendererConfig config;
	private int x, y;
	
	public ClockWidgetPart (int x, int y, int[][] cells, ClockRendererConfig config) {
		setPosition(x, y);
		this.cells = cells;
		this.config = config;
	}
	
	public int getCellHeight () {
		return cells[0].length;
	}

	public int getCellWidth () {
		return cells.length;
	}

	public int getHeight () {
		int cH = getCellHeight();
		return (cH * config.cellSize) + ((cH - 1) * config.cellSpacing);
	}

	public int getWidth () {
		int cW = getCellWidth();
		return (cW * config.cellSize) + ((cW - 1) * config.cellSpacing);
	}
	
	public void render (ShapeRenderer shapes, int color) {
//		shapes.setColor(Color.WHITE);
//		shapes.begin(ShapeType.Line);
//		for (int x = 0; x < cells.length; x++)
//			for (int y = 0; y < cells[x].length; y++)
//				if (cells[x][y] == 1)
//					drawBox(x, y, config.cellSize);
//		shapes.end();
//		shapes.begin(ShapeType.Filled);
//		for (int x = 0; x < cells.length; x++)
//			for (int y = 0; y < cells[x].length; y++)
//				if (cells[x][y] == 2)
//					drawBox(x, y, size);
//		shapes.end();
	}

	public void setConfig (ClockRendererConfig config) {
		this.config = config;
	}
	
	public void setPosition (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private void drawBox (int x, int y, float size) {
//		float xSc = spacing + ((size + spacing) * x);
//		float ySc = screenHeight - ((size + spacing) * (y + 1));
//		shapes.rect(xSc, ySc, size, size);
	}
}
