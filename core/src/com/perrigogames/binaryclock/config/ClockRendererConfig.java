
package com.perrigogames.binaryclock.config;

import com.badlogic.gdx.graphics.Color;
import com.perrigogames.binaryclock.ClockState.HourFormat;
import com.perrigogames.binaryclock.ClockState.SectionStyle;

/** Config class to determine the look of the clock renderer
 * @author Corey */
public class ClockRendererConfig {

	/** @return a default {@link ClockRendererConfig} */
	public static ClockRendererConfig defaultConfig () {
		ClockRendererConfig config = new ClockRendererConfig();
		config.cellSpacing = 2;
		config.edgeSpacing = 4;
		config.sectionSpacing = 12;
		config.colors = new Color[] { Color.WHITE };
		config.hourStylePortrait = config.minuteStylePortrait = config.secondStylePortrait = SectionStyle.DIGIT;
		config.hourStyleLandscape = config.minuteStyleLandscape = config.secondStyleLandscape = SectionStyle.DIGIT;
		config.hourFormatLandscape = config.hourFormatPortrait = HourFormat.H24;
		return config;
	}

	/** The size of the cells (in pixels) */
	public int cellSize;
	/** The amount of spacing (in pixels) between each individual binary cell */
	public int cellSpacing;
	/** The colors to use for the clock (the renderer will use (cellValue - 1) to
	 * look up color, since cellValue == 0 results in no rendering */
	public Color[] colors;
	/** The amount of spacing (in pixels) around the edge of the binary clock */
	public int edgeSpacing;
	/** the {@link HourFormat} to use while in landscape */
	public HourFormat hourFormatLandscape;
	/** the {@link HourFormat} to use while in portrait */
	public HourFormat hourFormatPortrait;
	/** the {@link SectionStyle} to use for the hours section while in landscape */
	public SectionStyle hourStyleLandscape;
	/** the {@link SectionStyle} to use for the hours section while in portrait */
	public SectionStyle hourStylePortrait;
	/** the {@link SectionStyle} to use for the minutes section while in landscape */
	public SectionStyle minuteStyleLandscape;
	/** the {@link SectionStyle} to use for the minutes section while in portrait */
	public SectionStyle minuteStylePortrait;
	/** the {@link SectionStyle} to use for the seconds section while in landscape */
	public SectionStyle secondStyleLandscape;
	/** the {@link SectionStyle} to use for the seconds section while in portrait */
	public SectionStyle secondStylePortrait;
	/** The amount of spacing (in pixels) between each binary cell section
	 * (minutes, hours, seconds) */
	public int sectionSpacing;
}
