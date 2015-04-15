
package com.perrigogames.binaryclock;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockState {
	
	public static enum HourFormat {
		H12,
		H24;
	}

	public static enum SectionStyle {
		DIGIT,
		GROUP;
	}

	public static int[] binary (int value, int max) {
		int pos = maxBinaryLength(max);
		int curr = value;
		int[] bin = new int[pos];
		while (curr > 0) {
			bin[--pos] = curr % 2;
			curr = curr >> 1;
		}
		return bin;
	}

	public static int[][][] combineArrays (int[][] ... arrays) {
		int height = 0;
		for (int[][] ar : arrays)
			for (int[] arC : ar)
				height = Math.max(height, arC.length);
		int[][][] out = new int[arrays.length][][];
		for (int g = 0; g < arrays.length; g++) {
			int[][] ar = arrays[g];
			out[g] = new int[ar.length][];
			for (int x = 0; x < ar.length; x++) {
				int[] arC = ar[x];
				out[g][x] = new int[height];
				System.arraycopy(arC, 0, out[g][x], height - arC.length, arC.length);
			}
		}
		return out;
	}
	
	public static int[][] incrementTableVals (int[][] table) {
		for (int[] ar : table)
			for (int i = 0; i < ar.length; i++)
				ar[i] += 1;
		return table;
	}
	
	public static int maxBinaryLength (int value) {
		return value == 0 ? 0 : (int)Math.floor((Math.log(value) / Math.log(2)) + 1);
	}

	private int[][][] cells;
	private Calendar currentTime;
	private HourFormat hourFormat = HourFormat.H24;
	private SectionStyle hourStyle = SectionStyle.GROUP, minuteStyle = SectionStyle.GROUP, secondStyle = SectionStyle.GROUP;
	private int lastSecs = -1;
	private final int lastMins = -1;
	private final int lastHours = -1;

	public int[][][] getCells () {
		return cells;
	}
	
	public int[][] hours (HourFormat hourStyle, SectionStyle secStyle) {
		boolean h24 = hourStyle == HourFormat.H24;
		return minSec(secStyle, currentTime.get(h24 ? Calendar.HOUR_OF_DAY : Calendar.HOUR), h24 ? 24 : 12);
	}
	
	public int[][] minutes (SectionStyle secStyle) {
		return minSec(secStyle, currentTime.get(Calendar.MINUTE), 60);
	}
	
	public int[][] seconds (SectionStyle secStyle) {
		return minSec(secStyle, currentTime.get(Calendar.SECOND), 60);
	}
	
	public void setHourFormat (HourFormat f) {
		hourFormat = f;
	}

	public void setHourStyle (SectionStyle s) {
		hourStyle = s;
	}
	
	public void setMinuteStyle (SectionStyle s) {
		minuteStyle = s;
	}
	
	public void setOrientation (boolean portrait) {
		SectionStyle style = portrait ? SectionStyle.GROUP : SectionStyle.DIGIT;
		setHourStyle(style);
		setMinuteStyle(style);
		setSecondStyle(style);
		update();
	}
	
	public void setSecondStyle (SectionStyle s) {
		secondStyle = s;
	}

	/** Updates the stored time and, if the seconds value has changed, update the
	 * stored values array */
	public void update () {
		currentTime = new GregorianCalendar();
		int currSecs = currentTime.get(Calendar.SECOND);
		if (lastSecs == currSecs)
			return;
		lastSecs = currSecs;
		int[][] hours = hours(hourFormat, hourStyle);
		int[][] minutes = minutes(minuteStyle);
		int[][] seconds = seconds(secondStyle);
		cells = combineArrays(hours, minutes, seconds);
	}

	/** Converts a value into an array of binary cells organized by the rules of a
	 * particular {@link SectionStyle}
	 * @param secStyle the style to organize the section in
	 * @param value the current value of the sequence
	 * @param max the max value of the sequence
	 * @return the properly formatted {@link int[][]} */
	private int[][] minSec (SectionStyle secStyle, int value, int max) {
		int[][] table;
		if (secStyle == SectionStyle.GROUP)
			table = new int[][] { binary(value, max) };
		else
			table = new int[][] { binary(value / 10, 10), binary(value % 10, 10) };
		return incrementTableVals(table);
	}
}
