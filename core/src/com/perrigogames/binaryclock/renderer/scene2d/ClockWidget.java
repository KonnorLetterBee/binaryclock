
package com.perrigogames.binaryclock.renderer.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ClockWidget extends Table {
	
	private ClockWidgetPart seconds, minutes, hours;
	
	public ClockWidget () {
		setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
}
