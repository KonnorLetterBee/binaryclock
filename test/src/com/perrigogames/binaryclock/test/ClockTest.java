
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
}
