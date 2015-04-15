
package com.perrigogames.binaryclock.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.perrigogames.binaryclock.ClockState;

public class ClockTest {

	@Test
	public void testBinLength () {
		for (int plc = 0; plc < 16; plc++)
			for (int val = (int)Math.pow(2, plc - 1); val < Math.pow(2, plc); val++) {
				System.out.printf("%d -> %d  ", val, ClockState.maxBinaryLength(val));
				assertEquals(plc, ClockState.maxBinaryLength(val));
				System.out.println("Correct");
			}
	}

	@Test
	public void testCombine () {
		int[][][] groups = new int[][][] { //
		{ { 1, 2 }, { 2, 1 } }, //
			{ { 2, 2, 2 }, { 2, 1, 2 } }, //
			{ { 1 }, { 1 } } //
		};
		int[][][] groupsAfter = new int[][][] { //
		{ { 0, 1, 2 }, { 0, 2, 1 } }, //
			{ { 2, 2, 2 }, { 2, 1, 2 } }, //
			{ { 0, 0, 1 }, { 0, 0, 1 } } //
		};
		int[][][] result = ClockState.combineArrays(groups);
		assertEquals(result.length, groupsAfter.length);
		for (int g = 0; g < result.length; g++) {
			assertEquals(result[g].length, groupsAfter[g].length);
			for (int x = 0; x < result[g].length; x++) {
				assertEquals(result[g][x].length, groupsAfter[g][x].length);
				for (int y = 0; y < result[g][x].length; y++)
					assertEquals(result[g][x][y], groupsAfter[g][x][y]);
			}
		}
	}
}
