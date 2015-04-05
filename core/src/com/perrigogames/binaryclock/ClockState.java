
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

	public static int[][] combineArrays (int[][] ... arrays) {
		int width = 0, height = 0;
		for (int[][] ar : arrays) {
			width += ar.length;
			for (int[] arC : ar)
			height = Math.max(height, arC.length);
		}
		int[][] out = new int[width][height];
		int x = 0;
		for (int[][] ar : arrays)
			for (int[] arC : ar)
			System.arraycopy(arC, 0, out[x++], height - arC.length, arC.length);
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

	private int[][] cells;
	private Calendar currentTime;
	private HourFormat hourFormat = HourFormat.H24;
	private SectionStyle hourStyle = SectionStyle.GROUP, minuteStyle = SectionStyle.GROUP, secondStyle = SectionStyle.GROUP;
	private int lastSecs = -1;

	public int[][] getCells () {
		return cells;
	}
	
	public int[][] hours (HourFormat hourStyle, SectionStyle secStyle) {
		return ms(secStyle, currentTime.get(hourStyle == HourFormat.H24 ? Calendar.HOUR_OF_DAY : Calendar.HOUR));
	}
	
	public int[][] minutes (SectionStyle secStyle) {
		return ms(secStyle, currentTime.get(Calendar.MINUTE));
	}
	
	public int[][] seconds (SectionStyle secStyle) {
		return ms(secStyle, currentTime.get(Calendar.SECOND));
	}
	
	public void setHourStyle (HourFormat f) {
		hourFormat = f;
	}

	public void setHourStyle (SectionStyle s) {
		hourStyle = s;
	}
	
	public void setMinuteStyle (SectionStyle s) {
		minuteStyle = s;
	}
	
	public void setSecondStyle (SectionStyle s) {
		secondStyle = s;
	}

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

	private int[][] ms (SectionStyle secStyle, int value) {
		int[][] table;
		if (secStyle == SectionStyle.GROUP)
			table = new int[][] { binary(value) };
		else
			table = new int[][] { binary(value / 10), binary(value % 10) };
		return incrementTableVals(table);
	}
}
