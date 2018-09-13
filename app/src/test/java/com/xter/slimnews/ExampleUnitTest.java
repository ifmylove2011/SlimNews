package com.xter.slimnews;

import com.xter.slimnews.presenstation.util.TimeUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void testTime(){
		String date = "2018-09-12 11:24:00";
		System.out.println(TimeUtil.getDelayFormat(date));
		System.out.println(TimeUtil.getNormalTime(0));
	}
}